package dc.framework.component.numberfield;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;




import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.converter.Converter;

import dc.framework.component.numberfield.shared.NumberFieldState;

/**
 * Converter for string value of {@link NumberField} (Double)
 * 
 * @author Kerim O.D.
 * 
 */
public class StringToDoubleConverter implements Converter<String, Double> {
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 2900524606474435324L;
	/**
	 * Integer number format
	 */
	private static final String NUMBER_FORMAT = "#,##,###";
	/**
	 * Number Sign #
	 */
	private static final String NUMBER_SIGN = "#";
	// field who's state used for convert
	private final NumberField field;

	public StringToDoubleConverter(NumberField field) {
		this.field = field;
	}

	/**
	 * See {@link Converter#getPresentationType()}
	 */
	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

	/**
	 * See {@link Converter#convertToModel(Object, Class, Locale)}
	 */
	@Override
	public Double convertToModel(String value,
			Class<? extends Double> targetType, Locale locale)
			throws ConversionException {
		if (value == null || value.isEmpty()) {
			return null;
		}
		NumberFormat format = createNumberFormat();
		try {
			double cvalue = format.parse(value).doubleValue();
			// check for: if input unsigned and value is negative
			boolean check = !field.isSigned() && cvalue < 0;
			if (check) {
				throw new InvalidValueException(field.getMessageUnsignedField());
			}
			// check for: is value less than minimum
			Double dvalue = field.getMinValue();
			if (dvalue != null) {
				check = cvalue >= dvalue;
				if (!check) {
					throw new InvalidValueException(
							field.getMessageValueIsLessThan() + " " + dvalue);
				}
			}
			// check for: is value more than maximum
			dvalue = field.getMaxValue();
			if (dvalue != null) {
				check = cvalue <= dvalue;
				if (!check) {
					throw new InvalidValueException(
							field.getMessageValueIsMoreThan() + " " + dvalue);
				}
			}
			return cvalue;
		} catch (ParseException ex) {
			throw new InvalidValueException(
					field.getMessageInvalidNumberValue());
		}
	}

	/**
	 * See {@link Converter#convertToPresentation(Object, Class, Locale)}
	 */
	@Override
	public String convertToPresentation(Double value,
			Class<? extends String> targetType, Locale locale)
			throws ConversionException {
		if (value == null) {
			return ""; // returns empty string
		}
		try {
			// formatting number value to string
			NumberFormat format = createNumberFormat();
			String svalue = format.format(value);
			return svalue;
		} catch (Exception ex) {
			throw new ConversionException(ex);
		}
	}

	/**
	 * Creating object for formatting/parsing number-string
	 * 
	 * @return number formatter
	 */
	private NumberFormat createNumberFormat() {
		DecimalFormat format = new DecimalFormat(createFormatString());
		NumberFieldState state = field.getState();
		format.setGroupingUsed(state.isUseGrouping);
		DecimalFormatSymbols custom = new DecimalFormatSymbols();
		custom.setDecimalSeparator(state.decimalSeparator);
		custom.setGroupingSeparator(state.groupingSeparator);
		format.setDecimalFormatSymbols(custom);
		return format;
	}

	/**
	 * Creating number-format string
	 * 
	 * @return number-format string
	 */
	private String createFormatString() {
		StringBuilder sb = new StringBuilder();
		sb.append(NUMBER_FORMAT);
		NumberFieldState state = field.getState();
		if (state.decimalLength > 0) {
			sb.append(".");
			for (int i = 0; i < state.decimalLength; i++) {
				sb.append(NUMBER_SIGN);
			}
		}
		return sb.toString();
	}

	/**
	 * See {@link Converter#getModelType()}
	 */
	@Override
	public Class<Double> getModelType() {
		return Double.class;
	}

}
