package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.PaisEntity;
import dc.model.business.geral.diverso.PaisBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.PaisFormView;

@Controller
@Scope("prototype")
public class PaisFormController extends CRUDFormController<PaisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PaisFormView subView;

	/**
	 * ENTITY
	 */

	private PaisEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private PaisBusiness<PaisEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public PaisFormController() {

	}

	public PaisBusiness<PaisEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "País";
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
	public PaisEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PaisFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(PaisEntity.class);

	        fieldGroup.bind(this.subView.getTfNome(), "nomePtbr");
	        fieldGroup.bind(this.subView.getTfNomeIngles(), "nomeIngles");
	        fieldGroup.bind(this.subView.getTfCodigo(), "codigo");
	        fieldGroup.bind(this.subView.getTfSigla2(), "sigla2");
	        fieldGroup.bind(this.subView.getTfSigla3(), "sigla3");
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
			this.entity = new PaisEntity();
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