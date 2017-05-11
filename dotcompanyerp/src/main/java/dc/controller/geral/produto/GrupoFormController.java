package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoEntity;
import dc.model.business.geral.produto.GrupoBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.GrupoFormView;

@Controller
@Scope("prototype")
public class GrupoFormController extends CRUDFormController<GrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GrupoFormView subView;

	/**
	 * ENTITY
	 */

	private GrupoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private GrupoBusiness<GrupoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public GrupoFormController() {
		// TODO Auto-generated constructor stub
	}

	public GrupoBusiness<GrupoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Grupo";
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
	public GrupoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new GrupoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(GrupoEntity.class);
            fieldGroup.bind(this.subView.getTfNome(),"nome");
            fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
            // Commit tenta transferir os dados do View para o Model, levando em conta os critérios de validação
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
			this.entity = new GrupoEntity();
			
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