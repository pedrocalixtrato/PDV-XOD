package dc.visao.framework.util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vaadin.addons.maskedtextfield.DecimalField;
import org.vaadin.addons.maskedtextfield.MaskedTextField;
import org.vaadin.addons.maskedtextfield.NumericField;
import org.vaadin.addons.maskedtextfield.PrefixedMaskedTextField;

import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import dc.control.converter.CurrencyConverter;
import dc.framework.ConfigProperties;
import dc.visao.framework.component.BigDecimalConverter;
import dc.visao.framework.component.LookupComponent;

public final class ComponentUtil {
	
	static Class<?> fieldClass;
	private static final String[] PHONE_NINE_DIGITS_PREFIXES;
	
	private static NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance();
	
	static{
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(VaadinServlet.getCurrent()
						.getServletContext());
		ConfigProperties  config =(ConfigProperties) ctx
		.getBean(ConfigProperties.class);
		String prefixes = config.PHONE_NINE_DIGITS_PREFIXES;
		PHONE_NINE_DIGITS_PREFIXES = prefixes.split(",");
	}
	public static TextField buildPercentageField(String caption) {
		TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter("", " %"));

		return textField;
	}

	/**
	 * Use somente para campos do tipo BigDecimal.
	 * 
	 * @param caption
	 * @return
	 */
	public static TextField buildNumberField(String caption) {
		TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter());

		return textField;
	}

	public static TextField buildCurrencyField(String caption) {
		final TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter("R$ "));
		//textField.setConverter(Money.class);
		//textField.setConvertedValue(((BigDecimal) textField.getConvertedValue()).setScale(2, RoundingMode.HALF_EVEN));
		//textField.setConvertedValue(new DoubleConverter("R$ "));
		//textField.setConverter(new StringToDoubleConverter());
		textField.addTextChangeListener(event -> CurrencyConverter.vceMask(event, textField));

		return textField;
	}
	
	public static DecimalField buildDecimalField(String caption) {
		final DecimalField textField = new DecimalField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		//textField.setConverter(new StringToDoubleConverter());
		//setConverter(new MaskNumberConverter());
		//textField.setConverter(new StringToDoubleConverter());
		textField.addTextChangeListener(event -> CurrencyConverter.vceMask(event, textField));

		return textField;
	}
	
	public static NumericField buildCurrencyTField(String caption) {
		NumericField textField = new NumericField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter("R$ "));
		//textField.setConverter(new StringToDoubleConverter());
		textField.addTextChangeListener(event -> CurrencyConverter.vceMask(event, textField));

		return textField;
	}
	
	/*private void setSummaryFooterCells(FooterRow footer) {
        for (int i = 0; i < numberOfYearHalves; i++) {
            String propertyId = ExampleUtil.getBudgetPeriodPropertyId(i);
 
            // Loop items in the data source to calculate sums
            double sum = 0;
            for (Object itemId : sample.getContainerDataSource().getItemIds()) {
                BigDecimal value = (BigDecimal) sample.getContainerDataSource()
                        .getItem(itemId).getItemProperty(propertyId).getValue();
                sum += value.doubleValue();
            }
 
            // Use the same converter as the column values use
            footer.getCell(propertyId).setText(
                    ((DollarConverter) sample.getColumn(propertyId)
                            .getConverter()).convertToPresentation(
                            new BigDecimal(sum), String.class,
                            sample.getLocale()));
 
            // Align the footer text
            footer.getCell(propertyId).setStyleName("align-right");
        }
    }*/
	
	public static TextField buildTextField(String caption) {
		TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		
/*		 // Ajusta representação nula
        if (textField instanceof TextField) {
            textField.setNullRepresentation("");
            textField.setNullSettingAllowed(true);
        }

        // Atribui conversor ao tipo de retorno do campo no bean
        if (textField instanceof AbstractField) {
            AbstractField<?> af = (AbstractField) textField;
            af.setValidationVisible(false);
            af.setConverter(fieldClass);
        }
*/
		return textField;
	}
	

	public static ComboBox buildComboBox(String caption) {
		ComboBox comboBox = new ComboBox();
		comboBox.setCaption(caption);
		comboBox.setImmediate(true);
		comboBox.setSizeFull();
		comboBox.setFilteringMode(FilteringMode.CONTAINS);

		return comboBox;
	}

	public static <ID, T> LookupComponent<ID, T> buildLookup(
			String codeCaption, String comboCaption) {
		LookupComponent<ID, T> lookupComponent = new LookupComponent<>();
		lookupComponent.setCodeCaption(codeCaption);
		lookupComponent.setComboCaption(comboCaption);
		lookupComponent.setSizeFull();

		return lookupComponent;
	}

	public static RichTextArea buildRichTextArea(String caption) {
		RichTextArea richTextArea = new RichTextArea();
		richTextArea.setNullRepresentation("");
		richTextArea.setCaption(caption);
		richTextArea.setImmediate(true);
		richTextArea.setSizeFull();

		return richTextArea;
	}

	public static TextArea buildTextArea(String caption) {
		TextArea textArea = new TextArea();
		textArea.setNullRepresentation("");
		textArea.setCaption(caption);
		textArea.setImmediate(true);
		textArea.setSizeFull();

		return textArea;
	}

	public static PopupDateField buildPopupDateField(String caption) {
		PopupDateField data = new PopupDateField();
		data.setCaption(caption);
		data.setImmediate(true);
		data.setSizeFull();
		// data.setWidth("-1px");
		// data.setHeight("-1px");

		return data;
	}

	public static CheckBox buildCheckBox(String caption) {
		CheckBox checkBox = new CheckBox();
		checkBox.setCaption(caption);
		checkBox.setImmediate(true);
		checkBox.setSizeFull();

		return checkBox;
	}
	
	public static MaskedTextField buildMaskedTextField(String caption,String mask) {
		MaskedTextField textField = new MaskedTextField();
		textField.setMask(mask);
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setMaskClientOnly(true);
		// textField.setSizeFull();

		return textField;
	}

	public static PasswordField buildPasswordField(String caption) {
		PasswordField textField = new PasswordField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();

		return textField;
	}

	public static NumericField buildNumericField(String caption) {
		NumericField textField = new NumericField(caption);
		textField.setNullRepresentation("");
		textField.setImmediate(true);
		textField.setMaxLength(3);
		textField.setSizeFull();

		return textField;
	}
	
	public static MaskedTextField buildPhoneField(String caption){
		return buildPhoneField(caption, true);
	}
	public static MaskedTextField buildPhoneField(String caption, boolean nineDigitsAble) {
		Map<String, String> prefixesMap = new HashMap<String, String>();
		String nineDigitsMask = "(##) ####-#####";
		if (nineDigitsAble) {
			for (String prefix : PHONE_NINE_DIGITS_PREFIXES) {
				prefixesMap.put("(" + prefix + ")", nineDigitsMask);
			}
		}
		PrefixedMaskedTextField textField = new PrefixedMaskedTextField(caption, "(##) ####-####", prefixesMap);

		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setMaskClientOnly(true);

		return textField;
	}
	
	public static MaskedTextField buildCnpjField(String caption){
		Map<String, String> prefixesMap = new HashMap<String, String>();
		PrefixedMaskedTextField textField = new PrefixedMaskedTextField(caption, "##.###.###/####-##", prefixesMap);
		
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setMaskClientOnly(true);
        
        return textField;
	}
	
	public static TextField buildCurrencysField(String caption) {
		
		//caption = NUMBER_FORMAT.format(new Double(0));
		DecimalField field = new DecimalField("R$ #,##0.00", ',', '.');
		field.setNullRepresentation("");
		field.setConverter(new BigDecimalConverter());
		field.setCaption(caption);
		field.setImmediate(true);
		
        return field;
	}
	

}