package dc.visao.framework.component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.vaadin.data.util.converter.Converter;

public class PercentageConverter implements Converter<String, BigDecimal> {

	private static final long serialVersionUID = 8469805997615778349L;

	private NumberFormat getFormat() {
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
		return decimalFormat;
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

		if (StringUtils.isEmpty(value)) {
			return null;
		}
		value = value.trim();
		value = value.replace(" %", "");
		value = value.replaceAll("\\.", ",");

		try {
			return new BigDecimal(getFormat().parse(value).doubleValue());
		} catch (ParseException e) {
			throw new ConversionException(e);
		}

		
	}

	@Override
	public String convertToPresentation(BigDecimal value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {

		if (value != null) {
			return getFormat().format(value.doubleValue()) + " %";
		}
		return null;
		
		
	}

}
