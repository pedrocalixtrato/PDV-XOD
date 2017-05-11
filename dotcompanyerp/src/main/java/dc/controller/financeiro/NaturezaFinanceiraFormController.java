package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.contabilidade.IContabilContaDAO;
import dc.servicos.dao.financeiro.INaturezaFinanceiraDAO;
import dc.servicos.dao.financeiro.IPlanoNaturezaFinanceiraDAO;
import dc.visao.financeiro.NaturezaFinanceiraFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;

@Controller
@Scope("prototype")
public class NaturezaFinanceiraFormController extends CRUDFormController<NaturezaFinanceira> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NaturezaFinanceiraFormView subView;

	@Autowired
	private INaturezaFinanceiraDAO naturezafinanceiraDAO;

	private NaturezaFinanceira currentBean;

	@Autowired
	private IPlanoNaturezaFinanceiraDAO planonaturezafinanceiraDAO;

	@Autowired
	private IContabilContaDAO contabilcontaDAO;

	@Autowired
	private MainController mainController;

	@Override
	protected String getNome() {
		return "Natureza Financeira";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			naturezafinanceiraDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = naturezafinanceiraDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		
        try {
			
			subView = new NaturezaFinanceiraFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(NaturezaFinanceira.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtClassficacao(),"classificacao");
	        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
	        fieldGroup.bind(this.subView.getTxtAplicacao(),"aplicacao");
	        //fieldGroup.bind(this.subView.getCbTipo(),"tipo");
	        fieldGroup.bind(this.subView.getCbPlanoNaturezaFinanceira(),"planoNaturezaFinanceira");
	        fieldGroup.bind(this.subView.getCbContabilConta(),"contabilconta");
	        
	        this.subView.getCbPlanoNaturezaFinanceira().configuraCombo(
	        		"nome", PlanoNaturezaFinanceiraListController.class, this.planonaturezafinanceiraDAO, this.getMainController());
	        this.subView.getCbContabilConta().configuraCombo(
	        		"classificacao", ContabilContaListController.class, this.contabilcontaDAO, this.getMainController());

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
	        this.currentBean = new NaturezaFinanceira();

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
			this.naturezafinanceiraDAO.deleteAll(ids);

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
		return ClassUtils.getUrl(this);
	}
	
	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public NaturezaFinanceira getModelBean() {
		return currentBean;
	}
}
