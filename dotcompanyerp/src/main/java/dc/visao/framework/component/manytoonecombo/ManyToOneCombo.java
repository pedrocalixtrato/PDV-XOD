package dc.visao.framework.component.manytoonecombo;

import it.zero11.vaadin.asyncfiltercombobox.AsyncFilterComboBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


//import com.porotype.iconfont.FontAwesome.Icon;
import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import dc.framework.ConfigProperties;

@SuppressWarnings("serial")
@Component
public class ManyToOneCombo<T> extends CustomComponent {

	private HorizontalLayout mainLayout;
	private Button btnEdit;
	private Button btnNew;
	private DCComboBox cmbResult;
	private ItemValue createItemValue;
	private ItemValue searchItemValue;

	private ManyToOneComboModel<T> model;
	private String filterString;
	private LinkedList<ValueChangeListener> valueChangeListeners = null;

	public static int ITEM_TYPE_BEAN = 0;
	public static int ITEM_TYPE_CREATE = 1;
	public static int ITEM_TYPE_SEARCH = 2;

	public static Logger logger = Logger.getLogger(ManyToOneCombo.class);

	public ManyToOneCombo() {
		buildMainLayout();
		addCustomButtons(mainLayout);
		setCompositionRoot(mainLayout);
	}

	private void addCustomButtons(HorizontalLayout layout) {
		// lblEdit
		layout.setSpacing(false);
		this.btnNew = new Button();

		btnNew.setIcon(FontAwesome.FILE);
		btnNew.setHtmlContentAllowed(true);
		btnNew.setId("lblNew");
		btnNew.setWidth("22px");
		btnNew.setIconAlternateText("Criar Novo");
		btnNew.setDescription("Criar Novo");
		btnNew.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				ItemValue val = (ItemValue) cmbResult.getValue();

				if (val != null && val.getFilter() != null) {
					model.onCriarNovo(val.getFilter());
				} else {
					model.onCriarNovo("");
				}
			}

		});

		layout.addComponent(btnNew);
		layout.setComponentAlignment(btnNew, Alignment.BOTTOM_LEFT);

		try {
			UI.getCurrent()
					.getPage()
					.getStyles()
					.add("#lblNew i { font-size: 12pt; } #lblNew { padding: 5px 1px 3px 4px; float:left; }");
		} catch (Exception e) {

		}
		
		// lblEdit
		btnEdit = new Button();
		btnEdit.setIcon(FontAwesome.EDIT);
		btnEdit.setHtmlContentAllowed(true);
		btnEdit.setId("lblEdit");
		btnEdit.setWidth("22px");
		btnEdit.setIconAlternateText("Editar");
		btnEdit.setDescription("Editar");
		// ButtonHintExtension.addHint("Editar",btnEdit);
		mainLayout.addComponent(btnEdit);
		mainLayout.setComponentAlignment(btnEdit, Alignment.BOTTOM_LEFT);

		try {
			UI.getCurrent()
					.getPage()
					.getStyles()
					.add("#lblEdit i { font-size: 12pt; } #lblEdit { padding: 5px 1px 3px 4px; float:left;}");
				
		} catch (Exception e) {
		}

		Button btnAdvancedSearch = new Button();
		btnAdvancedSearch.setIcon(FontAwesome.SEARCH);
		btnAdvancedSearch.setHtmlContentAllowed(true);
		btnAdvancedSearch.setId("lblAdvSearch");
		btnAdvancedSearch.setWidth("22px");
		btnAdvancedSearch.setIconAlternateText("Busca avançada");
		btnAdvancedSearch.setDescription("Busca avançada");
		btnAdvancedSearch.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				model.onAdvancedSearch();
			}

		});

		layout.addComponent(btnAdvancedSearch);
		layout.setComponentAlignment(btnAdvancedSearch, Alignment.BOTTOM_LEFT);

		try {
			UI.getCurrent()
					.getPage()
					.getStyles()
					.add("#lblAdvSearch i { font-size: 12pt; } #lblAdvSearch { padding: 5px 1px 3px 4px; float:left;}");
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("serial")
	class FilteredBeanItemContainer extends BeanItemContainer<ItemValue> {

		public FilteredBeanItemContainer(List<T> all) throws IllegalArgumentException {
			super(ItemValue.class);

			this.addAll(wrapValues(all));
		}

		public FilteredBeanItemContainer() throws IllegalArgumentException {
			this(getModel().getAll());
		}
		
		@Override
		public void addContainerFilter(Filter filter) {
			String q = filterString;

			if (q != null && q.length() >= 3) {
				removeAllItems();

				searchItemValue = new ItemValue();
				searchItemValue.setType(ITEM_TYPE_SEARCH);
				searchItemValue.setCaption("Pesquisa Avançada...");
				searchItemValue.setFilter(q);

				addItem(searchItemValue);

				// cmbResult.setItemIcon(searchItemValue, new
				// ThemeResource("components/img/close.png"));

				// Adiciona os itens filtrados
				if (model != null) {
					List l = model.getResultado(q);

					if (l != null && l.size() > 0) {
						addAll(wrapValues(l));
					}
				}

				// Adiciona "criar novo"
				createItemValue = new ItemValue();
				createItemValue.setType(ITEM_TYPE_CREATE);
				createItemValue.setCaption("Criar Novo Cadastro: " + q);
				createItemValue.setFilter(q);

				addItem(createItemValue);
			} else {
				removeAllItems();

				addAll(wrapValues(model.getAll()));
			}
		}

	}

	@SuppressWarnings("serial")
	private void setupActions() {
		createItemValue = new ItemValue();
		createItemValue.setType(ITEM_TYPE_CREATE);

		//cmbResult.setContainerDataSource(new FilteredBeanItemContainer());
		cmbResult.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		cmbResult.setItemCaptionPropertyId("caption");
		//cmbResult.setSizeFull();

		cmbResult.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				logger.info("value change");
				selectSpecialItem();
			}

		});

		cmbResult.addItemSetChangeListener(new ItemSetChangeListener() {
			@Override
			public void containerItemSetChange(ItemSetChangeEvent event) {
				logger.info("item set change");
			}
		});

		cmbResult.addBlurListener(new BlurListener() {
			@Override
			public void blur(BlurEvent event) {
				logger.info("blur on combo");
			}
		});

		btnEdit.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (getValue() != null)
					model.onEditar(getValue());
			}

		});
	}

	@SuppressWarnings("serial")
	public class ItemValue implements Serializable {
		private T bean;
		private int type = ITEM_TYPE_BEAN;
		private String caption;
		private String filter;

		public String getCaption() {
			try {
				if (type == ITEM_TYPE_BEAN) {
					return BeanUtils.getProperty(bean,
							model.getCaptionProperty()).toString();
				} else {
					return this.caption;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public T getBean() {
			return bean;
		}

		public void setBean(T bean) {
			this.bean = bean;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public void setCaption(String caption) {
			this.caption = caption;
		}

		public String getFilter() {
			return filter;
		}

		public void setFilter(String filter) {
			this.filter = filter;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((bean == null) ? 0 : bean.hashCode());

			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			ItemValue other = (ItemValue) obj;

			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (bean == null) {
				if (other.bean != null)
					return false;
			} else if (!bean.equals(other.bean))
				return false;

			return true;
		}

		private ManyToOneCombo getOuterType() {
			return ManyToOneCombo.this;
		}
	}

	@SuppressWarnings("unchecked")
	protected void selectSpecialItem() {
		logger.info("select special item...");

		ItemValue val = (ItemValue) cmbResult.getValue();

		if (val == null)
			return;
		if (val.getType() == ITEM_TYPE_BEAN)
			return;

		if (val.getType() == ITEM_TYPE_CREATE) {
			model.onCriarNovo(val.getFilter());
		} else if (val.getType() == ITEM_TYPE_SEARCH) {
			model.onAdvancedSearch();
		}
	}

	private List<ItemValue> wrapValues(List<T> resultado) {
		List<ItemValue> lst = new ArrayList<>();

		for (T val : resultado) {
			ItemValue item = new ItemValue();
			item.setBean(val);
			lst.add(item);
		}

		return lst;
	}

	@SuppressWarnings("unchecked")
	public T getValue() {
		if (cmbResult != null && cmbResult.getValue() != null) {
			return ((ItemValue) cmbResult.getValue()).getBean();
		} else {
			return null;
		}
	}

	private HorizontalLayout buildMainLayout() {
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		// mainLayout.setWidthUndefined();
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		// setWidth("100.0%");
		setHeight("100.0%");

		// cmbResult
		cmbResult = new DCComboBox();

		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(VaadinServlet.getCurrent()
						.getServletContext());

		ConfigProperties config = (ConfigProperties) ctx
				.getBean(ConfigProperties.class);
		cmbResult.setFilterChangeTimeout(config.COMBO_DELAYVALUE);
		// cmbResult.setFilterChangeTimeout(5000);

		cmbResult.setImmediate(true);
		cmbResult.setFilteringMode(FilteringMode.CONTAINS);
		//cmbResult.setHeight("250px");
		//cmbResult.setWidth("35px");
		//cmbResult.setSizeFull();
		mainLayout.addComponent(cmbResult);
		cmbResult.setStyleName("manyToOneCombo");
		
		try {

			UI.getCurrent().getPage().getStyles().add(".manyToOneCombo{width:200px; float:left;} ");
		} catch (Exception e) {

		}

		return mainLayout;
	}

	public void setCaption(String caption) {
		cmbResult.setCaption(caption);
	}

	public String getCaption() {
		return cmbResult.getCaption();
	}

	public ManyToOneComboModel<T> getModel() {
		return model;
	}

	public void setModel(ManyToOneComboModel<T> model) {
		this.model = model;

		if (model instanceof DefaultManyToOneComboModel) {
			((DefaultManyToOneComboModel) this.model).setCombo(this);

			if (((DefaultManyToOneComboModel) this.model)
					.permissionToCreateOrEdit()) {
				btnEdit.setVisible(false);
				btnNew.setVisible(false);
			}
		}

		setupActions();
	}

	public void setValue(T bean) {
        if (bean != null) {
            ItemValue beanItem = new ItemValue();

            List<T> resultado = model.getAll();
            if (cmbResult.getContainerDataSource() == null || cmbResult.getContainerDataSource().getItemIds().size() == 0) {
    			cmbResult.setContainerDataSource(new FilteredBeanItemContainer(resultado));
    		}

            for (T t : resultado) {
                ItemValue item = new ItemValue();

                item.setBean(t);

                if (bean.equals(t)) {
                    beanItem = item;
                    cmbResult.getContainerDataSource().removeItem(item);
                }

                cmbResult.getContainerDataSource().addItem(item);
            }

            cmbResult.setValue(beanItem);
        } else {

            cmbResult.setValue(null);
        }
	}

	public void addValueChangeListener(ValueChangeListener listener) {
		//System.out.println("modelo chanaddValueChangeListenerged:");

		if (valueChangeListeners == null) {
			valueChangeListeners = new LinkedList<ValueChangeListener>();
		}

		cmbResult.addValueChangeListener(listener);
		valueChangeListeners.add(listener);
	}

	public T getItemValueBean(ItemValue item) {
		return item.getBean();
	}

	class DCComboBox extends AsyncFilterComboBox {

		@Override
		public void changeVariables(final Object source,
				final Map<String, Object> variables) {
			String newFilter;

			if ((newFilter = (String) variables.get("filter")) != null) {
				filterString = newFilter;
			}

			super.changeVariables(source, variables);
		}

		@Override
		protected void setValue(Object newValue, boolean repaintIsNotNeeded)
				throws com.vaadin.data.Property.ReadOnlyException {
			super.setValue(newValue, repaintIsNotNeeded);
		}

	};

	public void setRequired(Boolean required) {
		cmbResult.setRequired(required);
	}

	public boolean isRequired() {
		return cmbResult.isRequired();
	}
	
	@Override
	public void beforeClientResponse(boolean initial) {
		if (cmbResult.getContainerDataSource() == null || cmbResult.getContainerDataSource().getItemIds().size() == 0) {
			cmbResult.setContainerDataSource(new FilteredBeanItemContainer());
		}
		super.beforeClientResponse(initial);
	}
}