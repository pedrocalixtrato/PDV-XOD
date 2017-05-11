package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.model.business.ordemservico.GrupoOsBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.GrupoOsFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class GrupoOsFormController extends CRUDFormController<GrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	private GrupoOsFormView subView;

	private GrupoOsEntity currentBean;

	/**
	 * BUSINESS
	 */
	@Autowired
	private GrupoOsBusiness<GrupoOsEntity> business;

	/**
	 * CONSTRUTOR
	 */
	public GrupoOsFormController() {
	}

	public GrupoOsBusiness<GrupoOsEntity> getBusiness() {
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
	public GrupoOsEntity getModelBean() {
		return currentBean;
	}

	@Override
	protected void initSubView() {
		try {
	        this.subView = new GrupoOsFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(GrupoOsEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtNome(),"nome");
	
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
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
	        this.currentBean = this.business.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
	         this.currentBean = new GrupoOsEntity();
	 
	         // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
	         fieldGroup.setItemDataSource(this.currentBean);
	 
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
		for (Serializable id : ids) {
			GrupoOsEntity grupoOs = (GrupoOsEntity) id;

			try {
				business.delete(grupoOs);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}
}