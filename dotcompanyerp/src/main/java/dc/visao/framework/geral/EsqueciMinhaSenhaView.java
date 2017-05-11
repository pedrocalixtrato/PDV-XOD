package dc.visao.framework.geral;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.sun.istack.logging.Logger;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.sistema.ContaEmpresa;
import dc.visao.sistema.CustomFieldFactory;

@org.springframework.stereotype.Component
@Scope("prototype")
public class EsqueciMinhaSenhaView extends ExternalView {
	
	private static final long serialVersionUID = -3556275669516114733L;

	public static final String PATH = "esqueciMinhaSenha";
	
	public static Logger logger = Logger.getLogger(EsqueciMinhaSenhaView.class);

    VerticalLayout loginLayout;
    CssLayout root = new CssLayout();
    
	Label error = new Label("",     ContentMode.HTML);

	private CssLayout panel;
	
	@Autowired
	public EsqueciMinhaSenhaController controller;
	
	
	@PostConstruct
	public void init(){
		controller.setView(this);
	}

	public void showErrorMessage(String message){
		error.setValue(message);
		panel.addComponent(error);
	}


	protected void initUI() {
			root = new CssLayout();
		  	setSizeFull();
	        addComponent(root);
	        root.addStyleName("root");
	        root.setSizeFull();

	        // Unfortunate to use an actual widget here, but since CSS generated
	        // elements can't be transitioned yet, we must
	        Label bg = new Label();
	        bg.setSizeUndefined();
	        bg.addStyleName("login-bg");
	        root.addComponent(bg);

	        buildCreateAccountView(false);
	        
	        error.addStyleName("error");
			error.setSizeUndefined();
			error.addStyleName("light");
			error.addStyleName("v-animate-reveal");
			System.out.println("ResetSenhaView init()");
		
	}

	private void buildCreateAccountView(boolean b) {
		 
        addStyleName("login");

        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        root.addComponent(loginLayout);

        panel = new CssLayout();
        panel.addStyleName("login-panel");
        panel.setWidth("80%");
        panel.setHeight("80%");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        panel.addComponent(labels);
        Label welcome = new Label("Esqueceu sua senha?");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

        Label title = new Label("DotCompany ERP");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
        
        VerticalLayout fields = new VerticalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        
        final TextField emailTextField = new TextField();
        emailTextField.setCaption("Digite o Endereço de e-mail utilizado para acesso ao DotCompanyERP");
        fields.addComponent(emailTextField);
;        
        final Button btnNewPassword = new Button("Envie-me instruções para cadastrar uma nova senha");
        btnNewPassword.addStyleName("default");
        fields.addComponent(btnNewPassword);
        fields.setComponentAlignment(btnNewPassword, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("ChangePwd",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                	btnNewPassword.click();
            }
        };

        btnNewPassword.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	if(emailTextField.getValue() == null || emailTextField.getValue().trim() == "" ){
            		showErrorMessage("Preencha o seu Endereço de e-mail para envio do link de nova senha");
            	}else{
            		controller.enviaEmail(emailTextField.getValue());
            	}
            }
        });

        btnNewPassword.addShortcutListener(enter);

        panel.addComponent(fields);
        loginLayout.addComponent(panel);
        loginLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
	}




	@Override
	protected ViewController getController() {
		return controller;
	}



	public void notifyMailOK() {
		Notification notif = new Notification(
			    "OK!",
			    "Se seu e-mail estiver cadastrado, Você receber� uma mensagem com o link para cadastrar sua nova senha.",
			    Notification.Type.HUMANIZED_MESSAGE);

		notif.setDelayMsec(4000);
		notif.show(Page.getCurrent());
	}

}
