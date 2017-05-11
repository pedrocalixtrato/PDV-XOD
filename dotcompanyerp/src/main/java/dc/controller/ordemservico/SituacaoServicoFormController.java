package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.SituacaoServicoEntity;
import dc.model.dao.ordemservico.ISituacaoServicoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.SituacaoServicoFormView;

@Controller
@Scope("prototype")
public class SituacaoServicoFormController extends CRUDFormController<SituacaoServicoEntity> {

	private static final long serialVersionUID = 1L;

	SituacaoServicoFormView subView;

	@Autowired
	private ISituacaoServicoDAO situacaoServicoDAO;

	private SituacaoServicoEntity currentBean;

	@Override
	protected String getNome() {
		return "Situação de Serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			situacaoServicoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
	        this.currentBean = this.situacaoServicoDAO.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}


	@Override
	protected void initSubView() {
		try {
	        this.subView = new SituacaoServicoFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(SituacaoServicoEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	         this.currentBean = new SituacaoServicoEntity();
	 
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
			this.situacaoServicoDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
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
	protected void removerEmCascata(List<Serializable> ids) {
		
		for (Serializable id : ids) {
			SituacaoServicoEntity situacaoServico = (SituacaoServicoEntity) id;

			try {
				situacaoServicoDAO.delete(situacaoServico);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "tipoServicoForm";
	}

	@Override
	public SituacaoServicoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
