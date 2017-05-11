package dc.controller.sistema;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.FormLayout;

import dc.entidade.geral.pessoal.ColaboradorEntity;

public class ColaboradorField extends CustomField<ColaboradorEntity>{

	private static final long serialVersionUID = 1510031270980694979L;

	private BeanFieldGroup<ColaboradorEntity> binder = new BeanFieldGroup<>(ColaboradorEntity.class);

	
	@Override
	protected Component initContent() {
		FormLayout layout = new FormLayout();
		binder.setItemDataSource(this.getInternalValue());
		layout.addComponent(binder.buildAndBind("matricula"));
        return layout;
	}

	@Override
	public Class<? extends ColaboradorEntity> getType() {
		return ColaboradorEntity.class;
	}
	
	 
	

}
