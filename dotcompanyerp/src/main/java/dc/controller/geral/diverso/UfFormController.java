package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.PaisEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.model.business.geral.diverso.PaisBusiness;
import dc.model.business.geral.diverso.UfBusiness;
import dc.servicos.dao.geral.diverso.IPaisDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.UfFormView;

@Controller
@Scope("prototype")
public class UfFormController extends CRUDFormController<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UfFormController.class);

	private UfFormView subView;

	/**
	 * ENTITY
	 */

	private UfEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private UfBusiness<UfEntity> business;

	@Autowired
	private PaisBusiness<PaisEntity> paisBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private IPaisDAO paisDAO;

	/**
	 * CONSTRUTOR
	 */

	public UfFormController() {
		// TODO Auto-generated constructor stub
	}

	public UfBusiness<UfEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "UF";
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
	public UfEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UfFormView(this);

			this.fieldGroup = new DCFieldGroup<>(UfEntity.class);

	        fieldGroup.bind(this.subView.getTfNome(), "nome");
	        fieldGroup.bind(this.subView.getMocPais(), "pais");
	        fieldGroup.bind(this.subView.getTfSigla(), "sigla");
	        fieldGroup.bind(this.subView.getTfCodigoIbge(), "codigoIbge");
	        this.subView.getMocPais().configuraCombo("nomePtbr", PaisListController.class, this.paisDAO, this.getMainController());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			this.entity = new UfEntity();
			
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