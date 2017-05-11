package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.CepEntity;
import dc.model.business.geral.diverso.CepBusiness;
import dc.servicos.dao.geral.IUfDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.CepFormView;

@Controller
@Scope("prototype")
public class CepFormController extends CRUDFormController<CepEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CepFormView subView;

	/**
	 * ENTITY
	 */

	private CepEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private CepBusiness<CepEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IUfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public CepFormController() {
		// TODO Auto-generated constructor stub
	}

	public CepBusiness<CepEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "CEP";
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
	public CepEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new CepFormView(this);

			this.fieldGroup = new DCFieldGroup<>(CepEntity.class);

	        fieldGroup.bind(this.subView.getTfCep(), "cep");
	        fieldGroup.bind(this.subView.getMocUf(), "uf");
	        fieldGroup.bind(this.subView.getTfLogradouro(), "logradouro");
	        fieldGroup.bind(this.subView.getTfComplemento(), "complemento");
	        fieldGroup.bind(this.subView.getTfBairro(), "bairro");
	        fieldGroup.bind(this.subView.getTfMunicipio(), "municipio");
	        fieldGroup.bind(this.subView.getTfCodigoIbgeMunicipio(), "codigoIbgeMunicipio");

	        // Configura os ManyToOneComboFields
	        this.subView.getMocUf().configuraCombo("sigla", UfListController.class, this.ufDAO, this.getMainController());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {

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
			this.entity = new CepEntity();
			
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
				CepEntity cep = (CepEntity) id;

				try {
					business.delete(cep);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

}