package dc.framework;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.control.enums.SimNaoEn;

public class SimNaoConverter implements Converter<Boolean,SimNaoEn>{
	
	private static final long serialVersionUID = -8753389649254738164L;



	@Override
	public Class<SimNaoEn> getModelType() {
		// TODO Auto-generated method stub
		return SimNaoEn.class;
	}

	@Override
	public Class<Boolean> getPresentationType() {
		// TODO Auto-generated method stub
		return Boolean.class;
	}

	@Override
	public SimNaoEn convertToModel(Boolean value,
			Class<? extends SimNaoEn> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		if( value != null && value.booleanValue() == true){
			return SimNaoEn.S;
		}else{
			return SimNaoEn.N;
		}
	}

	@Override
	public Boolean convertToPresentation(SimNaoEn value,
			Class<? extends Boolean> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		if(value != null && value == SimNaoEn.S){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}


}
