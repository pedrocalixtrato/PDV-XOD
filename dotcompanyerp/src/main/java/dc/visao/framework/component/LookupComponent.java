package dc.visao.framework.component;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.AbstractBeanContainer.BeanIdResolver;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class LookupComponent<ID, T> extends CustomComponent {

	private static final long serialVersionUID = -6755998687826121235L;

	private HorizontalLayout mainLayout;
	private ComboBox comboBox;
	private TextField code;

	private String codeProperty = "id";

	private BeanContainer<ID, T> container;

	public LookupComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@SuppressWarnings("serial")
	private void buildMainLayout() {
		mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);

		code = new TextField();
		code.setNullRepresentation("");
		code.setImmediate(true);
		code.setSizeFull();
		code.setConverter(new StringToIntegerConverter());
		mainLayout.addComponent(code);

		comboBox = new ComboBox();
		comboBox.setImmediate(true);
		comboBox.setSizeFull();
		comboBox.setFilteringMode(FilteringMode.CONTAINS);
		mainLayout.addComponent(comboBox);

		code.addBlurListener(new BlurListener() {

			@Override
			public void blur(BlurEvent event) {
				Object itemId = code.getConvertedValue();
				if (comboBox.containsId(itemId)) {
					comboBox.select(itemId);
				} else {
					code.setValue(null);
					comboBox.setValue(null);
				}
			}
		});

		comboBox.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				Object comboValue = comboBox.getValue();
				if (comboValue != null) {
					code.setValue(String.valueOf(comboValue));
				} else {
					code.setValue(null);
				}
			}
		});

	}

	public void setComboCaption(String caption) {
		comboBox.setCaption(caption);
	}

	public void setCodeCaption(String caption) {
		code.setCaption(caption);
	}


	public void setItemCaptionPropertyId(Object propertyId) {
		comboBox.setItemCaptionPropertyId(propertyId);
	}

	public Object getValue() {
		return comboBox.getValue();
	}

	public void select(Object item) {
		Field field = ReflectionUtils.findField(item.getClass(), codeProperty);
		field.setAccessible(true);
		Object fieldValue = ReflectionUtils.getField(field, item);
		
		comboBox.select(fieldValue);
	}

	public void setCodeProperty(String codeProperty) {
		this.codeProperty = codeProperty;
	}

	@SuppressWarnings({"serial","unchecked"})
	public BeanContainer<ID, T> createContainer(Class<T> classContainer) {
		if (container != null) {
			throw new IllegalArgumentException("O container já foi instanciado!");
		}
		container = new BeanContainer<>(classContainer);
		container.setBeanIdResolver(new BeanIdResolver<ID, T>() {

			@Override
			public ID getIdForBean(T bean) {
				Field field = ReflectionUtils.findField(bean.getClass(), codeProperty);
				field.setAccessible(true);
				Object fieldValue = ReflectionUtils.getField(field, bean);
				
				return (ID) fieldValue;
			}
		});
		comboBox.setContainerDataSource(container);
		return container;
	}

}
