package dc.framework.component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import dc.framework.component.numberfield.NumberField;

public class CurrencyField extends NumberField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static NumberFormat NF = NumberFormat
			.getCurrencyInstance(new Locale("pt", "BR"));

	public CurrencyField() {
		this("");
	}

	public CurrencyField(String caption) {
		super(caption);
		this.setDecimalSeparator(',');
		this.setGroupingSeparator('.');
		this.setDecimalLength(2);
		this.addFocusListener(new FocusListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				CurrencyField field = (CurrencyField) event
						.getComponent();
				field.selectAll();
			}
		});
	}

	public void setValue(BigDecimal value)
			throws com.vaadin.data.Property.ReadOnlyException {
		setDoubleValue(value.doubleValue());
	}

	public BigDecimal getBigDecimalValue() {
		return new BigDecimal(this.getDoubleValue());
	}

	@Override
	public String getValue() {
		return NF.format(this.getDoubleValue());
	}

	protected void setValue(String newFieldValue, boolean repaintIsNotNeeded)
			throws Property.ReadOnlyException, Converter.ConversionException,
			InvalidValueException {

		super.setValue(newFieldValue.replace("R$", "").trim(),
				repaintIsNotNeeded);

	}
}