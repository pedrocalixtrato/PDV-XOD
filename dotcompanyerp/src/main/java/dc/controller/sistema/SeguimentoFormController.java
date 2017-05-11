package dc.controller.sistema;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.PapelMenu;
import dc.entidade.framework.SeguimentoEntity;
import dc.model.business.sistema.SeguimentoBusiness;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.servicos.dao.framework.geral.ISeguimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.sistema.SeguimentoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class SeguimentoFormController extends
		CRUDFormController<SeguimentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SeguimentoFormView subView;

	/**
	 * ENTITY
	 */

	private SeguimentoEntity entity = new SeguimentoEntity();

	/**
	 * BUSINESS
	 */

	@Autowired
	private SeguimentoBusiness<SeguimentoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private ISeguimentoDAO seguimentoDAO;

	@Autowired
	private IFmMenuDAO menuDAO;

	/**
	 * CONSTRUTOR
	 */

	public SeguimentoFormController() {
		// TODO Auto-generated constructor stub
	}

	public SeguimentoBusiness<SeguimentoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Seguimento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public SeguimentoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		criarNovoBean();
		subView = new SeguimentoFormView(this);
		subView.populaModulos(seguimentoDAO.getAll(FmModulo.class));
	}

	@Override
	protected boolean validaSalvar() {
		boolean retornoValidacao = true;

		try {
			subView.getBinder().commit();

			SeguimentoEntity papel = subView.getBinder().getItemDataSource()
					.getBean();

			List<PapelMenu> fs = subView.getPapelMenus();

			papel.setPapelMenuList(fs);

			if (papel.getNome() == null || papel.getNome().isEmpty()) {
				adicionarErroDeValidacao(subView.getTxtNome(),
						"Não pode ficar em Branco!");

				retornoValidacao = false;
			}

			if (papel.getPapelMenuList() == null
					|| papel.getPapelMenuList().isEmpty()) {
				adicionarErroDeValidacao(subView.getTreeTable(),
						"Ao menos um menu deve ser associado ao seguimento");

				retornoValidacao = false;
			}

			this.entity = papel;
		} catch (CommitException e) {
			e.printStackTrace();

			retornoValidacao = false;
		}

		return retornoValidacao;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			this.business.saveOrUpdate(entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro("Problemas ao salvar registro");
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getBinder().setItemDataSource(this.entity);

			this.subView.populaPapelMenu(this.seguimentoDAO
					.getSeguimentosMenusOrdered(this.entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new SeguimentoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.subView.getBinder().discard();
			this.subView.getBinder().setItemDataSource(getCurrentBean());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
			this.seguimentoDAO.deleteAll(objetos);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */

	public SeguimentoEntity getCurrentBean() {
		return entity;
	}

	public void loadModules(FmModulo modulo) {
		List<FmMenu> m = menuDAO.getAllMenusByModuleIdGrouped(modulo.getId());
		subView.buildTree(m, modulo);
	}

	public List<FmMenu> getFmMenus(FmModulo fm) {
		return menuDAO.getAllMenusByModuleId(fm.getId());
	}

}