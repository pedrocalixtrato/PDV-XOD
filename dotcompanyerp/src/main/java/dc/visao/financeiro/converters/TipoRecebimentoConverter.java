package dc.visao.financeiro.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.financeiro.TipoRecebimento;

public class TipoRecebimentoConverter implements Converter<String, TipoRecebimento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TipoRecebimento convertToModel(String value, Class<? extends TipoRecebimento> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Verificar
		return null;
	}

	@Override
	public String convertToPresentation(TipoRecebimento value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return value.getDescricao();
	}

	@Override
	public Class<TipoRecebimento> getModelType() {
		// TODO Auto-generated method stub
		return TipoRecebimento.class;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}

}
