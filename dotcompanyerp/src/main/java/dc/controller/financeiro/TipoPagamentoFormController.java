package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.TipoPagamento;
import dc.servicos.dao.financeiro.ITipoPagamentoDAO;
import dc.visao.financeiro.TipoPagamentoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class TipoPagamentoFormController extends CRUDFormController<TipoPagamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoPagamentoFormView subView;

	@Autowired
	private ITipoPagamentoDAO tipoPagamentoDAO;

	private TipoPagamento currentBean;

	@Override
	protected String getNome() {
		return "Tipo Pagamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			tipoPagamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = tipoPagamentoDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

@Override
protected void initSubView() {
	
		
	try {
		
		subView = new TipoPagamentoFormView(this);
		
		this.fieldGroup = new DCFieldGroup<>(TipoPagamento.class);

        // Mapeia os campos
        fieldGroup.bind(this.subView.getTxtTipo(),"tipo");
        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");

    } catch (Exception e) {
        e.printStackTrace();
    }

}

@Override
protected void criarNovoBean() {
		
	try {
        this.currentBean = new TipoPagamento();

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
		this.tipoPagamentoDAO.deleteAll(ids);

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
		
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public TipoPagamento getModelBean() {
		return currentBean;
	}
}
