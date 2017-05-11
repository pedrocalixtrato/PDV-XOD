package dc.visao.sistema;

import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CustomFieldFactory extends DefaultFieldGroupFieldFactory {

	private static final long serialVersionUID = -265635931133107220L;

	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
		T t = super.createField(dataType, fieldType);
		if (t instanceof TextField){
			TextField txt = (TextField )t;
			txt.setNullRepresentation("");
		}else if (t instanceof TextArea){
			TextArea txt = (TextArea) t;
			txt.setNullRepresentation("");
		} else if (t instanceof PasswordField){
			PasswordField txt = (PasswordField) t;
			txt.setNullRepresentation("");
		}
		
		return t;
		
	}

}
