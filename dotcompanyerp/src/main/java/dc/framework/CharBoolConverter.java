package dc.framework;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class CharBoolConverter implements Converter<Boolean,Character>{
	
	private static final long serialVersionUID = -8753389649254738164L;



	@Override
	public Class<Character> getModelType() {
		// TODO Auto-generated method stub
		return Character.class;
	}

	@Override
	public Class<Boolean> getPresentationType() {
		// TODO Auto-generated method stub
		return Boolean.class;
	}

	@Override
	public Character convertToModel(Boolean value,
			Class<? extends Character> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		if( value != null && value.booleanValue() == true){
			return 'S';
		}else{
			return 'N';
		}
	}

	@Override
	public Boolean convertToPresentation(Character value,
			Class<? extends Boolean> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		if(value != null && value == 'S'){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}


}
