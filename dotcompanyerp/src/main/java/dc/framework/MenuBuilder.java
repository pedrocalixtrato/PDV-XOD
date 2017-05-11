package dc.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sun.istack.logging.Logger;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

import dc.controller.adm.dotcompany.ParametroClienteListController;
import dc.controller.relatorio.RelatorioListController;
import dc.controller.sistema.SeguimentoListController;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.PapelMenu;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.servicos.dao.sistema.IPapelDAO;
import dc.visao.framework.FmMenuListController;
import dc.visao.framework.FmModuloListController;
import dc.visao.framework.geral.Controller;
import dc.visao.framework.geral.ControllerAcesso;
import dc.visao.framework.geral.MainController;
import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("prototype")
public class MenuBuilder implements Serializable {

	private static final long serialVersionUID = 894865014775813710L;
	public static final String MODULE_MENUBAR_MENUITEM = "module-menubar-menuitem";
	public static final String MODULE_MENU_BAR = "module-menubar";

	@Autowired
	private IFmMenuDAO dao;

	@Autowired
	private IPapelDAO daoPapel;

	private Logger logger = Logger.getLogger(MenuBuilder.class);

	public MenuItem createModuleMenuItem(MenuBar menubar, String text) {
		MenuItem item = menubar.addItem(text, null, null);
		item.setStyleName(MODULE_MENUBAR_MENUITEM);
		return item;
	}

	public HorizontalLayout buildMenuPanel(String moduleID, MainController mainController) {
		logger.info("Building menu for :" + moduleID);
		HorizontalLayout menuPanel = new HorizontalLayout();
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();

		final MenuBar menuBar = new MenuBar();
		menuBar.setAutoOpen(true);

		if (String.valueOf(FmModulo.ID_MODULO_ADM_DC).equals(moduleID)) {
			buildAdmSistemaMenu(menuPanel, menuBar, mainController);
		} else {
			buildMenu(menuBar, moduleID, menuPanel, mainController, usuario);
		}

		menuPanel.addStyleName(MODULE_MENU_BAR);

		return menuPanel;

	}

	private void buildMenu(MenuBar bar, String moduleID, HorizontalLayout menuPanel, MainController mainController, UsuarioEntity usuario) {
		@SuppressWarnings("unchecked")
		List<FmMenu> menusDoModulo = new ArrayList<FmMenu>();

		if (usuario.getAdministrador()) {
			menusDoModulo = dao.getAllMenusByModuleIdGrouped(Integer.valueOf(moduleID));
		} else {
			menusDoModulo = dao.getAllMenusByModuleIdGrouped(Integer.valueOf(moduleID), usuario.getId());
		}

		HashMap<Integer, HashMap<MenuItem, List<MenuItem>>> estruturaMenus = buildFirstPass(bar, menuPanel, menusDoModulo);
		buildSecondPass(bar, menuPanel, menusDoModulo, estruturaMenus, mainController, usuario, moduleID);
	}

	private void buildSecondPass(MenuBar bar, HorizontalLayout menuPanel, List<FmMenu> menusDoModulo,
			HashMap<Integer, HashMap<MenuItem, List<MenuItem>>> estruturaMenus, MainController mainController, UsuarioEntity u, String moduleID) {
		java.util.Iterator<FmMenu> it = menusDoModulo.iterator();
		while (it.hasNext()) {
			FmMenu m = it.next();
			logger.info("Menu item:" + m.getCaption() + " -> PARENT: " + m.getParentId());
			if (m.getParentId() != null) {
				addChild(estruturaMenus, m, mainController, u, moduleID);
			} else {
				logger.info("Should not pass on this, 2nd pass. No ROOT MENU should be parsed");
			}
		}
		logger.info("Menu items size: " + menusDoModulo.size());
		menuPanel.addComponent(bar);
	}

	private HashMap<Integer, HashMap<MenuItem, List<MenuItem>>> buildFirstPass(MenuBar bar, HorizontalLayout menuPanel, List<FmMenu> menusDoModulo) {
		HashMap<Integer, HashMap<MenuItem, List<MenuItem>>> estruturaMenus = new HashMap<Integer, HashMap<MenuItem, List<MenuItem>>>();
		java.util.Iterator<FmMenu> it = menusDoModulo.iterator();
		List<FmMenu> markForDelete = new ArrayList<FmMenu>();
		while (it.hasNext()) {
			FmMenu m = it.next();
			if (m.getParentId() == null) {
				logger.info("ROOT Menu item:" + m.getCaption());
				HashMap<MenuItem, List<MenuItem>> newItemsMap = new HashMap<MenuItem, List<MenuItem>>();
				ArrayList<MenuItem> newItems = new ArrayList<MenuItem>();
				MenuItem item = createModuleMenuItem(bar, m.getCaption());
				newItems.add(item);
				newItemsMap.put(item, newItems);
				estruturaMenus.put(m.getId(), newItemsMap);
				markForDelete.add(m);
			}
		}
		menusDoModulo.removeAll(markForDelete);
		return estruturaMenus;
	}

	private void addChild(HashMap<Integer, HashMap<MenuItem, List<MenuItem>>> estruturaMenus, FmMenu m, MainController mainController, UsuarioEntity u,
			String moduleID) {
		HashMap<MenuItem, List<MenuItem>> items = estruturaMenus.get(m.getParentId());

		if (items == null) {
			logger.info("No parent found for this menu:" + m.getCaption());
		} else {
			logger.info("Adding child for menu:" + m.getId());
			List<MenuItem> listItems = items.values().iterator().next();
			MenuItem parent = items.keySet().iterator().next();
			MenuItem newChild = createModuleMenuChildItemItem(parent, m, mainController, u, moduleID);
			listItems.add(newChild);

			logger.info("Adding as parent:" + m.getCaption());
			HashMap<MenuItem, List<MenuItem>> newItemsMap = new HashMap<MenuItem, List<MenuItem>>();
			ArrayList<MenuItem> newItems = new ArrayList<MenuItem>();
			newItems.add(newChild);
			newItemsMap.put(newChild, newItems);
			estruturaMenus.put(m.getId(), newItemsMap);
		}

	}

	private MenuItem createModuleMenuChildItemItem(MenuItem parent, final FmMenu m, final MainController main, final UsuarioEntity u, final String moduleID) {
		MenuItem item = parent.addItem(m.getCaption(), m.getCommand());

		item.setCommand(new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				long inicio = System.currentTimeMillis();
				buildContent(m, main, u, moduleID);
				long fim = System.currentTimeMillis();
				logger.info("------- Abrir a listagem demorou " + ((fim - inicio)/1000) + " segundos.");
			}

		});
		item.setStyleName(MODULE_MENUBAR_MENUITEM);
		return item;
	}

	public void buildContent(FmMenu m, MainController main, UsuarioEntity u, String moduleID) {
		Class<?> c;
		try {
			c = Class.forName(m.getControllerClass());
			dc.visao.framework.geral.Controller ctrl = (Controller) main.getEntityController(c);
			if (ctrl instanceof ControllerAcesso) {
				ControllerAcesso ctrlAcesso = (ControllerAcesso) ctrl;
				if (u.getAdministrador()) {
					ctrlAcesso.setAcessoLiberado();
				} else {
					PapelMenu pf = daoPapel.getPapelMenuByPapelAndMenuID(u.getPapel().getId(), m.getId());
					ctrlAcesso.setPapelMenu(pf);
				}

			}
			if (ctrl instanceof dc.visao.framework.geral.Task) {
				((dc.visao.framework.geral.Task) ctrl).setModuleId(moduleID);
				main.showTaskableContent((dc.visao.framework.geral.Task) ctrl);
			} else {
				// main.show
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void buildAdmSistemaMenu(HorizontalLayout menuPanel, final MenuBar menubar, final MainController mainController) {
		menuPanel.addComponent(menubar);

		/** Menu Administrativo @ Wesley Jr */

		MenuBar.MenuItem administrativo = this.createModuleMenuItem(menubar, "Administrativo");

		MenuBar.MenuItem parametroCliente = administrativo.addItem("Parâmetro Cliente", null, new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Controller c = (Controller) mainController.getEntityController(ParametroClienteListController.class);
				showControllerSistema(mainController, c);

			}
		});

		MenuBar.MenuItem cadastros = this.createModuleMenuItem(menubar, "Cadastros");

		MenuBar.MenuItem modulos = cadastros.addItem("Módulos", null, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Controller c = mainController.getEntityController(FmModuloListController.class);
				showControllerSistema(mainController, c);
			}
		});
		MenuBar.MenuItem menus = cadastros.addItem("Menus", null, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				dc.visao.framework.geral.Controller c = (Controller) mainController.getEntityController(FmMenuListController.class);
				showControllerSistema(mainController, c);

			}

		});

		MenuBar.MenuItem relatorios = cadastros.addItem("Relatórios", null, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				dc.visao.framework.geral.Controller c = (Controller) mainController.getEntityController(RelatorioListController.class);
				showControllerSistema(mainController, c);

			}

		});

		MenuBar.MenuItem seguimentos = cadastros.addItem("Seguimentos", null, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				dc.visao.framework.geral.Controller c = (Controller) mainController.getEntityController(SeguimentoListController.class);
				showControllerSistema(mainController, c);

			}

		});

	}

	private void showControllerSistema(final MainController mainController, dc.visao.framework.geral.Controller c) {
		if (c instanceof ControllerAcesso) {
			ControllerAcesso ctrlAcesso = (ControllerAcesso) c;
			ctrlAcesso.setAcessoLiberado();
		}

		if (c instanceof dc.visao.framework.geral.Task) {
			((dc.visao.framework.geral.Task) c).setModuleId(String.valueOf(FmModulo.ID_MODULO_ADM_DC));
			mainController.showTaskableContent((dc.visao.framework.geral.Task) c);
		} else {
			// main.show
		}
	};

}
