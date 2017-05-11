package dc.visao.framework.component.manytoonecombo;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

import dc.model.dao.AbstractDAO;
import dc.visao.framework.geral.MainController;

/**
 * Created by cjalmeida on 14/07/15.
 */
public class ManyToOneComboField<T> extends CustomField<T> {

    private final ManyToOneCombo<T> combo;
    private final Class<T> beanType;

    public ManyToOneComboField(Class<T> beanType) {
        this.combo = new ManyToOneCombo<>();
        this.combo.addValueChangeListener((listener) -> setValue(this.combo.getValue()));
        this.beanType = beanType;
    }

    public void configuraCombo(String captionProperty,
                               Class crudListControllerClass,
                               AbstractDAO<T> crudDAO,
                               MainController mainController) {

        DefaultManyToOneComboModel<T> model = new DefaultManyToOneComboModel<T>(crudListControllerClass, crudDAO, mainController);
        model.setCaptionProperty(captionProperty);
        this.setModel(model);
    }

    public void setModel(ManyToOneComboModel<T> model) {
        this.combo.setModel(model);
    }

    public ManyToOneComboModel<T> getModel() {
        return this.combo.getModel();
    }


    @Override
    protected Component initContent() {
        return this.combo;
    }

    @Override
    public Class<? extends T> getType() {
        return this.beanType;
    }

    @Override
    protected void setInternalValue(T newValue) {
        super.setInternalValue(newValue);
        this.combo.setValue(newValue);
    }

    @Override
    public void addValueChangeListener(ValueChangeListener listener) {
        this.combo.addValueChangeListener(listener);
    }

}
