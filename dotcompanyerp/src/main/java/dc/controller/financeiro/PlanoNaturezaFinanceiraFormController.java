package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.PlanoNaturezaFinanceira;
import dc.servicos.dao.financeiro.IPlanoNaturezaFinanceiraDAO;
import dc.visao.financeiro.PlanoNaturezaFinanceiraFormView;
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
public class PlanoNaturezaFinanceiraFormController extends CRUDFormController<PlanoNaturezaFinanceira> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoNaturezaFinanceiraFormView subView;

	@Autowired
	private IPlanoNaturezaFinanceiraDAO planoDAO;

	private PlanoNaturezaFinanceira currentBean;

	@Override
	protected String getNome() {
		return "Plano Natureza Financeira";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			planoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = planoDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
         try {
			
			subView = new PlanoNaturezaFinanceiraFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(PlanoNaturezaFinanceira.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtNome(),"nome");
	        fieldGroup.bind(this.subView.getTxtMascara(),"mascara");
	        fieldGroup.bind(this.subView.getTxtNiveis(),"niveis");
	        fieldGroup.bind(this.subView.getDtDataInclusao(),"dataInclusao");
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	        this.currentBean = new PlanoNaturezaFinanceira();

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
			this.planoDAO.deleteAll(ids);

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
	public PlanoNaturezaFinanceira getModelBean() {
		return currentBean;
	}

}