package dc.controller.geral.eventos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.eventos.CerimonialEventosEntity;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.eventos.ICerimonialEventosDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.CerimonialEventosFormView;

@Controller
@Scope("prototype")
public class CerimonialEventosFormController extends
		CRUDFormController<CerimonialEventosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private CerimonialEventosFormView subView;

	/**
	 * ENTITY
	 */

	private CerimonialEventosEntity entity;

	/**
	 * BUSINESS
	 */

	// @Autowired
	// private CerimonialBusiness<CerimonialEventosEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private ICerimonialEventosDAO cerimonialEventosDAO;

	@Autowired
	private IUfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public CerimonialEventosFormController() {
		// TODO Auto-generated constructor stub
	}

	// public CerimonialBusiness<CerimonialEventosEntity> getBusiness() {
	// return business;
	// }

	@Override
	protected String getNome() {
		return "Cerimonial Eventos";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public CerimonialEventosEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new CerimonialEventosFormView(this);

			this.fieldGroup = new DCFieldGroup<>(CerimonialEventosEntity.class);

			// Mapeia os campos

			fieldGroup.bind(this.subView.getTxtNome(), "nome");
			fieldGroup.bind(this.subView.getTxtCnpj(), "cnpj");
			fieldGroup.bind(this.subView.getTfEndereco(), "endereco");
			fieldGroup.bind(this.subView.getTfBairro(), "bairro");
			fieldGroup.bind(this.subView.getTxtComplemento(), "complemento");
			fieldGroup.bind(this.subView.getTfEmail(), "email");
			fieldGroup.bind(this.subView.getTfCep(), "cep");
			fieldGroup.bind(this.subView.getTfCidade(), "cidade");

			
			fieldGroup.bind(this.subView.getTfTelefone(),"telefone");
			fieldGroup.bind(this.subView.getTfCelular(),"celular");
			fieldGroup.bind(this.subView.getTxtContato(),"contato");

			// this.subView.getMocUf().configuraCombo(
			// "nome", UfListController.class, this.ufDAO,
			// this.getMainController());

			carregarUf();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			// Commit tenta transferir os dados do View para a entidade ,
			// levando em conta os critérios de validação.
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {
			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			// this.business.saveOrUpdate(this.entity);
			this.cerimonialEventosDAO.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	protected void carregar(Serializable id) {
		try {
			// this.entity = this.business.find(id);

			this.entity = this.cerimonialEventosDAO.find(id);
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new CerimonialEventosEntity();
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			// this.business.deleteAll(ids);

			this.cerimonialEventosDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				CerimonialEventosEntity cerimonial = (CerimonialEventosEntity) id;

				try {
					cerimonialEventosDAO.delete(cerimonial);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.getAll();
			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getMocUf().setContainerDataSource(bic);
			this.subView.getMocUf().setItemCaptionPropertyId("sigla");
			
			fieldGroup.bind(this.subView.getTxtContato(), "contato");
			fieldGroup.bind(this.subView.getMocUf(), "siglaUf");
			

		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/**
	 * 
	 */

}