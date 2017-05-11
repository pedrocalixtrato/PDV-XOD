package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.MunicipioEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.model.business.geral.diverso.MunicipioBusiness;
import dc.servicos.dao.geral.IUfDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.MunicipioFormView;

@Controller
@Scope("prototype")
public class MunicipioFormController extends
		CRUDFormController<MunicipioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MunicipioFormView subView;

	/**
	 * ENTITY
	 */

	private MunicipioEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private MunicipioBusiness<MunicipioEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IUfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public MunicipioFormController() {
		// TODO Auto-generated constructor stub
	}

	public MunicipioBusiness<MunicipioEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Município";
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
	public MunicipioEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new MunicipioFormView(this);

			this.fieldGroup = new DCFieldGroup<>(MunicipioEntity.class);

	        fieldGroup.bind(this.subView.getTfNome(), "nome");
	        fieldGroup.bind(this.subView.getCmbUf(), "uf");
	        fieldGroup.bind(this.subView.getTfCodigoIbge(), "codigoIbge");
	        fieldGroup.bind(this.subView.getTfCodigoReceitaFederal(), "codigoReceitaFederal");
	        fieldGroup.bind(this.subView.getTfCodigoEstadual(), "codigoEstadual");
	        carregarUf();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.getAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getCmbUf().setContainerDataSource(bic);
			this.subView.getCmbUf().setItemCaptionPropertyId("nome");
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new MunicipioEntity();
			
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
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

}