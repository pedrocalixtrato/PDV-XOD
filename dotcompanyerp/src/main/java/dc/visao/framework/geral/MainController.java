package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.istack.logging.Logger;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.framework.BlankModuleView;
import dc.framework.MenuBuilder;
import dc.framework.ModuleLoader;
import dc.framework.ModuleView;
import dc.framework.RoutesBuilder;
import dc.servicos.dao.sistema.IPapelDAO;
import dc.visao.framework.geral.fake.DashboardView;
import dc.visao.framework.geral.fake.TransactionsView;
import dc.visao.spring.SecuritySessionProvider;

/**
*
* @author Wesley Jr
/*
 * Nessa classe é a classe principal.
 * Essa classe seria como se fosse o DesktopApp, onde nela colocamos as informações do Menu Principal
 * Colocamos as Telas que queremos que abra no Browser na Tela Principal.
 * Tem o Modelo de implementação do Menu com as Telas.
 *
*/

@Controller
@Scope("session")
public class MainController implements ViewDisplay,Serializable {

	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger(MainController.class);
	
	@Autowired(required = true)
	private transient ApplicationContext applicationContext;
	
	@Autowired(required = true)
	private MainView view;
	
	private Navigator navigator;

	@Autowired
	private ModuleLoader moduleLoader;
	
	@Autowired
	private MenuBuilder builder;
	
	@Autowired
	private TaskContentManager contentManager;

	private List<FmModulo> modules = new ArrayList<FmModulo>();
	
	@Autowired
	private IPapelDAO papelDAO;

	@PostConstruct
	protected void init() {
		logger.info("maincontroller init");
	}

	public void showContent(Component content,boolean inactivateBar) {
		view.setContent(content);
		if(inactivateBar){
			if(view.isBarActive()){
				contentManager.inativateActiveTask();
				view.inactivateBar();
				view.updateBar(contentManager.getMainTasks(),contentManager.getNonMainTasks(), this);	
			}
		}else{
			view.activateBar();
		}
	}
	
	public void showMenu(Component menu) {
		view.setTopMenu(menu);	
	}
	
	public void hideMenu() {
		view.hideMenu();	
	}
	
	public Component getContent() {
		MainUI mUI = (MainUI) UI.getCurrent();
		logger.info(mUI.getPathInfo());
		return view;	
	}

	@Override
	public void showView(View view) {
		
		logger.info("show view");
		showingView();
		if (view instanceof ConfirmacaoContaView){
			UI.getCurrent().setContent((Component) view);
		}else{
			if(view instanceof ExternalView){
				UI.getCurrent().setContent((ExternalView) view);
			}else {
				UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
				if(usuario != null && !usuario.isConfirmado()){
					navigator.navigateTo("confirmacaoConta");
				}else if( view instanceof MainView){	
					if(this.modules == null || this.modules.isEmpty()){
						loadModulesAndRoutes(usuario);	
					}
					UI.getCurrent().setContent((Component) view);
				}else if (view instanceof ModuleView){
					ModuleView mView = (ModuleView) view;
					showMenu(mView.getTopMenu());
					showContent(mView,true);
				}else if(view instanceof TransactionsView){
					hideMenu();
					showContent((Component) view,true);
				}else if(view instanceof DashboardView){
					hideMenu();
					showContent((Component) view,true);
				}
				else{
					UI.getCurrent().setContent((Component) view);;	
				}
			}
		}	
	}

	private void loadModulesAndRoutes(UsuarioEntity u) {
		this.modules = moduleLoader.loadModules(u);
		RoutesBuilder routesBuilder = new RoutesBuilder();
		HashMap<String, View> routes = new HashMap<String, View>() ;
		routesBuilder.buildModuleRoutes(moduleLoader, routes, navigator,this);
		logger.info("should build modules and routes");
		((MainView)view).setModules(modules);
	} 
	 
	private void showingView() {
		contentManager.inativateActiveTask();
	}

	public dc.visao.framework.geral.Controller getEntityController(Class beanClass){
		final dc.visao.framework.geral.Controller controller = (dc.visao.framework.geral.Controller) applicationContext.getBean(beanClass);
		return controller;
	}

	void initNavigationMenu(MainUI ui) {
			Navigator uiNav = ui.getNavigator();
			if(uiNav != null){
				uiNav.removeView("");	
			}
			navigator = new Navigator(ui,this); 
	        navigator.addView("", view);
	        navigator.addView("home", new DashboardView());
	        
	        WizardNovaContaView viewWizard = applicationContext.getBean(WizardNovaContaView.class);
	        View criaContaView = applicationContext.getBean(CriaContaEmpresaView.class);
	        View confirmaContaView = applicationContext.getBean(ConfirmacaoContaView.class);
	        View perdiMinhaSenhaView = applicationContext.getBean(EsqueciMinhaSenhaView.class);
	        View alterarMinhaSenhaView = applicationContext.getBean(AlterarSenhaView.class);
	        
	        navigator.addView(ConfirmacaoContaView.PATH, confirmaContaView);
	        navigator.addView(CriaContaEmpresaView.PATH, criaContaView);
	        navigator.addView(WizardNovaContaView.PATH, viewWizard);
	        navigator.addView(EsqueciMinhaSenhaView.PATH, perdiMinhaSenhaView);
	        navigator.addView(AlterarSenhaView.PATH, alterarMinhaSenhaView);
	}

	public List<FmModulo> getModules() {
		return this.modules;
	}
	
	public MenuBuilder getMenuBuilder() {
		return builder;
	}

	
	public void removeTask(Task task) {
		contentManager.removeTask(task);
	}

	
	public void removeTask(Task task,boolean updateView) {
		contentManager.removeTask(task);
		if(task.isActive()){
			updateBarAfterRemoval(task);
		}else{
			if(updateView){
				view.updateBar(contentManager.getMainTasks(),contentManager.getNonMainTasks(),this);
			}	
		}
		
	}

	private void updateBarAfterRemoval(Task task) {
		if(contentManager.isEmpry()){
			view.updateBar(contentManager.getMainTasks(),contentManager.getNonMainTasks(),this);
			FmModulo module = null;
			if(task.getModuleId().equals(String.valueOf(FmModulo.ID_MODULO_ADM_DC))){
				module = FmModulo.loadSystemInstance();
			}else{
				module = moduleLoader.loadModule(Integer.valueOf(task.getModuleId()));
			}
			showView(new BlankModuleView(module, this));
		}else{
			Task newTask = contentManager.getActiveTask();
			showMenu(getMenuBuilder().buildMenuPanel(newTask.getModuleId(),this));
			showTaskableContent(newTask);	
		}
	}
	
	public void showTaskableContent(Task task) {
		Collection<dc.visao.framework.geral.Task> tasks = contentManager.getAllTasks();
		if(task.getParent()!= null){
			task.getParent().setActive(false);
			contentManager.removeTask(task.getParent());
		}
		
		dc.visao.framework.geral.Controller controller = task.getController();
		View taskView = controller.getView();
		boolean changed = false;
		if(taskView instanceof CRUDFormView){
			CRUDFormView v = (CRUDFormView) taskView;
			changed = contentManager.addOrActivate(task);
		}else if(taskView instanceof CRUDListView){
			CRUDListView v = (CRUDListView) taskView;
			changed = contentManager.addOrActivate(task);
		}else if (taskView instanceof BlankFormView )
		{
			changed = contentManager.addOrActivate(task);
		}
		
		if(changed){
			showMenu(builder.buildMenuPanel(task.getModuleId(), this));
			showContent((Component) taskView,false);
			view.updateBar(contentManager.getMainTasks(),contentManager.getNonMainTasks(),this);	
		}
	}

	public void closeAllTasks() {
		Task t = contentManager.getActiveTask();
		contentManager.removeAll();
		updateBarAfterRemoval(t);
	}

	public void setMenuBuilder(MenuBuilder builder2) {
		this.builder = builder2;
	}

	public void navigateTo(String p) {
		navigator.navigateTo(p);
	}

	public IPapelDAO getDaoPapel() {
		return papelDAO;
	}
	
	public FmMenu getMenu(String nomeClasse) {
		return papelDAO.getMenu(nomeClasse);
	}

}
