package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.EfdTabela437Entity;
import dc.servicos.dao.geral.tabela.IEfdTabela437DAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.EfdTabela437FormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class EfdTabela437FormController extends
		CRUDFormController<EfdTabela437Entity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EfdTabela437FormView subView;

	@Autowired
	private IEfdTabela437DAO efdTabela437DAO;

	private EfdTabela437Entity currentBean;

	@Override
	protected String getNome() {
		return "EFD Tabela 437";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			efdTabela437DAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		 try {
		        this.currentBean = this.efdTabela437DAO.find(id);

		        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
		        fieldGroup.setItemDataSource(this.currentBean);

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	@Override
	protected void initSubView() {
		try {
		       this.subView = new EfdTabela437FormView(this);

		        // Cria o DCFieldGroup
		        this.fieldGroup = new DCFieldGroup<>(EfdTabela437Entity.class);

		        // Mapeia os campos
		        fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
		        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
		        
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	        this.currentBean = new EfdTabela437Entity();

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
			//this.business.deleteAll(ids);
			this.efdTabela437DAO.deleteAll(ids);

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
	public String getViewIdentifier() {
		return "efdTabela437Form";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public EfdTabela437Entity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}