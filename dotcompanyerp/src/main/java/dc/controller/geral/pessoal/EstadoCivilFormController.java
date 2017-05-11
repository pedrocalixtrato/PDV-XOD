package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.model.business.geral.pessoal.EstadoCivilBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.EstadoCivilFormView;

@Controller
@Scope("prototype")
public class EstadoCivilFormController extends
		CRUDFormController<EstadoCivilEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstadoCivilFormView subView;

	/**
	 * ENTITY
	 */

	private EstadoCivilEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private EstadoCivilBusiness<EstadoCivilEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public EstadoCivilFormController() {
		// TODO Auto-generated constructor stub
	}

	public EstadoCivilBusiness<EstadoCivilEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Estado civil";
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
	public EstadoCivilEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new EstadoCivilFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(EstadoCivilEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			// Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
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
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new EstadoCivilEntity();
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