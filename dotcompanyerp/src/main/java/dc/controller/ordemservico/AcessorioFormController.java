package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.AcessorioEntity;
import dc.model.dao.ordemservico.IAcessorioDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.AcessorioFormView;

@Controller
@Scope("prototype")
public class AcessorioFormController extends CRUDFormController<AcessorioEntity> {

	private static final long serialVersionUID = 1L;

	AcessorioFormView subView;

	@Autowired
	private IAcessorioDAO acessorioDAO;

	private AcessorioEntity currentBean;

	@Override
	protected String getNome() {
		return "Acessório";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			acessorioDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			        this.currentBean = this.acessorioDAO.find(id);
			
			        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
			        fieldGroup.setItemDataSource(this.currentBean);
			
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}

	@Override
	protected void initSubView() {
		try {
			        this.subView = new AcessorioFormView(this);
			
			        // Cria o DCFieldGroup
			        this.fieldGroup = new DCFieldGroup<>(AcessorioEntity.class);
			
			        // Mapeia os campos
			        fieldGroup.bind(this.subView.getTxtNome(),"nome");
			
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}

	@Override
	protected void criarNovoBean() {
		 try {
			         this.currentBean = new AcessorioEntity();
			 
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
			this.acessorioDAO.deleteAll(ids);

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
			AcessorioEntity acessorio = (AcessorioEntity) id;

			try {
				acessorioDAO.delete(acessorio);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "acessorioForm";
	}

	@Override
	public AcessorioEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
