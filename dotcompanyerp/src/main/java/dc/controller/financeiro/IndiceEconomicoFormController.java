package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.controller.geral.diverso.PaisListController;
import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.model.business.geral.pessoal.IndiceEconomicoBusiness;
import dc.servicos.dao.geral.diverso.IPaisDAO;
import dc.visao.financeiro.IndiceEconomicoFormView;
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
public class IndiceEconomicoFormController extends CRUDFormController<IndiceEconomicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceEconomicoFormView subView;

	//@Autowired
	//private IndiceEconomicoDAO indiceDAO;
	
	/**
	 * BUSINESS
	 */
	
	@Autowired
	private IndiceEconomicoBusiness<IndiceEconomicoEntity> business;
	
	@Autowired
	private IPaisDAO paisDAO;

	private IndiceEconomicoEntity currentBean;
	
	public IndiceEconomicoBusiness<IndiceEconomicoEntity> getBusiness() {
		 return business;
	}

	@Override
	protected String getNome() {
		return "Índice Econômico";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			business.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		 try {
		        this.currentBean = this.business.find(id);

		        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
		        fieldGroup.setItemDataSource(this.currentBean);

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	@Override
	protected void initSubView() {
		try {
		       this.subView = new IndiceEconomicoFormView(this);

		        // Cria o DCFieldGroup
		        this.fieldGroup = new DCFieldGroup<>(IndiceEconomicoEntity.class);

		        // Mapeia os campos
		        fieldGroup.bind(this.subView.getTxtSigla(),"sigla");
		        fieldGroup.bind(this.subView.getTxtNome(),"nome");
		        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
		        
		        fieldGroup.bind(this.subView.getCbPais(),"pais");
		        
		        this.subView.getCbPais().configuraCombo(
		        		"nomePtbr", PaisListController.class, this.paisDAO, this.getMainController());
		        
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	        this.currentBean = new IndiceEconomicoEntity();

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
			this.business.deleteAll(ids);

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
		// TODO Auto-generated method stub
		return "indiceEconomicoForm";
	}

	@Override
	public IndiceEconomicoEntity getModelBean() {
		return currentBean;
	}

}