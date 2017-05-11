package dc.visao.financeiro.converters;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.geral.FornecedorDAO;

public class FornecedorConverter implements Converter<Object, FornecedorEntity> {

	@Autowired
	private FornecedorDAO dao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FornecedorEntity convertToModel(Object value, Class<? extends FornecedorEntity> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if(value== null){
			return null;
		}
		return null;
	}

	@Override
	public Object convertToPresentation(FornecedorEntity value, Class<? extends Object> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if(value== null){
			return "";
		}
		
		value = dao.find(value.getId());
		return value.getPessoa().getNome();
	}

	@Override
	public Class<FornecedorEntity> getModelType() {
		// TODO Auto-generated method stub
		return FornecedorEntity.class;
	}

	@Override
	public Class<Object> getPresentationType() {
		// TODO Auto-generated method stub
		return Object.class;
	}

}
