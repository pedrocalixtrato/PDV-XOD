package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.EquipamentoEntity;
import dc.model.dao.ordemservico.IEquipamentoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.EquipamentoFormView;


@Controller
@Scope("prototype")
public class EquipamentoFormController extends CRUDFormController<EquipamentoEntity> {

	private static final long serialVersionUID = 1L;

	EquipamentoFormView subView;

	@Autowired
	private IEquipamentoDAO equipamentoDAO;

	private EquipamentoEntity currentBean;

	@Override
	protected String getNome() {
		return "Equipamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			equipamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
	        this.currentBean = this.equipamentoDAO.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void initSubView() {

		try {
	        this.subView = new EquipamentoFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(EquipamentoEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTfFilial(),"filial");
	        fieldGroup.bind(this.subView.getTfEquipamento(),"equipamento");
	
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
	         this.currentBean = new EquipamentoEntity();
	 
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
			this.equipamentoDAO.deleteAll(ids);

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
			EquipamentoEntity equi = (EquipamentoEntity) id;

			try {
				equipamentoDAO.delete(equi);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "equipamentoForm";
	}

	@Override
	public EquipamentoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
