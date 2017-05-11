package dc.framework.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

import dc.control.util.NumberUtils;
import dc.control.util.StringUtils;

public class NumberField extends TextField {

	private static final String regexOnlyDigits = "/\\d+\\,?\\d*/";
	private static final DecimalFormat df = new DecimalFormat("0.##");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int fractionLenght = 2;

	public int getFractionLenght() {
		return fractionLenght;
	}

	public void setFractionLenght(int fractionLenght) {
		this.fractionLenght = fractionLenght;
	}

	public NumberField(String caption) {
		super(caption);

		this.addTextChangeListener(new TextChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				String newValue = event.getText();

				newValue = newValue.replaceAll(regexOnlyDigits, "");

				if (StringUtils.isBlank(newValue)) {
					newValue = String.valueOf(new Integer(0));
				}

				setValue(newValue);
			}

		});
	}

	public Integer getIntegerValue() {

		String newValue = this.getValue().replaceAll(regexOnlyDigits, "");

		if (StringUtils.isBlank(newValue)) {
			newValue = "0";
		}
		Number parse = 0;
		try {
			parse = df.parse(newValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return NumberUtils.toInt(parse);
	}

	public BigDecimal getBigDecimalValue() {

		String newValue = this.getValue();

		if (StringUtils.isBlank(newValue)) {
			newValue = "0";
		}
		
		Number parse = 0;
		try {
			parse = df.parse(newValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return NumberUtils.createBigDecimal(parse).setScale(fractionLenght, RoundingMode.HALF_UP);
	}

	public void setValue(Integer newValue) throws com.vaadin.data.Property.ReadOnlyException {

		if (NumberUtils.isBlank(newValue)) {
			newValue = new Integer(0);
		}

		/*String value = String.valueOf(newValue);
		value = value.replaceAll(regexOnlyDigits, "");
/*/
		super.setValue(df.format(newValue));
	}

	public void setValue(BigDecimal newValue) throws com.vaadin.data.Property.ReadOnlyException {

		if (NumberUtils.isBlank(newValue)) {
			newValue = new BigDecimal(0);
		}

		/*String value = String.valueOf(newValue.setScale(fractionLenght, RoundingMode.HALF_UP));
		value = value.replaceAll(regexOnlyDigits, "");
*/
		super.setValue(df.format(newValue.setScale(fractionLenght, RoundingMode.HALF_UP)));
	}

	

}