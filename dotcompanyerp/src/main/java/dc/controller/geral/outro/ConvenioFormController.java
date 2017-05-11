package dc.controller.geral.outro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.PessoaListController;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.outro.ConvenioEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.outro.ConvenioBusiness;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.servicos.dao.geral.IUfDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.outro.ConvenioFormView;

@Controller
@Scope("prototype")
public class ConvenioFormController extends CRUDFormController<ConvenioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConvenioFormView subView;

	/**
	 * ENTITY
	 */

	private ConvenioEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private ConvenioBusiness<ConvenioEntity> business;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private IUfDAO ufDAO;

	@Autowired
	private IPessoaDAO pessoaDAO;

	/**
	 * CONSTRUTOR
	 */

	public ConvenioFormController() {
		// TODO Auto-generated constructor stub
	}

	public ConvenioBusiness<ConvenioEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Convênio";
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
	public ConvenioEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ConvenioFormView(this);
			
	            this.fieldGroup = new DCFieldGroup<>(ConvenioEntity.class);
				
				// Mapeia os campos
				
				fieldGroup.bind(this.subView.getTfNome(),"nome");
				fieldGroup.bind(this.subView.getPdfDataCadastro(),"dataCadastro");
				fieldGroup.bind(this.subView.getTfLogradouro(),"logradouro");
				fieldGroup.bind(this.subView.getTfBairro(),"bairro");
				fieldGroup.bind(this.subView.getTfCnpj(),"cnpj");
				
				fieldGroup.bind(this.subView.getTfTelefone(),"telefone");
				fieldGroup.bind(this.subView.getTfContato(),"contato");
				
				fieldGroup.bind(this.subView.getMocPessoa(),"pessoa");
				
				//this.subView.getMocUf().configuraCombo(
				//		"nome", UfListController.class, this.ufDAO, this.getMainController());
				
				this.subView.getMocPessoa().configuraCombo(
						"nome", PessoaListController.class, this.pessoaDAO, this.getMainController());
				
				carregarUf();
			} catch (Exception e) {
				e.printStackTrace();
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
	protected void actionSalvar() {
		try {
			this.business.saveOrUpdate(entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new ConvenioEntity();
			
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				ConvenioEntity convenio = (ConvenioEntity) id;

				try {
					business.delete(convenio);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

	/**
	 * COMBOS
	 */
	
	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.getAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getMocUf().setContainerDataSource(bic);
			this.subView.getMocUf().setItemCaptionPropertyId("nome");
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}