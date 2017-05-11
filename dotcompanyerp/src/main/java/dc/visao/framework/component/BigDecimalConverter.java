package dc.visao.framework.component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.vaadin.data.util.converter.Converter;

public class BigDecimalConverter implements Converter<String, BigDecimal> {

	private static final long serialVersionUID = 8469805997615778349L;

	private String prefix = "";
	private String suffix = "";

	public BigDecimalConverter() {
	}

	public BigDecimalConverter(String prefix) {
		this.prefix = prefix;
	}

	public BigDecimalConverter(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}

	private NumberFormat getFormat() {
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
		return decimalFormat;
	}

	@Override
	public BigDecimal convertToModel(String value, Class<? extends BigDecimal> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		value = value.trim();
		value = value.replace(prefix, "");
		value = value.replace(suffix, "");
		//value = value.replaceAll("\\.", ",");

		try {
			return new BigDecimal(getFormat().parse(value).doubleValue());
		} catch (ParseException e) {
			throw new ConversionException(e);
		}
	}

	@Override
	public String convertToPresentation(BigDecimal value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value != null) {
			return prefix + getFormat().format(value.doubleValue()) + suffix;
		}
		return null;
	}

	@Override
	public Class<BigDecimal> getModelType() {
		return BigDecimal.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

}
