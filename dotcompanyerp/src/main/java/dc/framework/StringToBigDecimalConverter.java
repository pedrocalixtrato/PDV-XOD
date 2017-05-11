package dc.framework;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class StringToBigDecimalConverter implements
		Converter<String, BigDecimal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 477290279683278370L;


	/**
	 * Returns the format used by {@link #convertToPresentation(Double, Locale)}
	 * and {@link #convertToModel(String, Locale)}.
	 * 
	 * @param locale
	 *            The locale to use
	 * @return A NumberFormat instance
	 */
	protected NumberFormat getFormat(Locale locale) {
		if (locale == null) {
			locale = Locale.getDefault();
		}

		return NumberFormat.getNumberInstance(locale);
	}


	@Override
	public Class<BigDecimal> getModelType() {
		return BigDecimal.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

	@Override
	public BigDecimal convertToModel(String value,
			Class<? extends BigDecimal> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null) {
			return null;
		}

		// Remove leading and trailing white space
		value = value.trim();

		ParsePosition parsePosition = new ParsePosition(0);
		Number parsedValue = getFormat(locale).parse(value, parsePosition);
		if (parsePosition.getIndex() != value.length()) {
			throw new ConversionException("Could not convert '" + value
					+ "' to " + getModelType().getName());
		}
		if (parsedValue == null) {
			// Convert "" to null
			return null;
		}

		return new BigDecimal(parsedValue.doubleValue());
	}

	@Override
	public String convertToPresentation(BigDecimal value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (value == null) {
			return null;
		}

		return getFormat(locale).format(value.doubleValue());
	}
}
