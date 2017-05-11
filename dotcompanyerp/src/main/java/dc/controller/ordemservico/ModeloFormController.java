package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.ModeloEntity;
import dc.servicos.dao.ordemservico.IMarcaOsDAO;
import dc.servicos.dao.ordemservico.IModeloDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ModeloFormView;


@Controller
@Scope("prototype")
public class ModeloFormController extends CRUDFormController<ModeloEntity> {

	private static final long serialVersionUID = 1L;

	private ModeloFormView subView;

	@Autowired
	private IModeloDAO modeloDAO;

	@Autowired
	private IMarcaOsDAO marcaDAO;

	private ModeloEntity currentBean;

	@Override
	protected String getNome() {
		return "Modelo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			modeloDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
	        this.currentBean = this.modeloDAO.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void initSubView() {
		try {
	        this.subView = new ModeloFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(ModeloEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtNome(),"nome");
	        fieldGroup.bind(this.subView.getCbMarca(),"marca");
	        
	        this.subView.getCbMarca().configuraCombo(
	        		"nome", MarcaListController.class, this.marcaDAO, this.getMainController());
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	         this.currentBean = new ModeloEntity();
	 
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
			this.modeloDAO.deleteAll(ids);

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
			ModeloEntity modeloOs = (ModeloEntity) id;

			try {
				modeloDAO.delete(modeloOs);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "modeloForm";
	}

	@Override
	public ModeloEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
