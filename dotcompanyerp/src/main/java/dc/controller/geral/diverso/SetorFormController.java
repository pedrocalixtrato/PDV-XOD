package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.SetorEntity;
import dc.model.business.geral.diverso.SetorBusiness;
import dc.servicos.dao.geral.IUfDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.SetorFormView;

@Controller
@Scope("prototype")
public class SetorFormController extends CRUDFormController<SetorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SetorFormView subView;

	/**
	 * ENTITY
	 */

	private SetorEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private SetorBusiness<SetorEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IUfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public SetorFormController() {
		// TODO Auto-generated constructor stub
	}

	public SetorBusiness<SetorEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Setor";
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
	public SetorEntity getModelBean() {
		return entity;
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
	protected void initSubView() {
		try {
			this.subView = new SetorFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(SetorEntity.class);
            fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
            fieldGroup.bind(this.subView.getTfNome(),"nome");
		} catch (Exception e) {
			e.printStackTrace();
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
			this.entity = new SetorEntity();
			
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
				SetorEntity setor = (SetorEntity) id;

				try {
					business.delete(setor);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}
}