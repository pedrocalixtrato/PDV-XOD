package dc.visao.framework.geral;

import java.io.Serializable;
import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.sun.istack.logging.Logger;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("prototype")
public class WizardNovaContaView extends ExternalView implements View,Serializable {
	
	private static final long serialVersionUID = -3556275669516114733L;
	public static final String PATH = "wizard";

	public static Logger logger = Logger.getLogger(WizardNovaContaView.class);
    VerticalLayout mainLayout;
    CssLayout root = new CssLayout();

    @Autowired
	public ConfiguraNovaContaController controller;

    Label error = new Label("",     ContentMode.HTML);

	private CssLayout mainPanel;
	
	@PostConstruct
	public void init(){
		//controller.setView(this);
	}


	public void showErrorMessage(String message){
		error.setValue(message);
		mainPanel.addComponent(error);
	}


	@Override
	protected void initUI() {
		  	setSizeFull();
	        addComponent(root);
	        root.addStyleName("root");
	        root.setSizeFull();


	        Label bg = new Label();
	        bg.setSizeUndefined();
	        bg.addStyleName("login-bg");
	        root.addComponent(bg);

	        buildView(false);
	        
	        error.addStyleName("error");
			error.setSizeUndefined();
			error.addStyleName("light");
			error.addStyleName("v-animate-reveal");
		System.out.println("Wizard view init()");
		
	}

	private void buildView(boolean b) {
		String nomeUsuario = controller.getNomeUsuario(SecuritySessionProvider.getUsuario());
        addStyleName("login");

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.addStyleName("login-layout");
        root.addComponent(mainLayout);

        mainPanel = new CssLayout();
        mainPanel.addStyleName("login-panel");
        mainPanel.setWidth("80%");
        mainPanel.setHeight("80%");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        mainPanel.addComponent(labels);
        Label welcome = new Label("Olá " + nomeUsuario + ", vamos agora configurar o DotCompany ERP. Para isso responda as perguntas a seguir");
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
        
        
        final Wizard wizard = new Wizard();
        wizard.setHeight("90%");
        wizard.addStep(new WizardFirstStep(controller));
        wizard.addStep(new WizardSecondStep(controller));
        wizard.addStep(new WizardThirdStep(controller));
        wizard.addListener(new WizardProgressListener() {
			
			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				logger.info("Wizard complete");
				getUI().getPage().setLocation(VaadinURLUtils.getInitialURL());
				new Notification("Ok! Vamos começar a usar").show(Page.getCurrent());
				
			}
			
			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				logger.info("Wizard canceled..");
				getUI().getPage().setLocation(VaadinURLUtils.getInitialURL());
				new Notification("Ok! Até mais tarde").show(Page.getCurrent());
			}
			
			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				// TODO Auto-generated method stub
			}
		});

        wizard.getNextButton().setCaption("avançar");
        wizard.getBackButton().setCaption("Voltar");
        wizard.getFinishButton().setCaption("Finalizar");
        wizard.getCancelButton().setCaption("Cancelar");
        
     	mainPanel.addComponent(wizard);
        mainLayout.addComponent(mainPanel);
        mainLayout.setComponentAlignment(mainPanel, Alignment.MIDDLE_CENTER);
		
	}


	@Override
	protected ViewController getController() {
		return controller;
	}

}
