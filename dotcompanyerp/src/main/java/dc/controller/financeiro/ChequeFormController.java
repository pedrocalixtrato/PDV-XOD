package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.Cheque;
import dc.servicos.dao.financeiro.IChequeDAO;
import dc.servicos.dao.financeiro.ITalonarioChequeDAO;
import dc.visao.financeiro.ChequeFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class ChequeFormController extends CRUDFormController<Cheque> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChequeFormView subView;

	@Autowired
	private IChequeDAO chequeDAO;
	
	/**
	 * BUSINESS
	 */
	
	//@Autowired
	//private ChequeBusiness<Cheque> business;

	@Autowired
	private ITalonarioChequeDAO talonarioChequeDAO;

	private Cheque currentBean;
	
	/*public ChequeBusiness<Cheque> getBusiness() {
		 return business;
	}*/

	@Override
	protected String getNome() {
		return "Cheque";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	protected void actionSalvar() {
		try {
			this.chequeDAO.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = chequeDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new ChequeFormView();

			this.fieldGroup = new DCFieldGroup<>(Cheque.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getDtStatus(),"dataStatus");
			fieldGroup.bind(this.subView.getTxtNumero(),"numero");
			fieldGroup.bind(this.subView.getCmbStatus(),"statusCheque");
			fieldGroup.bind(this.subView.getCmbTalonarioCheque(),"idTalonarioCheque");
			
			this.subView.getCmbTalonarioCheque().configuraCombo(
					"talao", TalonarioChequeListController.class, this.talonarioChequeDAO, this.getMainController());
			
		}catch (Exception e) {
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
			currentBean = new Cheque();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.chequeDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			Cheque cheque = (Cheque) id;

			try {
				chequeDAO.delete(cheque);
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
	public Cheque getModelBean() {
		return currentBean;
	}

}