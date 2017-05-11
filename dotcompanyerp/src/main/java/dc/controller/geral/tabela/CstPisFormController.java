package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.CstPisEntity;
import dc.servicos.dao.geral.tabela.ICstPisDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CstPisFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CstPisFormController extends CRUDFormController<CstPisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CstPisFormView subView;

	@Autowired
	private ICstPisDAO cstPisDAO;

	private CstPisEntity currentBean;

	@Override
	protected String getNome() {
		return "Cst Pis";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			cstPisDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

@Override
protected void carregar(Serializable id) {
	 try {
        this.currentBean = this.cstPisDAO.find(id);

        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.currentBean);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Override
protected void initSubView() {
	 try {
       this.subView = new CstPisFormView(this);

        // Cria o DCFieldGroup
        this.fieldGroup = new DCFieldGroup<>(CstPisEntity.class);

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
        this.currentBean = new CstPisEntity();

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
		this.cstPisDAO.deleteAll(ids);

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
		return "cstPisForm";
	}

	@Override
	public CstPisEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}