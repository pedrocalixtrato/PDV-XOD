package dc.visao.framework.component;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.sqlcontainer.RowId;

public class StringtoRowIdConverter implements Converter<String, RowId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RowId convertToModel(String value,

	Class<? extends RowId> targetType, Locale locale)

	throws com.vaadin.data.util.converter.Converter.ConversionException {

		// TODO Auto-generated method stub

		if (value == null) {

			return null;

		}

		return new RowId(value);

	}

	@Override
	public String convertToPresentation(RowId value,

	Class<? extends String> targetType, Locale locale)

	throws com.vaadin.data.util.converter.Converter.ConversionException {

		// TODO Auto-generated method stub

		if (value == null) {

			return null;

		}

		return value.toString();

	}

	@Override
	public Class<RowId> getModelType() {

		// TODO Auto-generated method stub

		return RowId.class;

	}

	@Override
	public Class<String> getPresentationType() {

		// TODO Auto-generated method stub

		return String.class;

	}

}