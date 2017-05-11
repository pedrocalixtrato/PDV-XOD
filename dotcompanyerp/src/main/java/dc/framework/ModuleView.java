package dc.framework;


import com.sun.istack.logging.Logger;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.framework.FmModulo;
import dc.visao.framework.geral.MainController;

public abstract class ModuleView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String BLANK_VIEW = "BlankModuleView";
	private FmModulo module;
	private static Logger logger = Logger.getLogger(ModuleView.class);
	private MainController mainController;
	private boolean showed;
	private HorizontalLayout menuPanel;
	
	public ModuleView(FmModulo module,MainController controller){
		this.module = module;
		this.mainController = controller;
	}

	
	protected abstract void buildModuleViewLayout();

	@Override
	public void enter(ViewChangeEvent event) {
		if(!showed){
			init();
		}else{
			
		}
		showed = true;
		moduleViewEnter();
	}

	protected abstract void moduleViewEnter();

	public void init() {
		setSizeFull();
		logger.info("Init of abstract module: with id => " + module.getId());
		logger.info("main controller:" + mainController);
		createTopMenu();
		createBasicContent();
		
		buildModuleViewLayout();
	}


	private void createBasicContent() {
		HorizontalLayout toolbar = new HorizontalLayout();
		toolbar.setWidth("100%");
		toolbar.setSpacing(true);
		toolbar.setMargin(true);
		toolbar.addStyleName("toolbar");
		addComponent(toolbar);
		setExpandRatio(toolbar, (float) 95);

		Label title = new Label(module.getCaption());
		title.addStyleName("h1");
		title.setSizeUndefined();
		toolbar.addComponent(title);
		toolbar.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

	}


	public void createTopMenu() {
		this.menuPanel = mainController.getMenuBuilder().buildMenuPanel(String.valueOf(module.getId()),mainController);
	}

	public Component getTopMenu() {
		if(menuPanel == null){
			createTopMenu();
		}
		return this.menuPanel;
	}
	
	
	
	
	
}
