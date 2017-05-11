package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.NivelFormacaoEntity;
import dc.model.business.geral.pessoal.NivelFormacaoBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.NivelFormacaoFormView;

@Controller
@Scope("prototype")
public class NivelFormacaoFormController extends
		CRUDFormController<NivelFormacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NivelFormacaoFormView subView;

	/**
	 * ENTITY
	 */

	private NivelFormacaoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private NivelFormacaoBusiness<NivelFormacaoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public NivelFormacaoFormController() {
		// TODO Auto-generated constructor stub
	}

	public NivelFormacaoBusiness<NivelFormacaoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Nível Formação";
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
	public NivelFormacaoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new NivelFormacaoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(NivelFormacaoEntity.class);

	        fieldGroup.bind(this.subView.getTfNome(), "nome");
	        fieldGroup.bind(this.subView.getTfDescricao(), "descricao");
			
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
			this.entity = new NivelFormacaoEntity();
			
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