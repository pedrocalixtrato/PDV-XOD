package dc.visao.ponto.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.ponto.PontoClassificacaoJornada;

public class PontoClassificacaoJornadaConverter implements Converter<String, PontoClassificacaoJornada> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PontoClassificacaoJornada convertToModel(String value,
			Class<? extends PontoClassificacaoJornada> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Verificar
		return null;
	}

	@Override
	public String convertToPresentation(PontoClassificacaoJornada value, Class<? extends String> targetType,
			Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return value.getNome();
	}

	@Override
	public Class<PontoClassificacaoJornada> getModelType() {
		// TODO Auto-generated method stub
		return PontoClassificacaoJornada.class;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}

}