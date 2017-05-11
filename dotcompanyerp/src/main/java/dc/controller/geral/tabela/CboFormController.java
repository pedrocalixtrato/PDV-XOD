package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CboEntity;
import dc.model.business.geral.tabela.CboBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CBOFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CboFormController extends CRUDFormController<CboEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CBOFormView subView;

	//private CboDAO cboDAO;

	private CboEntity currentBean;
	
	@Autowired
	private CboBusiness<CboEntity> business;

	
	public CboFormController() {
	}
	
	public CboBusiness<CboEntity> getBusiness() {
		return business;
	}
	
	@Override
	protected String getNome() {
		return "CBO";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.business.find(id);

			// Atribui a entidade carregada como origem de dados dos campos do formulario
            // no FieldGroup
            fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void initSubView() {
		
		try {
			this.subView = new CBOFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(CboEntity.class);
			fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
            fieldGroup.bind(this.subView.getTxtNome(),"nome");
            fieldGroup.bind(this.subView.getTxtObservacao(),"observacao");

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void criarNovoBean() {
		
		try {
			currentBean = new CboEntity();
			
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
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
	protected boolean validaSalvar() {
		
		try {
			
            fieldGroup.commit();
            return true;

		}catch (FieldGroup.CommitException ce) {
			return false;
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

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	@Override
	public CboEntity getModelBean() {
		return currentBean;
	}

}