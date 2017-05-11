package dc.controller.geral.outro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.TipoSindicatoEn;
import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.outro.SindicatoBusiness;
import dc.servicos.dao.geral.IUfDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.outro.SindicatoFormView;

@Controller
@Scope("prototype")
public class SindicatoFormController extends CRUDFormController<SindicatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SindicatoFormView subView;

	/**
	 * ENTITY
	 */

	private SindicatoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private SindicatoBusiness<SindicatoEntity> business;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private IUfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public SindicatoFormController() {
		// TODO Auto-generated constructor stub
	}

	public SindicatoBusiness<SindicatoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Sindicato";
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
	public SindicatoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SindicatoFormView(this);

			this.fieldGroup = new DCFieldGroup<>(SindicatoEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			fieldGroup.bind(this.subView.getTfLogradouro(),"logradouro");
			fieldGroup.bind(this.subView.getTfBairro(),"bairro");
			fieldGroup.bind(this.subView.getTfEmail(),"email");
			fieldGroup.bind(this.subView.getTfCnpj(), "cnpj");
			
			fieldGroup.bind(this.subView.getTfTelefone1(),"fone1");
			fieldGroup.bind(this.subView.getTfTelefone2(),"fone2");
			
			//this.subView.getMocUf().configuraCombo(
			//		"nome", UfListController.class, this.ufDAO, this.getMainController());
			
			carregarUf();

			comboTipoSindicato();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			this.business.saveOrUpdate(this.entity);
			
			entity.setCnpj(this.subView.getTfCnpj().getValue());

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
			this.entity = new SindicatoEntity();
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
				SindicatoEntity sindicato = (SindicatoEntity) id;

				try {
					business.delete(sindicato);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

	/**
	 * 
	 */

	public void comboTipoSindicato() {
		for (TipoSindicatoEn en : TipoSindicatoEn.values()) {
			this.subView.getCbTipo().addItem(en);
		}
	}
	
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