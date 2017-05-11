package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.CentroResultado;
import dc.servicos.dao.financeiro.ICentroResultadoDAO;
import dc.servicos.dao.financeiro.IPlanoCentroResultadoDAO;
import dc.visao.financeiro.CentroResultadoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no CentroResultado
 *         de Dados Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class CentroResultadoFormController extends CRUDFormController<CentroResultado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICentroResultadoDAO centroresultadoDAO;

	private CentroResultado currentBean;

	@Autowired
	private IPlanoCentroResultadoDAO planoresultadoDAO;

	private CentroResultadoFormView subView;

	@Override
	protected void actionSalvar() {
		try {
			centroresultadoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = centroresultadoDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
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
	        this.currentBean = new CentroResultado();

	        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);

	    } catch (Exception e) {
	        e.printStackTrace();
	        mensagemErro(e.getMessage());
	    }
	}

	@Override
	protected String getNome() {
		return "Centro Resultado";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	protected void initSubView() {
		
       try {
			
			subView = new CentroResultadoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(CentroResultado.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtClassficacao(),"classificacao");
	        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
	        fieldGroup.bind(this.subView.getCmbPlanoCentroResultado(),"planoCentroResultado");
	        
	        this.subView.getCmbPlanoCentroResultado().configuraCombo(
	        		"nome", PlanoCentroResultadoListController.class, this.planoresultadoDAO, this.getMainController());

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			//this.business.deleteAll(ids);
			this.centroresultadoDAO.deleteAll(ids);

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
	public CentroResultado getModelBean() {
		return currentBean;
	}

}