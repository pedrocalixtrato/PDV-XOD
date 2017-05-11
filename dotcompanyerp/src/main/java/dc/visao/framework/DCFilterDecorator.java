package dc.visao.framework;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;

import org.tepi.filtertable.FilterDecorator;
import org.tepi.filtertable.numberfilter.NumberFilterPopupConfig;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.Resolution;

import dc.visao.framework.component.SearchableCustomListTable.State;

public class DCFilterDecorator implements FilterDecorator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getEnumFilterDisplayName(Object propertyId, Object value) {
		if ("state".equals(propertyId)) {
			State state = (State) value;
			switch (state) {
			case CREATED:
				return "Order has been created";
			case PROCESSING:
				return "Order is being processed";
			case PROCESSED:
				return "Order has been processed";
			case FINISHED:
				return "Order is delivered";
			}
		}
		// returning null will output default value
		return null;
	}

	@Override
	public Resource getEnumFilterIcon(Object propertyId, Object value) {
		if ("state".equals(propertyId)) {
			State state = (State) value;
			switch (state) {
			case CREATED:
				return new ThemeResource("../runo/icons/16/document.png");
			case PROCESSING:
				return new ThemeResource("../runo/icons/16/reload.png");
			case PROCESSED:
				return new ThemeResource("../runo/icons/16/ok.png");
			case FINISHED:
				return new ThemeResource("../runo/icons/16/globe.png");
			}
		}
		return null;
	}

	@Override
	public String getBooleanFilterDisplayName(Object propertyId, boolean value) {
		if ("validated".equals(propertyId)) {
			return value ? "Validated" : "Not validated";
		}
		// returning null will output default value
		return null;
	}

	@Override
	public Resource getBooleanFilterIcon(Object propertyId, boolean value) {
		if ("validated".equals(propertyId)) {
			return value ? new ThemeResource("../runo/icons/16/ok.png") : new ThemeResource("../runo/icons/16/cancel.png");
		}
		return null;
	}

	@Override
	public String getFromCaption() {
		return "Data Inicial:";
	}

	@Override
	public String getToCaption() {
		return "Data Final:";
	}

	@Override
	public String getSetCaption() {
		// use default caption
		return "Filtrar";
	}

	@Override
	public String getClearCaption() {
		// use default caption
		return "Limpar";
	}

	@Override
	public boolean isTextFilterImmediate(Object propertyId) {
		// use text change events for all the text fields
		return true;
	}

	@Override
	public int getTextChangeTimeout(Object propertyId) {
		// use the same timeout for all the text fields
		return 1300;
	}

	@Override
	public String getAllItemsVisibleString() {
		return "";
	}

	@Override
	public Resolution getDateFieldResolution(Object propertyId) {
		return Resolution.DAY;
	}

	public DateFormat getDateFormat(Object propertyId) {
		return DateFormat.getDateInstance(DateFormat.SHORT, new Locale("pt", "BR"));
	}

	@Override
	public boolean usePopupForNumericProperty(Object propertyId) {
		return true;
	}

	@Override
	public String getDateFormatPattern(Object propertyId) {
		return null;
	}

	@Override
	public Locale getLocale() {
		return new Locale("pt", "BR");
	}

	@Override
	public NumberFilterPopupConfig getNumberFilterPopupConfig() {
		return null;
	}
}
