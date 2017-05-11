package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.model.dao.geral.pessoal.INotaFiscalTipoDAO;
import dc.visao.comercial.NotaFiscalTipoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class NotaFiscalTipoFormController extends CRUDFormController<NotaFiscalTipo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NotaFiscalTipo currentBean;

	NotaFiscalTipoFormView subView;

	@Autowired
	private INotaFiscalTipoDAO dao;
	
	//@Autowired
	//private NotaFiscalTipoBusiness<NotaFiscalTipo> business;
	
	//public NotaFiscalTipoBusiness<NotaFiscalTipo> getBusiness() {
	//	 return business;
	//}

	@Override
	public String getViewIdentifier() {
		return "notaFiscalTipoForm";
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
protected void criarNovoBean() {
	try {
        this.currentBean = new NotaFiscalTipo();

        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.currentBean);

    } catch (Exception e) {
        e.printStackTrace();
        mensagemErro(e.getMessage());
    }
}

	@Override
	protected void initSubView() {
		try {
			
			subView = new NotaFiscalTipoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(NotaFiscalTipo.class);
			fieldGroup.bind(this.subView.getTxtNome(),"nome");
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		
		
		try {
			currentBean = dao.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {

		try {

			this.dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		} catch (Exception e) {
			e.printStackTrace();
			 mensagemErro(e.getMessage());
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Nota Fiscal Tipo";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.dao.deleteAll(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagemRemovidoOK();
		}
		

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public NotaFiscalTipo getModelBean() {
		return currentBean;
	}

}
