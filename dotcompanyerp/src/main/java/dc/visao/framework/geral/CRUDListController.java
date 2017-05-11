package dc.visao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.vaadin.addons.lazyquerycontainer.BeanQueryFactory;
import org.vaadin.addons.lazyquerycontainer.CompositeItem;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.LazyQueryView;
import org.vaadin.addons.lazyquerycontainer.NestingBeanItem;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.CustomTable.ColumnReorderEvent;
import com.vaadin.ui.CustomTable.ColumnReorderListener;
import com.vaadin.ui.CustomTable.ColumnResizeEvent;
import com.vaadin.ui.CustomTable.ColumnResizeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import dc.anotacoes.AnotacoesUtil;
import dc.anotacoes.Caption;
import dc.control.util.ClassUtils;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.PapelMenu;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.TipoRelatorio;
import dc.framework.DcConstants;
import dc.model.dao.AbstractDAO;
import dc.servicos.dao.framework.geral.GenericListDAO;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.servicos.dao.framework.geral.IFmModuloDAO;
import dc.servicos.dao.framework.geral.IListDAO;
import dc.servicos.dao.relatorio.IRelatorioDAO;
import dc.visao.framework.DCFilterDecorator;
import dc.visao.framework.DCFilterGenerator;
import dc.visao.framework.component.CompanyFileHandler;
import dc.visao.framework.component.SearchableCustomListTable;
import dc.visao.framework.component.manytoonecombo.ModalWindowSaveListener;
import dc.visao.framework.component.manytoonecombo.ModalWindowSelectionListener;
import dc.visao.spring.SecuritySessionProvider;

public abstract class CRUDListController<E extends AbstractModel> extends
		ControllerTask implements Controller, ControllerAcesso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger(CRUDListController.class);

	private static final int PAGE_SIZE = 100;
	public static final int WINDOW_LIST = 1;
	public static final int WINDOW_FORM = 2;

	protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	CRUDListView view;
	private SearchableCustomListTable table;

	private HashMap selected = new HashMap<Object, Object>();

	@Autowired
	private MainController mainController;

	CRUDFormController<?> formController;

	@Autowired
	private TaskContentManager task;

	@Autowired
	private IListDAO genericDAO;

	@Autowired
	private CompanyFileHandler fileUtils;

	@Autowired
	private IFmMenuDAO fmMenuDAO;

	@Autowired
	private IRelatorioDAO relatorioDAO;

	private PapelMenu papelMenu;
	private boolean acessoLiberado = false;

	private Window window = null;
	private ModalWindowSaveListener saveListener;
	private ModalWindowSelectionListener windowSelectionListener;
	@Autowired(required = true)
	private transient ApplicationContext applicationContext;

	@PostConstruct
	protected void init() {
		long inicio = System.currentTimeMillis();
		getFormController().setListController(this);

        genericDAO.setPojoClass(getEntityClass());
        genericDAO.setColunas(getColunas());
        view = new CRUDListView(this);
		
		this.menu = getMenu();

		// Titulo
		view.getLblNome().setValue(getTitulo());
		// view.getLblNome().setContentMode(ContentMode.HTML);

		// Botao de pesquisa
		view.getBtnPesquisa().addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (papelMenu != null) {
					if (DcConstants.BOOL_CHAR_TRUE.equals(papelMenu
							.getPodeConsultar())) {
						actionPesquisa();
					} else {
						getFormController().mensagemErro(
								DcConstants.PERMISSAO_NEGADA);
					}
				} else {
					if (acessoLiberado) {
						actionPesquisa();
					}
				}
			}
		});

		// Botao Criar (Novo)
		view.getBtnCriar().addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (papelMenu != null) {
					if (DcConstants.BOOL_CHAR_TRUE.equals(papelMenu
							.getPodeInserir())) {
						actionCriarNovo();
					} else {
						getFormController().mensagemErro(
								DcConstants.PERMISSAO_NEGADA);
					}
				} else {
					if (acessoLiberado) {
						actionCriarNovo();
					}
				}
			}
		});

		List<Relatorio> relatorios = relatorioDAO
				.findRelatoriosByMenuAndUserAndType(
						this.menu,
						SecuritySessionProvider.getUsuario(),
						TipoRelatorio.LISTAGEM);

		if (relatorios != null && relatorios.size() > 0) {
			RelatorioMenuBuilder builder = new RelatorioMenuBuilder();
			MenuBar relatorioMenu = builder.buildRelatorioMenu(relatorios,
					this, applicationContext);

			view.getHorizontalLayout_3().addComponent(relatorioMenu, 0);
		}

		ConfirmDialog.Factory df = new DefaultConfirmDialogFactory() {

            @Override
            public ConfirmDialog create(String caption, String message, String okCaption, String cancelCaption, String notOkCaption) {
                ConfirmDialog d = super.create(caption, message, okCaption,	cancelCaption, notOkCaption);
                d.setStyleName("dc-confirm-dialog");
                d.setWidth("35%");
                d.setHeight("20%");
                return d;
            }
        };

		ConfirmDialog.setFactory(df);

		// Botaao remover selecionados
		view.getBtnRemover().addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (papelMenu != null) {
					if (DcConstants.BOOL_CHAR_TRUE.equals(papelMenu
							.getPodeExcluir())) {
						actionRemoverSelecionados();
					} else {
						getFormController().mensagemErro(
								DcConstants.PERMISSAO_NEGADA);
					}
				} else {
					if (acessoLiberado) {
						actionRemoverSelecionados();
					}
				}
			}
		});

		permissaoOperacao();

		actionPesquisa();

		// Botao Fechar (Sair)

		/*
		 * private void populateTaskBar(List<Task> tasks, final MainController
		 * mainController) {
		 * 
		 * for (final Task t : tasks) { final HorizontalLayout taskItem = new
		 * HorizontalLayout(); Button fechar = new NativeButton("");
		 * fechar.setImmediate(true); fechar.setDescription("Fechar");
		 */

		/** Wesley Jr (FECHAR) */

		view.getBtnFechar().addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				mainController.removeTask((Task) getController(), true);

			}
		});
		long fim = System.currentTimeMillis();
		logger.info("A montagem da tela demorou " + ((fim - inicio)/1000) + " segundos.");
	}

	/*
	 * HorizontalLayout taskItemDescripion = new HorizontalLayout();
	 * taskItemDescripion.setStyleName("taskbar-item-desc"); Label itemLabel =
	 * new Label(t.getTaskCaption()); itemLabel.setStyleName("taskbar-label");
	 * taskItemDescripion.addComponent(itemLabel);
	 * 
	 * taskItem.addComponent(fechar); }
	 */

	protected void actionAbrir(Object object) {
		if (object == null) {
			getFormController().mensagemAtencao(
					"Escolha um registro para abrir");
		} else {
			Serializable id = (Serializable) object;

			if (isOnSeparateWindow()) {
				notifySelected((E) genericDAO.find(id));
			} else {
				mainController.showTaskableContent(getFormController());
				getFormController().mostrar(id);
			}
		}
	}

	protected void actionCriarNovo() {
		criaNovo();
		mainController.showTaskableContent(getFormController());
	}

	public void criaNovo() {
		getFormController().init();
		getFormController().criarNovo();
	}

	protected void actionRemoverSelecionados() {
		final List ids = Arrays.asList(selected.keySet().toArray());
		final List values = Arrays.asList(selected.values().toArray());

		if (ids.isEmpty()) {
			getFormController().mensagemAtencao(
					"Nenhum registro selecionado para remoção");
		} else {
			ConfirmDialog
			    .show(MainUI.getCurrent(),
					"Confirme a remoção",
					"Isso apagará os registros selecionados e Não poderá ser revertido.!\nDeseja continuar?",
					"Sim", "Não", new ConfirmDialog.Listener() {
			    	
			    	private static final long serialVersionUID = 1L;

								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										try {
											if (deletaEmCascata()) {
												getFormController()
														.removerEmCascata(
																values);
											} else {
												getFormController()
														.remover(ids);
											}

											LazyQueryContainer container = (LazyQueryContainer) table
													.getContainerDataSource();

											for (Object id : ids) {
												container.removeItem(id);
											}

											container.commit();
											container.refresh();
											selected.clear();
											table.refreshRowCache();
										} catch (Exception e) {
											logger.warning(e.getMessage());
											getFormController()
													.mensagemErro(
															"Houve um erro remover registro. Verifique se o mesmo Não tem dependência com outros registros.");
										}
									}
								}

							});

		}
		;
	}

	protected abstract CRUDFormController<E> getFormController();

	@SuppressWarnings("rawtypes")
	protected void actionPesquisa() {		
		String valor = view.getTxtPesquisa().getValue();

		table = new SearchableCustomListTable();

		if (menu.isConsultaFilterTable()) {
			table.setFilterDecorator(new DCFilterDecorator());
			table.setFilterGenerator(new DCFilterGenerator(this.getEntityClass()));
			table.setFilterBarVisible(true);
		}

		table.setFileHandler(fileUtils);
		table.setEntityName(getEntityClass().getSimpleName());

		// table.setHeight("99%");
		table.setEditable(false);
		table.setImmediate(true);
		table.setSelectable(true);
		// table.setDragMode(TableDragMode.)
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		table.setMultiSelect(false);
		table.setPageLength(PAGE_SIZE);

		table.addColumnReorderListener(new ColumnReorderListener() {
			@Override
			public void columnReorder(ColumnReorderEvent event) {
				logger.info("reorder");
				table.saveToFile();
			}
		});

		table.addColumnResizeListener(new ColumnResizeListener() {
			@Override
			public void columnResize(ColumnResizeEvent event) {
				logger.info("resize");
				table.saveToFile();
			}
		});

		table.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 2068314108919135281L;

			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					actionAbrir(event.getItemId());
				}
			}

		});

		/** Wesley Jr(Alterações CheckBox) NUMERAÇÃO */
		// adiciona checkbox na ultima coluna para marcar para acoes como ex:
		// remover
		// table.setRowHeaderMode(Table.ROW_HEADER_MODE_INDEX);

		/*
		 * table.addGeneratedColumn("mycolumnnumeric", new ColumnGenerator() {
		 * 
		 * //table.addGeneratedColumn(CustomListTable.CUSTOM_SELECT_ID, new
		 * ColumnGenerator() {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * public Object generateCell(final Table source, final Object itemId,
		 * final Object columnId) { final TextField tf = new TextField();
		 * tf.setImmediate(true); // source.setColumnCollapsed(itemId, true);
		 * 
		 * // table.setColumnCollapsed(tf, true); return tf; } });
		 */

		/*
		 * table.addGeneratedColumn(CustomListTable.CUSTOM_SELECT_ID, new
		 * ColumnGenerator() {
		 * 
		 * @Override public Component generateCell(final Table source, final
		 * Object itemId, final Object columnId) { final CompositeItem
		 * selectedBeanItem = (CompositeItem) source
		 * .getContainerDataSource().getItem(itemId); final NestingBeanItem
		 * nestedItem = (NestingBeanItem) selectedBeanItem .getItem("bean");
		 * 
		 * final CheckBox checkBox = new CheckBox(); // final TextField tf = new
		 * TextField();
		 * 
		 * checkBox.setImmediate(true); checkBox.addValueChangeListener(new
		 * Property.ValueChangeListener() {
		 * 
		 * @Override public void valueChange(
		 * com.vaadin.data.Property.ValueChangeEvent event) { Boolean select =
		 * (Boolean) event.getProperty() .getValue(); if (select) {
		 * selected.put(itemId, nestedItem.getBean()); } else {
		 * selected.remove(itemId); }
		 * 
		 * } });
		 * 
		 * checkBox.setValue(selected.containsKey(itemId));
		 * 
		 * return checkBox; } });
		 */

		table.addGeneratedColumn(SearchableCustomListTable.CUSTOM_SELECT_ID,
				new ColumnGenerator() {

					private static final long serialVersionUID = 1L;

					int i = 1;

					@Override
					public Component generateCell(CustomTable source,
							final Object itemId, Object columnId) {
						final CompositeItem selectedBeanItem = (CompositeItem) source
								.getContainerDataSource().getItem(itemId);
						final NestingBeanItem nestedItem = (NestingBeanItem) selectedBeanItem
								.getItem("bean");

						if (source.isFirstId(itemId)) {
							i = 1;
						}

						final CheckBox checkBox = new CheckBox();

						checkBox.setImmediate(true);
						// checkBox.setWidth(BorderLayout.EAST);
						checkBox.addValueChangeListener(new Property.ValueChangeListener() {
							@Override
							public void valueChange(
									com.vaadin.data.Property.ValueChangeEvent event) {
								Boolean select = (Boolean) event.getProperty()
										.getValue();
								if (select) {
									selected.put(itemId, nestedItem.getBean());
									// table.select(itemId);
								} else {
									selected.remove(itemId);
									// table.select(itemId);
								}
							}
						});

						checkBox.setValue(selected.containsKey(itemId));

						// Create the component for the generated column
						HorizontalLayout cellLayout = new HorizontalLayout();
						cellLayout.setSizeFull();
						cellLayout.addComponent(new Label(String.valueOf(i)));
						cellLayout.addComponent(checkBox);

						cellLayout.addStyleName("checkboxPanelOnTheMainScreen");

						i++;

						return cellLayout;
					}

				});

		long inicioBusca = System.currentTimeMillis();
		doSearch(valor);
		long fimBusca = System.currentTimeMillis();
		logger.info("-----------------------  A Busca demorou" + ((fimBusca - inicioBusca)/1000) + " segundos.");

		view.getVltTabela().removeAllComponents();
		view.getVltTabela().addComponent(table);
		table.setStyleName("sortTable");
		view.getPdfExporter().setTableToBeExported(table);
		view.getExcelExporter().setTableToBeExported(table);
		
	}

	public void doSearch(String valor) {
		try {
			if (valor == null) {
				valor = "";
			}

			selected.clear();
			table.setWidth("100%");
			table.setColumnWidth(SearchableCustomListTable.CUSTOM_SELECT_ID, 60);
			logger.info("valor pesquisado: " + valor);

			BeanQueryFactory queryFactory = null;

			if (genericDAO.isConsultaMultiEmpresa(getEntityClass(), this.menu, false)) {
				queryFactory = new BeanQueryFactory<DCBeanQueryMultiEmpresa>(
						DCBeanQueryMultiEmpresa.class);
			} else {
				queryFactory = new BeanQueryFactory<DCBeanQuery>(
						DCBeanQuery.class);
			}

			Map<String, Object> conf = new HashMap<String, Object>();
			Integer idEmpresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa().getId();
			conf.put("search", valor);
			//genericDAO.setPojoClass(getEntityClass());
			conf.put("dao", getMainDao());
			conf.put("pojoClass", getEntityClass());
			conf.put("id_empresa", idEmpresa);
			conf.put("menu", this.menu);
			queryFactory.setQueryConfiguration(conf);

			LazyQueryContainer container = new LazyQueryContainer(queryFactory,
					getBeanIdProperty(), PAGE_SIZE, true);
			container.getQueryView().getQueryDefinition().setMaxNestedPropertyDepth(3);

			for (String id_coluna : getColunas()) {
				if(id_coluna.indexOf(".")>0){
					container.addContainerProperty(id_coluna, String.class, "",
							true, true);
				}
				else if (getEntityClass().getDeclaredField(id_coluna).getType()
						.equals(Boolean.class)) {
					container.addContainerProperty(id_coluna, Boolean.class,
							false, true, true);
				}

				else if (getEntityClass().getDeclaredField(id_coluna).getType()
						.equals(Date.class)) {
					container.addContainerProperty(id_coluna, Date.class, null,
							true, true);
					
					/*table.setConverter(id_coluna, new StringToDateConverter(){
						@Override
						protected DateFormat getFormat(Locale locale) {
							return DATE_FORMAT;
						}
					});*/

				}
				

				else if (getEntityClass().getDeclaredField(id_coluna).getType()
						.equals(Double.class)) {
					container.addContainerProperty(id_coluna, Double.class,
							null, false, true);
					configureMoneyMask(id_coluna);
				}

				else if (getEntityClass().getDeclaredField(id_coluna).getType()
						.equals(BigDecimal.class)) {
					container.addContainerProperty(id_coluna, Double.class,
							null, false, true);
					
					configureMoneyMask(id_coluna);
				}

				else if (getEntityClass().getDeclaredField(id_coluna).getType()
						.equals(Integer.class)) {
					container.addContainerProperty(id_coluna, Integer.class,
							null, false, true);
				} else if (getEntityClass().getDeclaredField(id_coluna)
						.getType().equals(BigDecimal.class)) {
					container.addContainerProperty(id_coluna, Double.class,
							null, false, true);
				}

				else {
					container.addContainerProperty(id_coluna, String.class, "",
							true, true);
				}
			}

			container.addContainerProperty(
					LazyQueryView.DEBUG_PROPERTY_ID_QUERY_INDEX, Integer.class,
					0, true, false);
			container.addContainerProperty(
					LazyQueryView.DEBUG_PROPERTY_ID_BATCH_INDEX, Integer.class,
					0, true, false);
			container.addContainerProperty(
					LazyQueryView.DEBUG_PROPERTY_ID_BATCH_QUERY_TIME,
					Integer.class, 0, true, false);

			table.setSortEnabled(true);
			// table.markAsDirty();
			table.setSizeFull();
			table.setContainerDataSource(container);

			for (String prop : getColunas()) {
				Caption captionAnn = null;
				if(prop.indexOf("\\.") == 0){
					captionAnn = AnotacoesUtil.getAnotacao(Caption.class,
							getEntityClass(), prop);
				}else{
					captionAnn = AnotacoesUtil.getAnotacao(Caption.class,
							getEntityClass(), prop.split("\\.")[0]);
				}

				boolean existe = (captionAnn != null && !captionAnn.value()
						.equals("NULO"));

				if (existe)
					table.setColumnHeader(prop, captionAnn.value());
				else
					table.setColumnHeader(prop, prop);

				// verifica se e uma propriedade de um objeto dentro do bean
				if (prop.contains(".")) {
					//container.addNestedContainerProperty(prop);
				}

				if (prop.equals(SearchableCustomListTable.CUSTOM_SELECT_ID)) {
					table.setColumnExpandRatio(prop, 1);
				} else {
					table.setColumnExpandRatio(prop, 3);
				}
			}

			boolean loadedFromFile = table.loadFromFile();
			// boolean loadedFromFile = false;
			if (!loadedFromFile) {
				Object[] cs = new Object[] { SearchableCustomListTable.CUSTOM_SELECT_ID };
				Object[] allCollumns = ArrayUtils.addAll(cs, getColunas());
				table.setVisibleColumns(allCollumns);
			}

			if (getColunas() != null && getColunas().length > 1) {
				table.setFooterVisible(true);
				// table.setColumnFooter(SearchableCustomListTable.CUSTOM_SELECT_ID,
				// "Total: ");
				// table.setColumnFooter(getColunas()[0],
				// container.getItemIds().size() +
				// " registro(s) encontrado(s)");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			getFormController()
					.mensagemErro(
							"Ocorreu um erro na busca. Entre em contato com o administrador");
		}
	}

	private void configureMoneyMask(String id_coluna) throws IllegalAccessException, InvocationTargetException {
		NumberFormat anotacao = AnotacoesUtil.getAnotacao(NumberFormat.class,
				getEntityClass(), id_coluna);
		if(anotacao != null){
			for (Method method : anotacao.annotationType().getDeclaredMethods()) {
				Object value = method.invoke(anotacao, (Object[])null);
				if("style".equals(method.getName()) && value.equals(Style.CURRENCY)){
					if(table.getColumnGenerator(id_coluna) == null){
						table.addGeneratedColumn(id_coluna, new ValueColumnGenerator("R$ %.2f"));
					}
				}			              
		    }
		}
	}

	private boolean isConsultaMultiEmpresa() {
		return genericDAO.isMultiEmpresa(getEntityClass())
				&& SecuritySessionProvider.getUsuario()
						.getConsultaMultiempresa().equals(0);
	}

	protected AbstractDAO getMainDao() {
		return genericDAO;
	}
	protected String getBeanIdProperty() {
		return "id";
	}

	@Override
	public void setPapelMenu(PapelMenu papelMenu) {
		logger.info("papel menu setado");
		// logger.info(String.valueOf(papelMenu));
		this.papelMenu = papelMenu;
	}

	@Override
	public void setAcessoLiberado() {
		this.acessoLiberado = true;
	}

	public abstract String[] getColunas();

	public abstract Class<? super E> getEntityClass();

	protected abstract List<E> pesquisa(String valor);

	protected abstract List<E> pesquisaDefault();

	protected abstract String getTitulo();

	protected abstract boolean deletaEmCascata();

	@Override
	public View getView() {
		return view;
	}

	public void show(CRUDListView crudListView) {
		mainController.showTaskableContent(this);
	}

	public HashMap getSelected() {
		return selected;
	}

	public boolean autoriaAlteracao() {
		return acessoLiberado
				|| DcConstants.BOOL_CHAR_TRUE
						.equals(papelMenu.getPodeAlterar());
	}

	public boolean autoriaCriacao() {
		return acessoLiberado
				|| DcConstants.BOOL_CHAR_TRUE
						.equals(papelMenu.getPodeInserir());
	}

	@Override
	public String getControllerTitle() {
		return this.getTitulo();
	}

	@Override
	public Controller getController() {
		return this;
	}

	public void setReadOnly(boolean readonly) {
		view.getBtnCriar().setVisible(!readonly);
		view.getBtnRemover().setVisible(!readonly);
		view.getBtnFechar().setVisible(!readonly);
	}

	public void setEnabled(boolean enabled) {
		view.getBtnCriar().setEnabled(enabled);
		view.getBtnRemover().setEnabled(enabled);
		view.getBtnFechar().setEnabled(enabled);
	}

	public void closeWindow() {
		window.close();
		window = null;
	}

	public void openOnNewWindow(int modalSize, final int mode) {
		Component content = null;
		if (mode == WINDOW_FORM) {
			content = (Component) getFormController().getView();
		} else if (mode == WINDOW_LIST) {
			content = (Component) getView();
		}
		openOnNewWindow(modalSize, mode, content);
	}

	public void openOnNewWindow(int modalSize, final int mode, Component content) {
		window = new Window() {

			private static final long serialVersionUID = 1L;

			public void close() {
				if (mode == WINDOW_FORM) {
					if (getFormController().hasNewAttemptOpen()) {
						getFormController().confirmClose();
					} else {
						super.close();
					}
				} else {
					super.close();
				}
			}

		};

		window.setContent(content);

		window.center();

		if (modalSize != 1 && modalSize != 2) {
			window.setWidth("70%");
			window.setHeight("80%");
		} else if (modalSize == 1) {
			window.setWidth("100%");
			window.setHeight("100%");
		} else {
			window.setWidth("35%");
			window.setHeight("40%");
		}

		window.setModal(true);

		UI.getCurrent().addWindow(window);
	}

	public boolean isOnSeparateWindow() {
		return window != null;
	}

	public CRUDFormController<E> getPublicFormController() {
		return getFormController();
	}

	public void setSaveListener(ModalWindowSaveListener modalWindowSaveListener) {
		saveListener = modalWindowSaveListener;
	}

	public void setSelectionListener(ModalWindowSelectionListener listener) {
		windowSelectionListener = listener;
	}

	public CustomTable getTable() {
		return table;
	}

	public void notifySaved(E obj) {
		if (saveListener != null) {
			saveListener.onSave(obj);
		}
	}

	public void notifySelected(E obj) {
		if (windowSelectionListener != null) {
			windowSelectionListener.onSelect(obj);
			this.closeWindow();
		}
	}

	public void showOnWindow(Component c) {
		window.setContent(c);
	}

	@Override
	public void dispose() {
		// view = null;
		// table = null;
		// saveListener = null;
		// windowSelectionListener = null;
	}

	@Override
	public void setChildModuleID(String id) {
		getFormController().setModuleId(id);
	}

	/** VERIFICAR PERMISSÃO PARA SALVAR, CRIAR, REMOVER E EDITAR */

	@Autowired
	private IFmMenuDAO meDAO;

	@Autowired
	private IFmModuloDAO mDAO;

	private FmMenu menu;

	private void permissaoOperacao() {
		UsuarioEntity usuario = ClassUtils.getUsuario();

		if (!usuario.getLogin().equals(DcConstants.ADMIN_USERNAME)) {
			List<FmModulo> auxLista = this.mDAO.getModuloLista(usuario);

			List<FmMenu> auxLista1 = this.meDAO.getMenuLista(auxLista, this
					.getFormController().getListController().getClass()
					.getName());

			for (Object obj : auxLista1) {
				FmMenu menu = (FmMenu) obj;

				if (menu.getControllerClass().equals(this.getClass().getName())) {
					if (menu.getPermissaoOperacao().equals(1)) {
						this.getFormController().getListController()
								.setEnabled(false);
						this.getFormController().setEnabled(false);
					}

					break;
				}
			}
		}
	}

	public FmMenu getMenu() {
		return this.meDAO.getMenu(this.getClass().getName());
	}

	public IListDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(GenericListDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	public String getTitulo(CRUDListController clazz) {
		try {
			FmMenu menu = this.fmMenuDAO.getEntity(clazz.getClass().getName()
					.toString());

			return menu.getCaption();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	
	class ValueColumnGenerator implements  ColumnGenerator {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String format; /* Format string for the Double values. */

        public ValueColumnGenerator(String format) {
            this.format = format;
        }

	@SuppressWarnings("rawtypes")
	@Override
		public Object generateCell(CustomTable source, Object itemId, Object columnId) {
			Property prop = source.getItem(itemId).getItemProperty(columnId);
			if (prop.getType().equals(Double.class)) {
				Double value = (Double) prop.getValue();
				if (value != null) {
					Label label = new Label(String.format(format, new Object[] { value }));

					label.addStyleName("column-type-value");
					label.addStyleName("column-" + (String) columnId);
					return label;
				}
			} else if (prop.getType().equals(BigDecimal.class)) {
				BigDecimal value = (BigDecimal) prop.getValue();
				if (value != null) {
					Label label = new Label(String.format(format, new Object[] { ((BigDecimal) prop.getValue()).doubleValue() }));

					label.addStyleName("column-type-value");
					label.addStyleName("column-" + (String) columnId);

					return label;
				}
			}
			return null;
		}
}

}