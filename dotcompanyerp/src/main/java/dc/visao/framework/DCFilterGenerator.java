package dc.visao.framework;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.tepi.filtertable.FilterGenerator;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;

import dc.anotacoes.AnotacoesUtil;

public class DCFilterGenerator<E> implements FilterGenerator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<E> entityClass;

	public DCFilterGenerator(Class entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Filter generateFilter(Object propertyId, Object value) {
		if ("id".equals(propertyId)) {
			/* Create an 'equals' filter for the ID field */
			if (value != null && value instanceof String) {
				try {
					return new Compare.Equal(propertyId, Integer.parseInt((String) value));
				} catch (NumberFormatException ignored) {
					// If no integer was entered, just generate default filter
				}
			}
		} else if ("checked".equals(propertyId)) {
			if (value != null && value instanceof Boolean) {
				if (Boolean.TRUE.equals(value)) {
					return new Compare.Equal(propertyId, value);
				} else {
					return new Or(new Compare.Equal(propertyId, true), new Compare.Equal(propertyId, false));
				}
			}
		}

		return null;
	}

	@Override
	public Filter generateFilter(Object propertyId, Field<?> originatingField) {
		// Use the default filter
		return null;
	}

	@Override
	public AbstractField<?> getCustomFilterComponent(Object propertyId) {
		// removed custom filter component for id
		if ("checked".equals(propertyId)) {
			CheckBox box = new CheckBox();
			return box;
		}
		return null;
	}

	@Override
	public void filterRemoved(Object propertyId) {
		// Notification n = new Notification("Filter removed from: " +
		// propertyId, Notification.Type.TRAY_NOTIFICATION);
		// n.setDelayMsec(800);
		// n.show(Page.getCurrent());
	}

	@Override
	public void filterAdded(Object propertyId, Class<? extends Filter> filterType, Object value) {
		// Notification n = new Notification("Filter added to: " + propertyId,
		// Notification.Type.TRAY_NOTIFICATION);
		// n.setDelayMsec(800);
		// n.show(Page.getCurrent());
	}

	@Override
	public Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
		/* Return null -> Does not add any filter on failure */
		return null;
	}
}
