package dc.visao.framework.geral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;

import com.sun.istack.logging.Logger;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.framework.DcConstants;
import dc.model.dao.administrativo.seguranca.IUsuarioDAO;
import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("prototype")
public class ConfirmacaoContaView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = -3556275669516114733L;

	public static final String PATH = "confirmacaoConta";
	
	public static Logger logger = Logger.getLogger(ConfirmacaoContaView.class);

	Panel panel;

    VerticalLayout loginLayout;
    CssLayout root = new CssLayout();
    
    @Autowired
    private IUsuarioDAO dao; 

	private boolean showed;

	private String errorMessage;
	
	Label error = new Label("",     ContentMode.HTML);
	
	 @Autowired
	 @Qualifier("org.springframework.security.authenticationManager")
	 protected transient AuthenticationManager authenticationManager;

	@Override
	public void enter(ViewChangeEvent event) {
		errorMessage = "";
		error.setValue(errorMessage);
		if(!showed){
			init();
		}else{
			
		}
		showed = true;
		viewEnter();
	        
	}


	private void viewEnter() {
		logger.info("view enter confirmacao conta");
	}


	private void init() {
		// TODO Auto-generated method stub

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

	        buildLoginView(false);
	        
	        error.addStyleName("error");
			error.setSizeUndefined();
			error.addStyleName("light");
			error.addStyleName("v-animate-reveal");
		System.out.println("ConfirmacaoContaView");
		
	}

	private void buildLoginView(boolean b) {
		final UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		 
        addStyleName("login");

        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        root.addComponent(loginLayout);

        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");
        loginPanel.setWidth("50%");
        loginPanel.setHeight("50%");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Bem-vindo, " + usuario.getUsernome());
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
        
        HorizontalLayout explanationaArea = new HorizontalLayout();
        explanationaArea.setWidth("100%");
        explanationaArea.setMargin(true);
        explanationaArea.addStyleName("labels");
        loginPanel.addComponent(explanationaArea);
        
        Label expText = new Label("Esse é seu primeiro login e sua conta ainda não está confirmada. Por favor, altere a sua senha para realizar a confirmação");
        //expText.setSizeUndefined();
        
        explanationaArea.addComponent(expText);
        explanationaArea.setComponentAlignment(expText, Alignment.MIDDLE_LEFT);
        
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final PasswordField pwd = new PasswordField("Nova senha");
        pwd.focus();
        fields.addComponent(pwd);

        final PasswordField confirmPwd = new PasswordField("Confirme a nova senha");
        fields.addComponent(confirmPwd);

        final Button btnChangePwd = new Button("OK");
        btnChangePwd.addStyleName("default");
        fields.addComponent(btnChangePwd);
        fields.setComponentAlignment(btnChangePwd, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("ChangePwd",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                btnChangePwd.click();
            }
        };

        btnChangePwd.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	errorMessage = "";
            	error.setValue(errorMessage);
            	if(loginPanel.getComponentIndex(error) > 0){
            		loginPanel.removeComponent(error);
            	}
            	
            	if(!validaNovaSenha(pwd.getValue(),confirmPwd.getValue(),usuario.getPassword())){
            		showErrorMessage(loginPanel, pwd, btnChangePwd, enter,errorMessage);
            	} else {
                   usuario.setConfirmado(true);
                   usuario.setSenha(pwd.getValue());
                   try{ 
                	   dao.saveOrUpdate(usuario);
                	   logger.info("cadastro atualizado, vai navegar para main view");
                	   SecuritySessionProvider.putUsuarioInSession(usuario, authenticationManager);
                	   UI.getCurrent().getNavigator().navigateTo("");
                   }catch (Exception e) {
                	   errorMessage = DcConstants.UPDATE_ACCOUNT_GENERIC_ERROR;
                	   showErrorMessage(loginPanel, pwd, btnChangePwd, enter,errorMessage);
                	   e.printStackTrace();
                   }
                }
            }

			private void showErrorMessage(final CssLayout loginPanel,
					final PasswordField pwd, final Button btnChangePwd,
					final ShortcutListener enter,String message) {
				btnChangePwd.removeShortcutListener(enter);
				if(loginPanel.getComponentIndex(error) < 0)
					error.setValue(message);{
					loginPanel.addComponent(error);	
				}
				pwd.focus();
			}

			
        });

        btnChangePwd.addShortcutListener(enter);

        loginPanel.addComponent(fields);

        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		
	}
	private boolean validaNovaSenha(String value, String valueConfirm,String senhaAtual) {
		if(value == null || "".equals(value.toString()) || valueConfirm == null || "".equals(valueConfirm) ){
			this.errorMessage = DcConstants.INVALID_EMPTY;
			logger.info("erro, senha em branco");
			return false;
		}else if(!value.equals(valueConfirm)){
			logger.info("erro, senha nao confere com confirmacao");
			this.errorMessage = DcConstants.INVALID_DO_NOT_MATCH;
			return false;
		} else if(value.equals(senhaAtual)){
			logger.info("erro, senha nova é igual a atual");
			this.errorMessage = DcConstants.INVALID_SAME_OLD_PWD;
			return false;
		}
		return true;
	}

}
