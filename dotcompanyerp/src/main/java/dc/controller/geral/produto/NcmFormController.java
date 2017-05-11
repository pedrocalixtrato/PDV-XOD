package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.NcmEntity;
import dc.model.business.geral.produto.NcmBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.NcmFormView;

@Controller
@Scope("prototype")
public class NcmFormController extends CRUDFormController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NcmFormView subView;

	/**
	 * ENTITY
	 */

	private NcmEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private NcmBusiness<NcmEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public NcmFormController() {
		// TODO Auto-generated constructor stub
	}

	public NcmBusiness<NcmEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "NCM";
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
	public NcmEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new NcmFormView(this);
			
			 this.fieldGroup = new DCFieldGroup<>(NcmEntity.class);
			 
			 // Mapeia os campos
			 fieldGroup.bind(this.subView.getTfCodigo(),"codigo");
			 fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
			 fieldGroup.bind(this.subView.getTfObservacao(),"observacao");
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

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new NcmEntity();
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