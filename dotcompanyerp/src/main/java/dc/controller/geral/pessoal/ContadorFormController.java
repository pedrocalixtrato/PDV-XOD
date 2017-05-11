package dc.controller.geral.pessoal;

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
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.model.business.geral.pessoal.ContadorBusiness;
import dc.servicos.dao.geral.IUfDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.ContadorFormView;

@Controller
@Scope("prototype")
public class ContadorFormController extends CRUDFormController<ContadorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContadorFormView subView;

	//@Autowired
	//private ContadorDAO contadorDAO;

	@Autowired
	private IUfDAO ufDAO;

	private ContadorEntity currentBean;
	
	@Autowired
	private ContadorBusiness<ContadorEntity> business;
	
	public ContadorBusiness<ContadorEntity> getBusiness() {
		 return business;
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
	protected void criarNovoBean() {
		try {
			this.currentBean = new ContadorEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			this.subView = new ContadorFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(ContadorEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTxtNome(),"nome");
			fieldGroup.bind(this.subView.getTxtComplemento(),"complemento");
			fieldGroup.bind(this.subView.getTxtLogradouro(),"logradouro");
			fieldGroup.bind(this.subView.getTxtBairro(),"bairro");
			fieldGroup.bind(this.subView.getTxtEmail(),"email");
			fieldGroup.bind(this.subView.getTxtTelefone(),"fone");
			
			//this.subView.getMocUf().configuraCombo(
			//		"nome", UfListController.class, this.ufDAO, this.getMainController());
			
			carregarUf();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.business.find(id);
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void actionSalvar() {
		
		try {
			this.business.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			mensagemErro(ex.getMessage());
		}
	}

	@Override
	protected String getNome() {
		return "Contador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				ContadorEntity contador = (ContadorEntity) id;

				try {
					business.delete(contador);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
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
	protected Component getSubView() {
		return subView;
	}

	@Override
	public ContadorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
	
	/**
	 * 
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