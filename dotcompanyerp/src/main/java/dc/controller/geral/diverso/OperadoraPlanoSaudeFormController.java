package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.model.business.geral.diverso.OperadoraPlanoSaudeBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.OperadoraPlanoSaudeFormView;

@Controller
@Scope("prototype")
public class OperadoraPlanoSaudeFormController extends
		CRUDFormController<OperadoraPlanoSaudeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperadoraPlanoSaudeFormView subView;

	/**
	 * ENTITY
	 */

	private OperadoraPlanoSaudeEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private OperadoraPlanoSaudeBusiness<OperadoraPlanoSaudeEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public OperadoraPlanoSaudeFormController() {
		// TODO Auto-generated constructor stub
	}

	public OperadoraPlanoSaudeBusiness<OperadoraPlanoSaudeEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Operadora de Plano de Saúde";
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
	public OperadoraPlanoSaudeEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new OperadoraPlanoSaudeFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(OperadoraPlanoSaudeEntity.class);
            fieldGroup.bind(this.subView.getTfRegistroAns(),"registroAns");
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
			this.entity = new OperadoraPlanoSaudeEntity();
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
				OperadoraPlanoSaudeEntity operadoraPlanoSaude = (OperadoraPlanoSaudeEntity) id;

				try {
					business.delete(operadoraPlanoSaude);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}
}