package dc.visao.framework.geral;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sun.istack.logging.Logger;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.framework.DcConstants;

@org.springframework.stereotype.Component
@Scope("prototype")
public class AlterarSenhaView extends ExternalView{


	public static final String PATH = "alterarSenha";
	
	public static Logger logger = Logger.getLogger(AlterarSenhaView.class);

    VerticalLayout loginLayout;
    CssLayout root = new CssLayout();
    
	Label error = new Label("",     ContentMode.HTML);

	private CssLayout panel;
	
	@Autowired
	public AlterarSenhaController controller;

	private String token = "";

	private String idConta;

	private DateTime dataLimite;

	private Button btnChangePassword;
	
	
	@PostConstruct
	public void init(){
		controller.setView(this);
		URI location = Page.getCurrent().getLocation();
		String query = location.getQuery();
		if(query != null){
			String[] qParams = query.split("token=");
			if(qParams.length >=2){
				Base64 encoder = new Base64();
				byte[] btokens = encoder.decode(qParams[1]);
				this.token = new String(btokens);
				String[] tokenParts = token.split("#");
				System.out.println(tokenParts);
				if(tokenParts.length >=2){
					this.idConta = tokenParts[0];
					String d = tokenParts[1];
					DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-YYYY");
					this.dataLimite  = DateTime.parse(d,fmt);
					
				}
				
			}
		}
		
		
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

	        Label bg = new Label();
	        bg.setSizeUndefined();
	        bg.addStyleName("login-bg");
	        root.addComponent(bg);

	        boolean invalidToken = false;
	        if(dataLimite.isBeforeNow() || idConta == null || idConta == ""){
	        	invalidToken = true;
	        }
	        buildCreateAccountView(false);
	        error.addStyleName("error");
			error.setSizeUndefined();
			error.addStyleName("light");
			error.addStyleName("v-animate-reveal");
			
			if(invalidToken){
				btnChangePassword.setDisableOnClick(true);
				showErrorMessage("Url Inválida. Peça novamente o recadastramento de senha via página \"Esqueci minha Senha\" ");
			}
		
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
        emailTextField.setCaption("Cadastra sua nova senha");
        
        final PasswordField pwd = new PasswordField("Nova senha");
        pwd.setRequired(true);
        pwd.focus();
        
        final PasswordField pwdConfirma = new PasswordField("Confirmar senha");
        pwdConfirma.setRequired(true);
        
        fields.addComponent(pwd);
        fields.addComponent(pwdConfirma);

        this.btnChangePassword = new Button("Alterar senha");
        btnChangePassword.addStyleName("default");
        fields.addComponent(btnChangePassword);
        fields.setComponentAlignment(btnChangePassword, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("ChangePwd",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                	btnChangePassword.click();
            }
        };

        btnChangePassword.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	if (validaNovaSenha(pwd.getValue(),pwdConfirma.getValue())){
            		controller.alteraSenha(idConta,pwd.getValue());
            	}
            	
            }


        });

        btnChangePassword.addShortcutListener(enter);
       

        panel.addComponent(fields);
        loginLayout.addComponent(panel);
        loginLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
	}

	
	private boolean validaNovaSenha(String value, String valueConfirm ) {
		if(value == null || "".equals(value.toString()) || valueConfirm == null || "".equals(valueConfirm) ){
			showErrorMessage("Senha em branco");
			return false;
		}else if(!value.equals(valueConfirm)){
			showErrorMessage(DcConstants.INVALID_DO_NOT_MATCH);
			return false;
		} 
		return true;
	}



	@Override
	protected ViewController getController() {
		return controller;
	}


	public void notifySaveOK() {
		// TODO Auto-generated method stub
		MainUI ui = (MainUI) MainUI.getCurrent();
		getUI().getPage().setLocation(ui.getContextPath());
		Notification notif = new Notification(
			    "OK!",
			    "Sua senha foi alterada. Faça o login no sistema usando sua nova senha.",
			    Notification.Type.HUMANIZED_MESSAGE);

		notif.setDelayMsec(4000);
		notif.show(Page.getCurrent());
	}


}
