package dc.visao.framework.geral;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
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
import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("prototype")
public class CriaContaEmpresaView extends ExternalView {

	private static final long serialVersionUID = -3556275669516114733L;

	private static final String MEDIUM_WIDTH = "30%";

	public static final String PATH = "criarconta";

	public static Logger logger = Logger.getLogger(CriaContaEmpresaView.class);

	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected transient AuthenticationManager authenticationManager;

	VerticalLayout loginLayout;
	CssLayout root = new CssLayout();

	@Autowired
	public CriaContaController controller;

	@Autowired
	public WizardNovaContaView nextView;

	Label error = new Label("", ContentMode.HTML);

	private BeanFieldGroup<ContaEmpresa> binder;

	private CssLayout loginPanel;

	@PostConstruct
	public void init() {
		controller.setView(this);
	}

	public void showErrorMessage(String message) {
		error.setValue(message);
		loginPanel.addComponent(error);
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

		System.out.println("CriaContaEmpresaView init()");
	}

	private void buildCreateAccountView(boolean b) {
		addStyleName("login");

		loginLayout = new VerticalLayout();
		loginLayout.setSizeFull();
		loginLayout.addStyleName("login-layout");
		root.addComponent(loginLayout);

		loginPanel = new CssLayout();
		loginPanel.addStyleName("login-panel");
		loginPanel.setWidth("90%");
		loginPanel.setHeight("90%");

		HorizontalLayout labels = new HorizontalLayout();
		labels.setWidth("100%");
		labels.setMargin(true);
		labels.addStyleName("labels");
		loginPanel.addComponent(labels);
		Label welcome = new Label(
				"Experimente gratuitamente, não é necessário informar dados para pagamento");
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

		binder = new BeanFieldGroup<ContaEmpresa>(ContaEmpresa.class);
		binder.setFieldFactory(new CustomFieldFactory());
		ContaEmpresa bean = controller.getCurrentBean();

		System.out.println(bean);

		binder.setItemDataSource(bean);

		TextField nomeTextField = (TextField) binder.buildAndBind("Seu nome",
				"nome");
		buildTxtField(fields, nomeTextField);

		TextField empresaTextField = (TextField) binder.buildAndBind(
				"Nome da sua Empresa", "empresa.nomeFantasia");
		buildTxtField(fields, empresaTextField);

		// modelo de mascara
		// 03.847.655/0001-98
		MaskedTextField cnpjMaskedField = new MaskedTextField("CNPJ",
				"##.###.###/####-##");
		cnpjMaskedField.setMaskClientOnly(true);
		binder.bind(cnpjMaskedField, "empresa.cnpj");
		buildMaskedTextField(fields, cnpjMaskedField);
		cnpjMaskedField.addValidator(new BeanValidator(EmpresaEntity.class,
				"cnpj"));

		TextField foneTextField = (TextField) binder.buildAndBind(
				"Seu telefone", "telefone");
		buildTxtField(fields, foneTextField);

		TextField emailTextField = (TextField) binder.buildAndBind("E-mail",
				"email");
		buildTxtField(fields, emailTextField);

		PasswordField pwdField = new PasswordField("Cadastre uma senha");
		binder.bind(pwdField, "usuarioCriador.senha");
		buildSenhaField(fields, pwdField);

		Link linkTermos = new Link("Clique aqui para ler os termos de uso",
				new ExternalResource(
						"http://www.dotcompanyerp.com.br/termos-de-uso/"));
		linkTermos.setTargetName("_blank");

		fields.addComponent(linkTermos);

		final CheckBox boxTermos = new CheckBox(
				"Li e concordo com os termos de uso");
		fields.addComponent(boxTermos);

		final Button btnCreateAccount = new Button("CRIAR CONTA");
		btnCreateAccount.addStyleName("default");
		fields.addComponent(btnCreateAccount);
		fields.setComponentAlignment(btnCreateAccount, Alignment.BOTTOM_LEFT);

		final ShortcutListener enter = new ShortcutListener("Enviar",
				KeyCode.ENTER, null) {

			@Override
			public void handleAction(Object sender, Object target) {
				btnCreateAccount.click();
			}

		};

		btnCreateAccount.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (boxTermos.getValue() == false) {
					showErrorMessage("Você deve aceitar os termos de uso para prosseguir na criação de conta");
				} else {
					try {
						binder.commit();
						ContaEmpresa c = binder.getItemDataSource().getBean();

						loginPanel.removeComponent(error);

						controller.criarconta(c);
					} catch (CommitException e) {
						// TODO Auto-generated catch block
						loginPanel.removeComponent(error);

						showErrorMessage("Verifique os campos preenchidos, corrija e tente novamente.");

						for (Field f : binder.getFields()) {
							((AbstractField) f).setValidationVisible(true);
						}

						e.printStackTrace();
					}
				}
			}

		});

		btnCreateAccount.addShortcutListener(enter);

		loginPanel.addComponent(fields);

		loginLayout.addComponent(loginPanel);
		loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
	}

	private void buildMaskedTextField(VerticalLayout fields,
			MaskedTextField cnpjField) {
		cnpjField.setRequired(true);
		cnpjField.setNullRepresentation("");
		cnpjField.setValidationVisible(false);
		cnpjField.setRequiredError("Não pode ficar em Branco!");
		cnpjField.setWidth(MEDIUM_WIDTH);
		fields.addComponent(cnpjField);
	}

	private void buildSenhaField(VerticalLayout fields,
			PasswordField senhaPwdField) {
		senhaPwdField.setNullRepresentation("");
		senhaPwdField.setRequired(true);
		senhaPwdField.setValidationVisible(false);
		senhaPwdField.setRequiredError("Não pode ficar em Branco!");
		senhaPwdField.setWidth(MEDIUM_WIDTH);
		fields.addComponent(senhaPwdField);
	}

	private void buildTxtField(VerticalLayout fields, TextField nomeTextField) {
		nomeTextField.setRequired(true);
		nomeTextField.setRequiredError("Não pode ficar em Branco!");
		nomeTextField.setValidationVisible(false);
		nomeTextField.setWidth(MEDIUM_WIDTH);
		fields.addComponent(nomeTextField);
	}

	public void notifySaveOK(ContaEmpresa novaConta) {
		logger.info("Save ok, wil navigate to wizard");

		MainUI ui = (MainUI) MainUI.getCurrent();
		Navigator nav = ui.getNavigator();

		if (nav == null) {
			nav = new Navigator(ui, this);
			nav.addView(WizardNovaContaView.PATH, nextView);
			ui.setNavigator(nav);
		}

		SecuritySessionProvider.putUsuarioInSession(
				novaConta.getUsuarioCriador(), authenticationManager);

		new Notification("Ok! Conta criada").show(Page.getCurrent());

		nav.navigateTo("wizard");
	}

	@Override
	protected ViewController getController() {
		// TODO Auto-generated method stub
		return controller;
	}

}