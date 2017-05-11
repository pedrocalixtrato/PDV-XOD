package dc.visao.financeiro.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.financeiro.TipoPagamento;

public class TipoPagamentoConverter implements Converter<String, TipoPagamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TipoPagamento convertToModel(String value, Class<? extends TipoPagamento> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Verificar
		return null;
	}

	@Override
	public String convertToPresentation(TipoPagamento value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return value.getDescricao();
	}

	@Override
	public Class<TipoPagamento> getModelType() {
		// TODO Auto-generated method stub
		return TipoPagamento.class;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}

}
