package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.Adiantamento;
import dc.servicos.dao.financeiro.IAdiantamentoDAO;
import dc.servicos.dao.financeiro.ILancamentoPagarDAO;
import dc.visao.financeiro.AdiantamentoFormView;
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
public class AdiantamentoFormController extends CRUDFormController<Adiantamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AdiantamentoFormView subView;

	@Autowired
	private IAdiantamentoDAO adiantamentoDAO;

	@Autowired
	private ILancamentoPagarDAO lancamentoPagarDAO;

	private Adiantamento currentBean;

	@Override
	protected String getNome() {
		return "Adiantamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		try {
			adiantamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = adiantamentoDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		
		try {
			
			subView = new AdiantamentoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(Adiantamento.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getDtAdiantamento(),"dataAdiantamento");
	        fieldGroup.bind(this.subView.getCmbLancamentoPagar(),"idLancamentoPagar");
	        fieldGroup.bind(this.subView.getTxtValor(), "valor");
	        
	        this.subView.getCmbLancamentoPagar().configuraCombo(
	        		"fornecedor.pessoa.nome", LancamentoPagarListController.class, this.lancamentoPagarDAO, this.getMainController());

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
	        this.currentBean = new Adiantamento();

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
			this.adiantamentoDAO.deleteAll(ids);

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
	public boolean isFullSized() {
		return true;
	}

	@Override
	public Adiantamento getModelBean() {
		return currentBean;
	}

}