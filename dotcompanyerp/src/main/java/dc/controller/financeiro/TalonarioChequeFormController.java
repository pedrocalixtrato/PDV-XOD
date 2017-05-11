package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.TalonarioCheque;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.servicos.dao.financeiro.ITalonarioChequeDAO;
import dc.visao.financeiro.TalonarioChequeFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class TalonarioChequeFormController extends CRUDFormController<TalonarioCheque> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TalonarioChequeFormView subView;

	@Autowired
	private ITalonarioChequeDAO talonarioChequeDAO;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;

	private TalonarioCheque currentBean;

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
			currentBean = new TalonarioCheque();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
		
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new TalonarioChequeFormView();

			this.fieldGroup = new DCFieldGroup<>(TalonarioCheque.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTxtTalao(),"talao");
			fieldGroup.bind(this.subView.getTxtNumero(),"numero");
			fieldGroup.bind(this.subView.getCmbStatus(),"statusTalao");
			fieldGroup.bind(this.subView.getCmbContaCaixa(),"contaCaixa");
			
			this.subView.getCmbContaCaixa().configuraCombo(
					"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			
		}catch (Exception e) {
	       e.printStackTrace();
	    }

	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = talonarioChequeDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void actionSalvar() {

		try {
			talonarioChequeDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
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
		return "Talonário Cheque";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.talonarioChequeDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			TalonarioCheque cheque = (TalonarioCheque) id;

			try {
				talonarioChequeDAO.delete(cheque);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public TalonarioCheque getModelBean() {
		return currentBean;
	}

}
