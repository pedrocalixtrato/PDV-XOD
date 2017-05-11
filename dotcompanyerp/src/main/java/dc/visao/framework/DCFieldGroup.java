package dc.visao.framework;

import javax.servlet.ServletContext;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;

/**
 * Created by cjalmeida on 14/07/15.
 */
public class DCFieldGroup<T> extends BeanFieldGroup<T> {

    private final Class<T> beanType;
    private final BeanDescriptor beanDescriptor;

    public DCFieldGroup(Class<T> beanType) {
        super(beanType);
        this.beanType = beanType;
        this.beanDescriptor = getValidator().getConstraintsForClass(beanType);
    }

    /**
     * Processa os campos adicionados aos FieldGroups. Atualmente:
     *  * Ajusta a representação de valor "null" nos TextFields para string vazia ("") e permite valores nulos.
     *  * Adiciona "setRequired" nos campos anotados com @NotNull
     *  * Ativa a validação somente ao salvar.
     *  * Verifica se precisa mudar o "tabsheet" ativo para exibir o campo se houver erro.
     */
    @Override
    protected void configureField(Field<?> field) {
        super.configureField(field);

        String propertyId = (String) getPropertyId(field);
        Class<?> fieldClass;
        try {
            fieldClass = beanType.getDeclaredField(propertyId).getType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


        PropertyDescriptor pd = beanDescriptor.getConstraintsForProperty(propertyId);
        if(pd != null) {
            for (ConstraintDescriptor<?> cd : pd.getConstraintDescriptors()) {
                if (cd.getAnnotation().annotationType().equals(NotNull.class)) {
                    field.setRequired(true);
                    field.setRequiredError(cd.getAttributes().get("message").toString());
                } else {
                    field.setRequired(false);
                }
            }
        } else {
            field.setRequired(false);
        }


        // Ajusta representação nula
        if (field instanceof TextField) {
            TextField textField = (TextField) field;
            textField.setNullRepresentation("");
            textField.setNullSettingAllowed(true);
        }

        // Desativa inicialmente a validação. Ativa no commit()
        // Atribui conversor ao tipo de retorno do campo no bean
        if (field instanceof AbstractField) {
            AbstractField<?> af = (AbstractField) field;
            af.setValidationVisible(false);
            af.setConverter(fieldClass);
        }

    }

    /**
     * Executa commit e ativa a validação dos campos.
     */
    @Override
    public void commit() throws CommitException {
        boolean focused = false;
        for (Field f : getFields()) {
            if (f instanceof AbstractField) {
                AbstractField abstractField = (AbstractField) f;
                abstractField.setValidationVisible(true);

                // Deixa o componente com erro visivel. Faz isso somente para o primeiro com erro.
                if (!focused && !f.isValid()) {
                    f.focus();
                    showTabbed(f);
                    focused = true;
                }
            }
        }
        super.commit();
    }

    /**
     * Deixa visivel os "tabs" se este campo estiver contido dentro de um ou mais TabSheets.
     */
    private void showTabbed(Field field) {
        HasComponents parent = field.getParent();
        Component previous = field;

        while (parent != null) {
            if (parent instanceof TabSheet) {
                TabSheet tabSheet = (TabSheet) parent;
                tabSheet.setSelectedTab(previous);
            }
            previous = parent;
            parent = parent.getParent();
        }
    }

    /**
     * Pega uma instancia do Bean Validator direto do Spring.
     */
    public Validator getValidator() {
        ServletContext webCtx = VaadinServlet.getCurrent().getServletContext();
        ApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(webCtx);
        return (Validator) appCtx.getBean("validator");
    }

}
