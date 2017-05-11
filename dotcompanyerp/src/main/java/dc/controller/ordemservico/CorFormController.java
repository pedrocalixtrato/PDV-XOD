package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.CorEntity;
import dc.model.dao.ordemservico.ICorDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.CorFormView;


@Controller
@Scope("prototype")
public class CorFormController extends CRUDFormController<CorEntity> {

	private static final long serialVersionUID = 1L;

	CorFormView subView;

	@Autowired
	private ICorDAO corDAO;

	private CorEntity currentBean;

	@Override
	protected String getNome() {
		return "Cor";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			corDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
	        this.currentBean = this.corDAO.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	protected void initSubView() {
		try {
	        this.subView = new CorFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(CorEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtNome(),"nome");
	
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
	         this.currentBean = new CorEntity();
	 
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
			this.corDAO.deleteAll(ids);

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
			CorEntity cor = (CorEntity) id;

			try {
				corDAO.delete(cor);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "corForm";
	}

	@Override
	public CorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
