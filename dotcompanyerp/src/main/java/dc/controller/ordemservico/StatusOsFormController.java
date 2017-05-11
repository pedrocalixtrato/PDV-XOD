package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.StatusOsEntity;
import dc.model.dao.ordemservico.IStatusOsDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.StatusOsFormView;

@Controller
@Scope("prototype")
public class StatusOsFormController extends CRUDFormController<StatusOsEntity> {

	private static final long serialVersionUID = 1L;

	StatusOsFormView subView;

	@Autowired
	private IStatusOsDAO statusOsDAO;

	private StatusOsEntity currentBean;

	@Override
	protected String getNome() {
		return "Status O.S";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			statusOsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {

		try {
	        this.currentBean = this.statusOsDAO.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	protected void initSubView() {
		try {
	        this.subView = new StatusOsFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(StatusOsEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	         this.currentBean = new StatusOsEntity();
	 
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
			this.statusOsDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
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
	protected void removerEmCascata(List<Serializable> ids) {
		
		for (Serializable id : ids) {
			StatusOsEntity statusOs = (StatusOsEntity) id;

			try {
				statusOsDAO.delete(statusOs);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "statusOsForm";
	}

	@Override
	public StatusOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
