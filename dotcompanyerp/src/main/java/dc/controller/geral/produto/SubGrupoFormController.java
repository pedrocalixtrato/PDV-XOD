package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.model.business.geral.produto.SubGrupoBusiness;
import dc.model.dao.geral.produto.IGrupoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.SubGrupoFormView;

@Controller
@Scope("prototype")
public class SubGrupoFormController extends CRUDFormController<SubGrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubGrupoFormView subView;

	/**
	 * ENTITY
	 */

	private SubGrupoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private SubGrupoBusiness<SubGrupoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IGrupoDAO grupoDAO;

	/**
	 * CONSTRUTOR
	 */

	public SubGrupoFormController() {
		// TODO Auto-generated constructor stub
	}

	public SubGrupoBusiness<SubGrupoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "SubGrupo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public SubGrupoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SubGrupoFormView(this);

			this.fieldGroup = new DCFieldGroup<>(SubGrupoEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
			fieldGroup.bind(this.subView.getMocGrupoProduto(), "grupo");
			
			this.subView.getMocGrupoProduto().configuraCombo(
					"nome", GrupoListController.class, this.grupoDAO, this.getMainController());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			fieldGroup.commit();

			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new SubGrupoEntity();
			fieldGroup.setItemDataSource(this.entity);
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
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

}