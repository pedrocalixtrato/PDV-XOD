package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.controller.geral.diverso.UfListController;
import dc.entidade.geral.tabela.FeriadoEntity;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.tabela.IFeriadoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.FeriadosFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class FeriadosFormController extends CRUDFormController<FeriadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FeriadosFormView subView;

	@Autowired
	private IFeriadoDAO feriadosDAO;

	@Autowired
	private IUfDAO ufDAO;

	private FeriadoEntity currentBean;

	@Override
	protected String getNome() {
		return "Feriados";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			feriadosDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

@Override
protected void carregar(Serializable id) {
	 try {
        this.currentBean = this.feriadosDAO.find(id);

        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.currentBean);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Override
protected void initSubView() {
	 try {
       this.subView = new FeriadosFormView(this);

        // Cria o DCFieldGroup
        this.fieldGroup = new DCFieldGroup<>(FeriadoEntity.class);

        // Mapeia os campos
        fieldGroup.bind(this.subView.getTxtAno(),"ano");
        fieldGroup.bind(this.subView.getTxtNome(),"nome");
        fieldGroup.bind(this.subView.getDtData(),"data");
        
        this.subView.getCmbUf().configuraCombo(
        		"nome", UfListController.class, this.ufDAO, this.getMainController());
        
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
        this.currentBean = new FeriadoEntity();

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
		this.feriadosDAO.deleteAll(ids);

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
		return "feriadosForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	@Override
	public FeriadoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}