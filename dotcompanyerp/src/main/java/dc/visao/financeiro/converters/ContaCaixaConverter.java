package dc.visao.financeiro.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.financeiro.ContaCaixa;

public class ContaCaixaConverter implements Converter<Object, ContaCaixa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ContaCaixa convertToModel(Object value, Class<? extends ContaCaixa> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if(value== null){
			return null;
		}
		if(value instanceof ContaCaixa)
		{
			return (ContaCaixa) value;
		}
		
		return null;
	}

	@Override
	public String convertToPresentation(ContaCaixa value, Class<? extends Object> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if(value== null){
			return "";
		}
		return value.getNome();
	}

	@Override
	public Class<ContaCaixa> getModelType() {
		// TODO Auto-generated method stub
		return ContaCaixa.class;
	}

	@Override
	public Class<Object> getPresentationType() {
		// TODO Auto-generated method stub
		return Object.class;
	}

}
