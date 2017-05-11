package dc.framework.component.numberfield;

import dc.framework.component.numberfield.shared.NumberFieldState;

import com.vaadin.ui.TextField;

/**
 * This is addon modification of number field {@link vaadin.com/addon/number-field},
 * which accepts only number input and has configuration: <br/>
 * 1. Minimum value. If value != null
 * {@link StringToDoubleConverter#convertToModel(String, Class, java.util.Locale)}
 * validates value for minimum; <br/>
 * 2. Maximum value If value != null
 * {@link StringToDoubleConverter#convertToModel(String, Class, java.util.Locale)}
 * validates value for maximum; <br/>
 * 3. {@link NumberField#getDoubleValue()} returns null, if string value is
 * invalid number; <br/>
 * 4. Character of decimal separator. Default = '.';<br/>
 * 5. Decimal length. Default = 0; <br/>
 * 6. Character of grouping separator. Default = ' ';<br/>
 * 7. Is signed. Default = true; <br/>
 * 8. Is use grouping. Default = false; <br/>
 * 
 * @author Kerim O.D.
 * 
 */
public class NumberField extends TextField {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 7663236836018122696L;
	private static final String NEGATIVE_VALUE = "Negative value";

	/**
	 * Minimum value of field
	 */
	private Double minValue = null;
	/**
	 * Maximum value of field
	 */
	private Double maxValue = null;
	// message strings
	private String messageInvalidNumberValue = "Invalid number value";
	private String messageValueIsLessThan = "Value is less than";
	private String messageValueIsMoreThan = "Value is more than";
	private String messageUnsignedField = "Only unsigned value";
	

	public NumberField() {
		initConverter();
	}

	public NumberField(String caption) {
		super(caption);
		initConverter();
	}

	private void initConverter() {
		// setting value converter for this field
		setConverter(new StringToDoubleConverter(this));
	}

	@Override
	protected NumberFieldState getState() {
		return (NumberFieldState) super.getState();
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public void setDecimalSeparator(char sep) {
		getState().decimalSeparator = sep;
	}

	public char getDecimalSeparator() {
		return getState().decimalSeparator;
	}

	public void setDecimalLength(int length) {
		if (length < 0) {
			throw new IllegalArgumentException(NEGATIVE_VALUE);
		}
		getState().decimalLength = length;
	}

	public boolean isUseGrouping() {
		return getState().isUseGrouping;
	}

	public void setUseGrouping(boolean use) {
		getState().isUseGrouping = use;
	}

	public boolean isSigned() {
		return getState().isSigned;
	}

	public void setSigned(boolean signed) {
		getState().isSigned = signed;
	}

	public int getDecimalLength() {
		return getState().decimalLength;
	}

	public void setGroupingSeparator(char sep) {
		getState().groupingSeparator = sep;
	}

	public char getGroupingSeparator() {
		return getState().groupingSeparator;
	}

	public Double getDoubleValue() {
		return (Double) getConvertedValue();
	}

	public void setDoubleValue(Double value) {
		setConvertedValue(value);
	}

	public String getMessageInvalidNumberValue() {
		return messageInvalidNumberValue;
	}

	public void setMessageInvalidNumberValue(String message) {
		this.messageInvalidNumberValue = message;
	}

	public String getMessageValueIsLessThan() {
		return messageValueIsLessThan;
	}

	public void setMessageValueIsLessThan(String message) {
		this.messageValueIsLessThan = message;
	}

	public String getMessageValueIsMoreThan() {
		return messageValueIsMoreThan;
	}

	public void setMessageValueIsMoreThan(String message) {
		this.messageValueIsMoreThan = message;
	}

	public String getMessageUnsignedField() {
		return messageUnsignedField;
	}

	public void setMessageUnsignedField(String message) {
		this.messageUnsignedField = message;
	}
}
