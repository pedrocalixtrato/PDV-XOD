package dc.visao.sistema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.SimNaoEn;
import dc.control.util.ObjectUtils;
import dc.controller.sistema.SeguimentoFormController;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.PapelMenu;
import dc.entidade.framework.SeguimentoEntity;

public class SeguimentoFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Object NAME_PROPERTY = "nome";

	private ComboBox comboModulos;

	private HorizontalLayout linhaMenus;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	private BeanFieldGroup<SeguimentoEntity> binder;

	private TextArea descricao;

	private HorizontalLayout linhaInicial;

	private TextField txtNome;

	private TreeTable treeTableSeguimentoMenu;

	private VerticalLayout vltTabela;

	private SeguimentoFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */

	private static Logger logger = Logger.getLogger(SeguimentoFormView.class.getName());

	public SeguimentoFormView(final SeguimentoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = controller;

		linhaMenus = new HorizontalLayout();
		linhaMenus.setSpacing(true);

		linhaInicial = new HorizontalLayout();
		linhaInicial.setSpacing(true);

		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		binder = new BeanFieldGroup<SeguimentoEntity>(SeguimentoEntity.class);
		binder.setItemDataSource(controller.getCurrentBean());
		binder.setFieldFactory(new CustomFieldFactory());

		this.txtNome = (TextField) binder.buildAndBind("Nome", "nome");
		txtNome.setWidth("100%");
		linhaInicial.addComponent(txtNome);

		mainLayout.addComponent(linhaInicial);

		descricao = new TextArea();
		descricao.setCaption("Descrição");
		descricao.setNullRepresentation("");
		descricao.setWidth("100%");
		descricao.setRows(2);
		binder.bind(descricao, "descricao");
		mainLayout.addComponent(descricao);

		buildTable(controller.getCurrentBean());

		this.comboModulos = new ComboBox("Módulo", new ArrayList<>());
		this.comboModulos.setItemCaptionPropertyId("caption");
		this.comboModulos.setNullSelectionAllowed(false);

		Button adicionaModuloBTN = new Button("Adicionar");
		adicionaModuloBTN.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FmModulo modulo = (FmModulo) comboModulos.getValue();

				if (modulo != null
						&& !treeTableSeguimentoMenu.containsId(modulo.getCaption())) {
					if (modulo != null) {
						controller.loadModules(modulo);
					}
				}
			}

		});

		Button removeMenuBTN = new Button("Remover");
		removeMenuBTN.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FmModulo modulo = (FmModulo) comboModulos.getValue();

				if (modulo != null
						&& treeTableSeguimentoMenu.containsId(modulo.getCaption())) {
					HierarchicalContainer container = (HierarchicalContainer) treeTableSeguimentoMenu
							.getContainerDataSource();
					container.removeItemRecursively(modulo.getCaption());
				}
			}

		});

		linhaMenus.addComponent(comboModulos);
		linhaMenus.addComponent(adicionaModuloBTN);
		linhaMenus.addComponent(removeMenuBTN);
		linhaMenus.setComponentAlignment(comboModulos, Alignment.BOTTOM_RIGHT);
		linhaMenus.setComponentAlignment(adicionaModuloBTN,
				Alignment.BOTTOM_RIGHT);
		linhaMenus.setComponentAlignment(removeMenuBTN, Alignment.BOTTOM_RIGHT);

		mainLayout.addComponent(linhaMenus);

		vltTabela = new VerticalLayout();
		vltTabela.setImmediate(false);
		vltTabela.setSizeFull();
		vltTabela.setMargin(false);
		vltTabela.setSpacing(true);
		vltTabela.addComponent(treeTableSeguimentoMenu);
		mainLayout.addComponent(vltTabela);

		// TODO: alternativa para ocupar a tela toda.
		mainLayout.setExpandRatio(vltTabela, 1);
	}

	public void buildTable(SeguimentoEntity p) {
		treeTableSeguimentoMenu = new TreeTable();
		treeTableSeguimentoMenu.setSizeFull();
		treeTableSeguimentoMenu.setColumnCollapsingAllowed(true);
		treeTableSeguimentoMenu.setImmediate(true);
		treeTableSeguimentoMenu.setTableFieldFactory(new CustomTableFieldFactory(
				true));
		treeTableSeguimentoMenu.setEditable(true);
		treeTableSeguimentoMenu.addContainerProperty(NAME_PROPERTY, String.class,
				"");
		treeTableSeguimentoMenu.addContainerProperty(
				CustomTableFieldFactory.INSERE_PROPERTY, Character.class, null);
		treeTableSeguimentoMenu.addContainerProperty(
				CustomTableFieldFactory.ALTERA_PROPERTY, Character.class, null);
		treeTableSeguimentoMenu.addContainerProperty(
				CustomTableFieldFactory.CONSULTA_PROPERTY, Character.class,
				null);
		treeTableSeguimentoMenu.addContainerProperty(
				CustomTableFieldFactory.EXCLUI_PROPERTY, Character.class, null);
		treeTableSeguimentoMenu.addContainerProperty(
				CustomTableFieldFactory.HABILITA_PROPERTY, Character.class,
				null);
		treeTableSeguimentoMenu.addContainerProperty(
				CustomTableFieldFactory.OBJ_PROPERTY, PapelMenu.class, null);
		treeTableSeguimentoMenu.setVisibleColumns(new Object[] { NAME_PROPERTY,
				CustomTableFieldFactory.INSERE_PROPERTY,
				CustomTableFieldFactory.ALTERA_PROPERTY,
				CustomTableFieldFactory.CONSULTA_PROPERTY,
				CustomTableFieldFactory.EXCLUI_PROPERTY,
				CustomTableFieldFactory.HABILITA_PROPERTY,
				CustomTableFieldFactory.OBJ_PROPERTY });
		treeTableSeguimentoMenu.setColumnCollapsed(
				CustomTableFieldFactory.OBJ_PROPERTY, true);
		treeTableSeguimentoMenu.setColumnHeader(NAME_PROPERTY, "Menu");
		treeTableSeguimentoMenu.setColumnHeader(
				CustomTableFieldFactory.INSERE_PROPERTY, "Pode inserir");
		treeTableSeguimentoMenu.setColumnHeader(
				CustomTableFieldFactory.ALTERA_PROPERTY, "Pode alterar");
		treeTableSeguimentoMenu.setColumnHeader(
				CustomTableFieldFactory.CONSULTA_PROPERTY, "Pode consultar");
		treeTableSeguimentoMenu.setColumnHeader(
				CustomTableFieldFactory.EXCLUI_PROPERTY, "Pode excluir");
		treeTableSeguimentoMenu.setColumnHeader(
				CustomTableFieldFactory.HABILITA_PROPERTY, "Habilitado");
		treeTableSeguimentoMenu.setColumnExpandRatio(NAME_PROPERTY, 2.0f);
		treeTableSeguimentoMenu.setColumnExpandRatio(
				CustomTableFieldFactory.INSERE_PROPERTY, 1.0f);
		treeTableSeguimentoMenu.setColumnExpandRatio(
				CustomTableFieldFactory.ALTERA_PROPERTY, 1.0f);
		treeTableSeguimentoMenu.setColumnExpandRatio(
				CustomTableFieldFactory.CONSULTA_PROPERTY, 1.0f);
		treeTableSeguimentoMenu.setColumnExpandRatio(
				CustomTableFieldFactory.EXCLUI_PROPERTY, 1.0f);
		treeTableSeguimentoMenu.setColumnExpandRatio(
				CustomTableFieldFactory.HABILITA_PROPERTY, 1.0f);
		treeTableSeguimentoMenu.setColumnExpandRatio(
				CustomTableFieldFactory.OBJ_PROPERTY, 0);
		treeTableSeguimentoMenu.setCollapsed(CustomTableFieldFactory.OBJ_PROPERTY,
				true);
		treeTableSeguimentoMenu.addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(final ValueChangeEvent event) {

			}

		});
	}

	public void populaPapelMenu(List<PapelMenu> pms) {
		CustomTableFieldFactory f = (CustomTableFieldFactory) treeTableSeguimentoMenu
				.getTableFieldFactory();
		f.setCheckChanges(false);
		treeTableSeguimentoMenu.removeAllItems();
		treeTableSeguimentoMenu.getContainerDataSource().removeAllItems();

		for (PapelMenu pm : pms) {
			FmModulo modulo = pm.getMenu().getFmModulo();
			addToTree(pm, pm.getMenu(), modulo);
		}

		f.setCheckChanges(true);
	}

	private void addToTree(PapelMenu pm, FmMenu menu, FmModulo modulo) {
		Object moduloId = addModuloToTree(modulo);

		if (menu.getParentId() == null) {
			addModuloChild(moduloId, menu, pm);
		} else {
			addMenuChild(menu, pm);
		}
	}

	public void populaModulos(List<FmModulo> modulos) {
		BeanItemContainer<FmModulo> objects = new BeanItemContainer(
				FmModulo.class, modulos);
		this.comboModulos.setContainerDataSource(objects);
	}

	public BeanFieldGroup<SeguimentoEntity> getBinder() {
		return this.binder;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		return mainLayout;
	}

	public List<PapelMenu> getPapelMenus() {
		ArrayList<PapelMenu> papeisMenu = new ArrayList<PapelMenu>();

		for (Iterator i = treeTableSeguimentoMenu.getItemIds().iterator(); i
				.hasNext();) {
			Object iid = i.next();

			if (iid instanceof Integer) {
				FmMenu fm = new FmMenu((Integer) iid);

				Item data = treeTableSeguimentoMenu.getContainerDataSource()
						.getItem(iid);

				populatePapelMenuLis(papeisMenu, data, fm);
			} else if (iid instanceof FmModulo) {
				FmModulo fmModulo = (FmModulo) iid;

				Item data = treeTableSeguimentoMenu.getContainerDataSource()
						.getItem(iid);

				List<FmMenu> menus = controller.getFmMenus(fmModulo);

				for (FmMenu fm : menus) {
					populatePapelMenuLis(papeisMenu, data, fm);
				}
			}
		}

		return papeisMenu;
	}

	private void populatePapelMenuLis(final ArrayList<PapelMenu> papeisMenu,
			Item data, FmMenu fm) {
		Object pmValue = data.getItemProperty(
				CustomTableFieldFactory.OBJ_PROPERTY).getValue();
		PapelMenu pm = new PapelMenu(fm, binder.getItemDataSource().getBean());

		if (ObjectUtils.isNotBlank(pmValue)) {
			pm = (PapelMenu) pmValue;
			pm.setSeguimento(binder.getItemDataSource().getBean());
		}

		Object pValue = data.getItemProperty(
				CustomTableFieldFactory.ALTERA_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue)) {
			Character altera = (Character) pValue;
			pm.setPodeAlterar(altera.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeAlterar(SimNaoEn.N);
		}

		Object pValue2 = data.getItemProperty(
				CustomTableFieldFactory.INSERE_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue2)) {
			Character insere = (Character) pValue2;
			pm.setPodeInserir(insere.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeInserir(SimNaoEn.N);
		}

		Object pValue3 = data.getItemProperty(
				CustomTableFieldFactory.CONSULTA_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue3)) {
			Character consulta = (Character) pValue3;
			pm.setPodeConsultar(consulta.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeConsultar(SimNaoEn.N);
		}

		Object pValue4 = data.getItemProperty(
				CustomTableFieldFactory.EXCLUI_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue4)) {
			Character exclui = (Character) pValue4;
			pm.setPodeExcluir(exclui.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeExcluir(SimNaoEn.N);
		}

		Object pValue5 = data.getItemProperty(
				CustomTableFieldFactory.HABILITA_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue5)) {
			Character habilitado = (Character) pValue5;
			pm.setHabilitado(habilitado.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setHabilitado(SimNaoEn.N);
		}

		if (!papeisMenu.contains(pm)) {
			papeisMenu.add(pm);
		}
	}

	public TextField getTxtNome() {
		return this.txtNome;
	}

	public AbstractComponent getTreeTable() {
		return treeTableSeguimentoMenu;
	}

	public void buildTree(List<FmMenu> menus, FmModulo modulo) {
		if (modulo != null) {
			Object moduloId = addModuloToTree(modulo);

			for (FmMenu m : menus) {
				PapelMenu p = new PapelMenu(m, binder.getItemDataSource()
						.getBean());
				if (m.getParentId() == null) {
					addModuloChild(moduloId, m, p);
				} else {
					addMenuChild(m, p);
				}
			}
		}
	}

	private void addMenuChild(FmMenu m, PapelMenu p) {
		if (!treeTableSeguimentoMenu.containsId(m.getId())) {
			Object itemId = treeTableSeguimentoMenu.addItem(
					new Object[] { p.getMenu().getCaption(),
							p.getPodeInserir(), p.getPodeAlterar(),
							p.getPodeConsultar(), p.getPodeExcluir(),
							p.getHabilitado(), p }, m.getId());
			treeTableSeguimentoMenu.setParent(itemId, p.getMenu().getParentId());
			treeTableSeguimentoMenu.setCollapsed(itemId, true);
		}
	}

	private void addModuloChild(Object moduloId, FmMenu m, PapelMenu p) {
		if (!treeTableSeguimentoMenu.containsId(m.getId())) {
			Object itemId = treeTableSeguimentoMenu.addItem(
					new Object[] { p.getMenu().getCaption(),
							p.getPodeInserir(), p.getPodeAlterar(),
							p.getPodeConsultar(), p.getPodeExcluir(),
							p.getHabilitado(), p }, m.getId());
			treeTableSeguimentoMenu.setParent(itemId, moduloId);
			treeTableSeguimentoMenu.setCollapsed(itemId, true);
		}
	}

	private Object addModuloToTree(FmModulo modulo) {
		if (!treeTableSeguimentoMenu.containsId(modulo)) {
			Object moduloId = treeTableSeguimentoMenu.addItem(
					new Object[] { modulo.getCaption(), null, null, null, null,
							null, null }, modulo);
			treeTableSeguimentoMenu.setChildrenAllowed(moduloId, true);
			treeTableSeguimentoMenu.setCollapsed(moduloId, true);

			return moduloId;
		}

		return modulo;
	}

}