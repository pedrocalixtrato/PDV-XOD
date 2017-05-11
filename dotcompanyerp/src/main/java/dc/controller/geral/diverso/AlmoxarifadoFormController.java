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
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.model.business.geral.diverso.AlmoxarifadoBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.AlmoxarifadoFormView;

@Controller
@Scope("prototype")
public class AlmoxarifadoFormController extends
		CRUDFormController<AlmoxarifadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(AlmoxarifadoFormController.class);

	private AlmoxarifadoFormView subView;

	/**
	 * ENTITY
	 */

	private AlmoxarifadoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private AlmoxarifadoBusiness<AlmoxarifadoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public AlmoxarifadoFormController() {
		// TODO Auto-generated constructor stub
	}

	public AlmoxarifadoBusiness<AlmoxarifadoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Almoxarifado";
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
	public AlmoxarifadoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new AlmoxarifadoFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(AlmoxarifadoEntity.class);
            fieldGroup.bind(this.subView.getTfNome(),"nome");

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
			this.entity = new AlmoxarifadoEntity();
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
			AlmoxarifadoEntity a = (AlmoxarifadoEntity) id;

			try {
				business.delete(a);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

}