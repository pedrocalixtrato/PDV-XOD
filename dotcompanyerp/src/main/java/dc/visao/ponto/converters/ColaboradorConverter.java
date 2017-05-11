package dc.visao.ponto.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.geral.pessoal.ColaboradorEntity;

public class ColaboradorConverter implements Converter<String, ColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ColaboradorEntity convertToModel(String value, Class<? extends ColaboradorEntity> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Verificar
		return null;
	}

	@Override
	public String convertToPresentation(ColaboradorEntity value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return value.getPessoa().getNome();
	}

	@Override
	public Class<ColaboradorEntity> getModelType() {
		// TODO Auto-generated method stub
		return ColaboradorEntity.class;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}

}
