
package dc.visao.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

import dc.control.enums.FilhoEn;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.servicos.dao.framework.geral.FmMenuDAO;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FmMenuFormController extends CRUDFormController<FmMenu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FmMenuFormView subView;

	/** DAO'S */

	@Autowired
	private IFmMenuDAO pDAO;

	/** ENTITIES */

	private FmMenu pEntity;

	/** CONSTRUTOR */

	public FmMenuFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FmMenu();
		}
	}

	@Override
	protected String getNome() {
		return "MENU";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.pEntity.setCaption(this.subView.getTxtCaption().getValue());
			this.pEntity.setUrlId(this.subView.getTxtURL().getValue());
			this.pEntity.setControllerClass(this.subView.getTxtController().getValue());

			boolean b1 = this.subView.getCkbPermissaoOperacao().getValue();

			this.pEntity.setPermissaoOperacao((b1 == true ? 1 : 0));

			boolean b2 = this.subView.getCkbConsultaMultiempresa().getValue();

			this.pEntity.setConsultaMultiempresa((b2 == true ? 1 : 0));

			this.pEntity.setConsultaFilterTable(this.subView.getCkbConsultaFilterTable().getValue() == true ? 1 : 0);

			this.pEntity.setFmModulo((FmModulo) this.subView.getCbModulo().getValue());

			if (this.subView.getTipoMenu().isSelected(FilhoEn.FILHO_MENU.toString())) {
				this.pEntity.setParent((FmMenu) this.subView.getCbMenu().getValue());
			}

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		/*
		 * System.out.println("carregando menu, vai printar pai");
		 * this.currentBean = this.fmDAO.find(id);
		 * 
		 * carregaCombos(this.currentBean.getFmModulo());
		 * 
		 * this.subView.getTxtCaption().setValue(this.currentBean.getCaption());
		 * this.subView.getTxtURL().setValue(this.currentBean.getUrlId());
		 * this.subView
		 * .getComboModulos().setValue(this.currentBean.getFmModulo());
		 * this.subView.setParentMenu(this.currentBean.getParent());
		 * this.subView.getCkbPermissaoOperacao().setValue(
		 * (this.currentBean.getPermissaoOperacao() == 1 ? true : false));
		 * this.subView.getCkbConsultaMultiempresa() .setValue(
		 * (this.currentBean.getConsultaMultiempresa() == 1 ? true : false));
		 * 
		 * if (this.currentBean.getControllerClass() != null &&
		 * !"".equals(this.currentBean.getControllerClass())) {
		 * this.subView.getTxtController().setValue(
		 * this.currentBean.getControllerClass()); }
		 */
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			if (this.pDAO == null) {
				this.pDAO = new FmMenuDAO();
			}

			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new FmMenuFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			if (this.pDAO == null) {
				this.pDAO = new FmMenuDAO();
			}

			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (estaVazio(this.subView.getTxtCaption()) || estaVazio(this.subView.getTxtURL())) {
			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
			this.pDAO.deleteAll(objetos);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "fmMenuForm";
	}

	public void menu(FmModulo modulo) {
		this.subView.carregarCmbMenu(this.menuListarTodos(modulo));
	}

	/** COMBOS */

	public List<FmModulo> moduloListarTodos() {
		List<FmModulo> auxLista = new ArrayList<FmModulo>();

		auxLista = this.pDAO.getAllModulos();

		return auxLista;
	}

	public List<FmMenu> menuListarTodos(FmModulo modulo) {
		List<FmMenu> auxLista = new ArrayList<FmMenu>();

		auxLista = this.pDAO.getAllMenusByModuleId(modulo.getId());

		return auxLista;
	}

	public void tipoMenu() {
		for (Enum<FilhoEn> en : FilhoEn.values()) {
			this.subView.getTipoMenu().addItem(en.toString());
		}
	}

	// private void carregaCombos(FmModulo fmModulo) {
	// carregaComboModulos();
	// carregaComboMenus(fmModulo);
	// }

	/*
	 * private void carregaComboModulos() { List<FmModulo> modulos =
	 * this.fmDAO.getAllModulos();
	 * 
	 * this.subView.populaModulos(modulos); }
	 * 
	 * public void carregaComboMenus(FmModulo module) { List<FmMenu> menus;
	 * 
	 * if (module != null) { menus =
	 * this.fmDAO.getAllMenusByModuleId(module.getId()); } else { menus =
	 * this.fmDAO.getAll(FmMenu.class); }
	 * 
	 * this.subView.populaMenus(menus); }
	 */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new FmMenu();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTxtCaption().setValue(this.pEntity.getCaption());
			this.subView.getTxtURL().setValue(this.pEntity.getUrlId());
			this.subView.getTxtController().setValue(this.pEntity.getControllerClass());
			this.subView.getCkbPermissaoOperacao().setValue((this.pEntity.getPermissaoOperacao() == 1 ? true : false));
			this.subView.getCkbConsultaMultiempresa().setValue((this.pEntity.getConsultaMultiempresa() == 1 ? true : false));
			this.subView.getCkbConsultaFilterTable().setValue(this.pEntity.isConsultaFilterTable());

			/**
			 * 
			 */

			this.tipoMenu();

			if (id.equals(0) || id == null) {
				this.subView.getCbModulo().setValue(null);
				this.subView.getCbMenu().setValue(null);

				this.subView.getCbMenu().setVisible(false);
				this.subView.getTxtController().setVisible(false);

				if (this.subView.getTipoMenu().isSelected(FilhoEn.FILHO_MODULO.toString())) {
					this.subView.getTipoMenu().select(FilhoEn.FILHO_MENU.toString());
				}

				if (this.subView.getTipoMenu().isSelected(FilhoEn.FILHO_MENU.toString())) {
					this.subView.getTipoMenu().select(FilhoEn.FILHO_MODULO.toString());
				}
			} else {
				if (this.pEntity.getParent() != null) {
					this.subView.getTipoMenu().setValue(FilhoEn.FILHO_MENU.toString());

					this.subView.getCbMenu().setVisible(true);
					this.subView.getTxtController().setVisible(true);
				} else {
					this.subView.getCbMenu().setVisible(false);
					this.subView.getTxtController().setVisible(false);
				}
			}

			/**
			 * 
			 */

			this.subView.carregarCmbModulo(this.moduloListarTodos());

			this.subView.getCbModulo().setValue(this.pEntity.getFmModulo());

			/**
			 * 
			 */

			if (this.pEntity.getFmModulo() != null) {
				this.menu(this.pEntity.getFmModulo());

				this.subView.getCbMenu().setValue(this.pEntity.getParent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */

	public void verificarTipo(ValueChangeEvent event) {
		if (event.getProperty().getValue().equals(FilhoEn.FILHO_MENU.toString())) {
			this.subView.getCbMenu().setVisible(true);
			this.subView.getTxtController().setVisible(true);
		} else {
			this.subView.getCbMenu().setVisible(false);
			this.subView.getTxtController().setVisible(false);
		}
	}

	@Override
	public FmMenu getModelBean() {
		return pEntity;
	}
}