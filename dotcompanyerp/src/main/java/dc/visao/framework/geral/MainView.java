package dc.visao.framework.geral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.google.gwt.thirdparty.guava.common.base.Predicate;
import com.google.gwt.thirdparty.guava.common.collect.Collections2;
import com.sun.istack.logging.Logger;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.visao.framework.geral.fake.DashboardView;
import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("prototype")
public class MainView extends CssLayout implements View {

	private static final long serialVersionUID = -7867699976154389905L;
	CssLayout sideBarMenu = new CssLayout();
	CssLayout topMenu = new CssLayout();

	CssLayout bottomBar = new CssLayout();
	CssLayout content = new CssLayout();

	HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();
	protected HorizontalLayout tasksBar;
	protected MenuItem tasksMenuItem;
	private boolean barActive;
	private ShortcutListener moveToNextTaskShortcut;
	private List<MoveToIndexShortcutHandler> moveToIndexTaskShortcuts = new ArrayList<MoveToIndexShortcutHandler>();
	public boolean showed = false;
	private List<FmModulo> modules;
	public static Logger logger = Logger.getLogger(MainView.class);
	private boolean menuBarExpandida = true;

	public MainView() {
		this.addStyleName("root");
		this.setSizeFull();
		logger.info("Main view instantiated");
	}

	private void showHomeView() {
		hideMenu();
		inactivateBar();
		setContent((Component) new DashboardView());
	}

	private void buildSideBarMenu() {
		logger.info("sidebarmenu compostion. ");
		this.addComponent(new HorizontalLayout() {
			{
				setSizeFull();
				addStyleName("main-view");
				// setLocked(true);
				VerticalLayout leftRegion = leftRegion();
				leftRegion.addLayoutClickListener(new LayoutClickListener() {

					@Override
					public void layoutClick(LayoutClickEvent event) {
						// if (!menuBarExpandida) {
						// ((HorizontalSplitPanel) event.getComponent()
						// .getParent()).setSplitPosition(10.7f,
						// Sizeable.Unit.PERCENTAGE);
						// menuBarExpandida = true;
						// }
					}
				});
				// setFirstComponent(leftRegion);
				addComponent(leftRegion);

				VerticalLayout rightRegion = rightRegion();
				rightRegion.addLayoutClickListener(new LayoutClickListener() {

					@Override
					public void layoutClick(LayoutClickEvent event) {
						// if (menuBarExpandida) {
						// event.getComponent().setSizeFull();
						// ((HorizontalSplitPanel) event.getComponent()
						// .getParent()).setSplitPosition(0.5f,
						// Sizeable.Unit.PERCENTAGE);
						// menuBarExpandida = false;
						// }
					}
				});
				// setSecondComponent(rightRegion);
				// setSplitPosition(10.7f, Sizeable.Unit.PERCENTAGE);
				addComponent(rightRegion);
				setExpandRatio(rightRegion, 1);

			}

		});

		sideBarMenu.removeAllComponents();

		sideBarMenu.addStyleName("menu");
		sideBarMenu.setHeight("100%");

	}

	private VerticalLayout rightRegion() {
		VerticalLayout rightSide = new VerticalLayout() {

			{
				addComponent(topMenu);
				topMenu.setWidth("100%");
				// topMenu.setHeight("8%");
				topMenu.addStyleName("view-menu");
				topMenu.setVisible(false);
				setComponentAlignment(topMenu, Alignment.TOP_LEFT);

				content.setSizeFull();
				content.setStyleName("view-content");
				addComponent(content);
				setComponentAlignment(content, Alignment.TOP_CENTER);

				addComponent(bottomBar);
				// bottomBar.setWidth("100%");
				// bottomBar.setHeight("100%");
				bottomBar.addStyleName("view-bottom");

				HorizontalLayout tasksPanel = new HorizontalLayout();

				tasksPanel.addStyleName("taskbar");
				tasksPanel.setHeight("100%");
				tasksPanel.setWidth("100%");
				tasksBar = new HorizontalLayout();
				tasksBar.addStyleName("tasks-panel");
				tasksBar.setImmediate(true);
				tasksBar.setWidth("100%");
				// tasksPanel.addStyleName(MenuBuilder.MODULE_MENU_BAR);
				tasksPanel.addComponent(tasksBar);

				MenuBar barMenu = new MenuBar();
				barMenu.setImmediate(true);
				tasksMenuItem = barMenu.addItem("", new ThemeResource(
						"img/icone_lista_tarefas.png"), null);
				barMenu.setStyleName("taskbar-menu");

				tasksPanel.addComponent(barMenu);
				tasksPanel.setComponentAlignment(barMenu,
						Alignment.MIDDLE_RIGHT);

				tasksPanel.setExpandRatio(tasksBar, 1);

				bottomBar.addComponent(tasksPanel);
				setComponentAlignment(bottomBar, Alignment.TOP_CENTER);
				bottomBar.setVisible(false);

				bottomBar.setHeight("30px");

				// setExpandRatio(topMenu,(float) 4.2);
				// setExpandRatio(content,(float) 91.6);
				// setExpandRatio(bottomBar,(float) 4.2);

				setExpandRatio(topMenu, (float) 3.5);
				setExpandRatio(content, (float) 92.8);
			}
		};

		rightSide.setHeight("100%");
		return rightSide;
	}

	private VerticalLayout leftRegion() {
		return new VerticalLayout() {
			// Sidebar
			{
				addStyleName("sidebar");
				setWidth(null);
				setHeight("100%");

				CssLayout homeLinkLayout = new CssLayout();
				homeLinkLayout.addStyleName("branding");
				Label logo = new Label("<span>SampleCompany</span> ERP",
						ContentMode.HTML);
				logo.setSizeUndefined();
				homeLinkLayout.addComponent(logo);
				homeLinkLayout
						.addLayoutClickListener(new LayoutClickListener() {

							@Override
							public void layoutClick(LayoutClickEvent event) {
								if (event.getButton().equals(
										LayoutClickEvent.BUTTON_LEFT)) {
									MainUI ui = (MainUI) UI.getCurrent();
									if (ui != null) {
										Navigator nav = ui.getNavigator();
										if (nav != null) {
											nav.navigateTo("home");
										}
									}

								}
							}
						});
				addComponent(homeLinkLayout);

				// SidebarMenu

				// NanoScrollPanel nPanel = new NanoScrollPanel();
				// nPanel.setSizeFull();
				// nPanel.setWidth("100%");
				// nPanel.setHeight("40px");
				// nPanel.flashScrollbar();
				// nPanel.setPreventPageScrolling(true);
				// nPanel.setContent(sideBarMenu);
				addComponent(sideBarMenu);
				// addComponent(nPanel);
				setExpandRatio(sideBarMenu, 1);

				// User menu
				addComponent(new VerticalLayout() {
					{
						setSizeUndefined();
						addStyleName("user");
						Image profilePic = new Image(null, new ThemeResource(
								"img/profile-pic.png"));
						profilePic.setWidth("34px");
						addComponent(profilePic);

						Label userName = new Label("Usuario");
						UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
						if (usuario != null) {
							userName.setValue(usuario.getUsernome());
							userName.setSizeUndefined();
							addComponent(userName);
						}

						Command cmd = new Command() {
							@Override
							public void menuSelected(MenuItem selectedItem) {
								Notification
										.show("Not implemented in this demo");
							}
						};

						MenuBar settings = new MenuBar();
						MenuItem settingsMenu = settings.addItem("", null);
						settingsMenu.setStyleName("icon-cog");
						settingsMenu.addItem("Configurações", cmd);
						settingsMenu.addItem("Preferências", cmd);
						settingsMenu.addSeparator();
						settingsMenu.addItem("Minha conta", cmd);
						addComponent(settings);

						Button exit = new NativeButton("Sair");
						exit.addClickListener(new ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {
								MainUI mUI = (MainUI) UI.getCurrent();
								String logoutURL = mUI.getContextPath()
										+ "/j_spring_security_logout";
								getUI().getPage().setLocation(logoutURL);

								// Close the VaadinSession
								getSession().close();

							}
						});
						exit.addStyleName("icon-cancel");
						exit.setDescription("Sair");
						addComponent(exit);

					}
				});
			}
		};
	}

	private void clearMenuSelection() {
		for (Iterator<Component> it = sideBarMenu.getComponentIterator(); it
				.hasNext();) {
			Component next = it.next();
			if (next instanceof NativeButton) {
				next.removeStyleName("selected");
			}
		}
	}

	public void setContent(Component c) {
		c.setSizeFull();
		content.removeAllComponents();
		content.addComponent(c);
	}

	public void setTopMenu(Component m) {
		topMenu.removeAllComponents();
		topMenu.addComponent(m);
		if (!topMenu.isVisible()) {
			topMenu.setVisible(true);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		logger.info("entering main view");
		if (!showed) {
			showHomeView();
			buildSideBarMenu();
			showModulesMenu();
		}
		showed = true;
	}

	public void setModules(List<FmModulo> modules) {
		this.modules = modules;
	}

	public void showModulesMenu() {
		final Navigator nav = UI.getCurrent().getNavigator();
		criarComboboxFilter(nav);
		if (modules != null) {
			for (final FmModulo m : modules) {

				String name = m.getCaption();
				Button b = new NativeButton(name);

				b.addStyleName("icon-" + m.getUrlID());
				b.addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						clearMenuSelection();
						event.getButton().addStyleName("selected");

						if (!nav.getState().equals("/" + m.getUrlID()))
							nav.navigateTo("/" + m.getUrlID());
					}
				});

				sideBarMenu.addComponent(b);
				viewNameToMenuButton.put("/" + m.getUrlID(), b);
			}
		}
	}

	private void criarComboboxFilter(final Navigator nav) {

		BeanItemContainer<FmMenu> container = new BeanItemContainer<FmMenu>(
				FmMenu.class);

		Predicate<FmModulo> predicate = new Predicate<FmModulo>() {
			@Override
			public boolean apply(FmModulo modulo) {
				return modulo.getMenus() != null;
			}
		};
		if (modules != null) {
			for (FmModulo fmModulo : Collections2.filter(modules, predicate)) {
				container.addAll(fmModulo.getMenus());
				// for (FmMenu fmMenu : menus) {
				// container.addAll(fmMenu.getMenusFilho());
				// }
			}
		}
		
		final ComboBox menuFilter = new ComboBox("", container);
		menuFilter.setWidth("100%");
		menuFilter.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		menuFilter.setItemCaptionPropertyId("caption");
		menuFilter.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				Object itemId = event.getProperty().getValue();
				if (itemId == null) {
					return;
				}
				BeanItem<?> item = (BeanItem<?>) menuFilter.getItem(itemId);
				FmMenu menu = (FmMenu) item.getBean();

				if (!nav.getState().equals("/" + menu.getFmModulo().getUrlID())) {
					clearMenuSelection();
					Button button = viewNameToMenuButton.get("/"
							+ menu.getFmModulo().getUrlID());
					button.addStyleName("selected");
					nav.navigateTo("/" + menu.getFmModulo().getUrlID());
				}

				MainController mainController = (MainController) nav
						.getDisplay();

				UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
				mainController.getMenuBuilder().buildContent(menu,
						mainController, usuario,
						menu.getFmModulo().getId().toString());
				menuFilter.setValue(null);
			}
		});

		menuFilter.setFilteringMode(FilteringMode.CONTAINS);
		menuFilter.setImmediate(true);
		menuFilter.setNullSelectionAllowed(true);
		sideBarMenu.addComponent(menuFilter);
	}

	public void updateBar(List<Task> mainTasks, Collection<Task> nonMainTasks,
			final MainController mainController) {

		if (mainTasks.size() > 0) {
			bottomBar.setVisible(true);
		} else {
			bottomBar.setVisible(false);
		}

		tasksBar.removeAllComponents();
		tasksBar.setSizeFull();
		populateTaskBar(mainTasks, mainController);

		tasksMenuItem.removeChildren();
		MenuItem separator = tasksMenuItem.addSeparator();
		tasksMenuItem.addItem("Fechar todas", new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				mainController.closeAllTasks();
			}
		});

		if (nonMainTasks.size() > 0) {
			System.out.println("adding to taskmenu");
			for (final Task nTask : nonMainTasks) {
				System.out.println("added task to taskmenu");
				System.out.println(nTask);
				Command cmd = new Command() {

					@Override
					public void menuSelected(MenuItem selectedItem) {
						mainController.showTaskableContent(nTask);
						setTopMenu(mainController.getMenuBuilder()
								.buildMenuPanel(nTask.getModuleId(),
										mainController));
					}
				};
				tasksMenuItem.addItemBefore(nTask.getTaskCaption(), null, cmd,
						separator);

			}
		}

		if (mainTasks.size() < TaskContentManager.MAIN_TASKS_LIMT) {
			int emptySpaceCount = TaskContentManager.MAIN_TASKS_LIMT
					- mainTasks.size();
			System.out.println("EMPTY SPACE FILLING");
			System.out.println(emptySpaceCount);
			for (int i = 0; i < emptySpaceCount; i++) {
				HorizontalLayout emptyItem = new HorizontalLayout();
				tasksBar.addComponent(emptyItem);
			}
		}

	}

	private void populateTaskBar(List<Task> tasks,
			final MainController mainController) {

		for (final Task t : tasks) {
			final HorizontalLayout taskItem = new HorizontalLayout();
			Button fechar = new NativeButton("");
			fechar.setImmediate(true);
			fechar.setDescription("Fechar");

			fechar.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					mainController.removeTask(t, true);
				}
			});

			if (t.isActive() && barActive) {
				taskItem.setStyleName("taskbar-item-active");
				fechar.addStyleName("taskbar-close-active");
				prepareShortcutForNextTask(taskItem, t, tasks, mainController);
			} else {
				if (tasks.indexOf(t) == 0) {
					taskItem.setStyleName("taskbar-item-first");
				} else {
					taskItem.setStyleName("taskbar-item");
				}
				fechar.addStyleName("taskbar-close");
			}
			prepareShortcutIndexTask(tasks, mainController);

			HorizontalLayout taskItemDescripion = new HorizontalLayout();
			taskItemDescripion.setStyleName("taskbar-item-desc");
			Label itemLabel = new Label(t.getTaskCaption());
			itemLabel.setStyleName("taskbar-label");
			taskItemDescripion.addComponent(itemLabel);

			com.vaadin.event.LayoutEvents.LayoutClickListener listener = new com.vaadin.event.LayoutEvents.LayoutClickListener() {

				@Override
				public void layoutClick(LayoutClickEvent event) {

					if (event.getButton().equals(LayoutClickEvent.BUTTON_LEFT)) {
						System.out.println("CLICOU PARA ABRIR");
						mainController.showTaskableContent(t);
						setTopMenu(mainController
								.getMenuBuilder()
								.buildMenuPanel(t.getModuleId(), mainController));
					}
				}

			};

			taskItemDescripion.addLayoutClickListener(listener);
			taskItem.addComponent(taskItemDescripion);
			taskItem.addComponent(fechar);
			tasksBar.addComponent(taskItem);
		}
	}

	private void prepareShortcutIndexTask(List<Task> tasks,
			MainController mainController) {

		clearAllIndexesShortcuts();
		for (Task t : tasks) {
			TaskChangeAction taskChange = new TaskChangeAction(mainController,
					t);
			moveToIndexTaskShortcuts.add(new MoveToIndexShortcutHandler(
					"Move para tarefa", taskChange, tasks.indexOf(t)));
		}
		registerAllIndexesShortcuts();
	}

	private void registerAllIndexesShortcuts() {
		for (MoveToIndexShortcutHandler l : moveToIndexTaskShortcuts) {
			content.addShortcutListener(l.getShortCutListener());
		}
	}

	private void clearAllIndexesShortcuts() {
		for (MoveToIndexShortcutHandler l : moveToIndexTaskShortcuts) {
			content.removeShortcutListener(l.getShortCutListener());
		}
		moveToIndexTaskShortcuts = new ArrayList<MoveToIndexShortcutHandler>();
	}

	private void prepareShortcutForNextTask(HorizontalLayout taskItem,
			final Task t, final List<Task> tasks,
			final MainController mainController) {
		// TODO Auto-generated method stub
		content.removeShortcutListener(this.moveToNextTaskShortcut);
		this.moveToNextTaskShortcut = new ShortcutListener("Troca de tarefa",
				KeyCode.TAB, new int[] { ModifierKey.SHIFT }) {

			@Override
			public void handleAction(Object sender, Object target) {
				System.out.println("Next task shortcut....");
				int idx = tasks.indexOf(t);
				if (idx < tasks.size() - 1) {
					int nextIdx = idx + 1;
					changeTask(tasks, mainController, nextIdx);
				} else if (!tasks.isEmpty() && tasks.size() > 1) {
					int nextIdx = 0;
					changeTask(tasks, mainController, nextIdx);
				}

			}

			private void changeTask(final List<Task> tasks,
					final MainController mainController, int nextIdx) {
				Task next = tasks.get(nextIdx);
				new Notification("Alterando para tarefa: "
						+ next.getTaskCaption()).show(Page.getCurrent());
				mainController.showTaskableContent(next);
				setTopMenu(mainController.getMenuBuilder().buildMenuPanel(
						next.getModuleId(), mainController));
			}
		};
		content.addShortcutListener(this.moveToNextTaskShortcut);

	}

	public void inactivateBar() {
		this.barActive = false;
		// content.removeShortcutListener(listener)
	}

	public boolean isBarActive() {
		return this.barActive;
	}

	public void activateBar() {
		this.barActive = true;
	}

	public void hideMenu() {
		topMenu.setVisible(false);
	}

}