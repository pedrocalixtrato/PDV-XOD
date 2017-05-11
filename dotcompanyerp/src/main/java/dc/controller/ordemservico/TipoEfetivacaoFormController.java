package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.ordemservico.TipoEfetivacaoEntity;
import dc.model.dao.ordemservico.ITipoEfetivacaoOsDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.TipoEfetivacaoFormView;


@Controller
@Scope("prototype")
public class TipoEfetivacaoFormController extends CRUDFormController<TipoEfetivacaoEntity> {

	private static final long serialVersionUID = 1L;

	TipoEfetivacaoFormView subView;

	@Autowired
	private ITipoEfetivacaoOsDAO tipoEfetivacaoDAO;

	private TipoEfetivacaoEntity currentBean;

	@Override
	protected String getNome() {
		return "Tipo Efetivação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			tipoEfetivacaoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
	        this.currentBean = this.tipoEfetivacaoDAO.find(id);
	
	        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	protected void initSubView() {
		try {
	        this.subView = new TipoEfetivacaoFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(TipoEfetivacaoEntity.class);
	
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
	        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
	
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
	         this.currentBean = new TipoEfetivacaoEntity();
	 
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
			this.tipoEfetivacaoDAO.deleteAll(ids);

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
			TipoEfetivacaoEntity tip = (TipoEfetivacaoEntity) id;

			try {
				tipoEfetivacaoDAO.delete(tip);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "tipoEfetivacaoForm";
	}

	@Override
	public TipoEfetivacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
