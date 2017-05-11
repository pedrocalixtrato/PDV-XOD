package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CfopEntity;
import dc.servicos.dao.geral.tabela.ICfopDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CfopFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CfopFormController extends CRUDFormController<CfopEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CfopFormView subView;

	@Autowired
	private ICfopDAO cfopDAO;

	private CfopEntity currentBean;

	@Override
	protected String getNome() {
		return "Cfop";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			cfopDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = cfopDAO.find(id);
            fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new CfopFormView();
			
            this.fieldGroup = new DCFieldGroup<>(CfopEntity.class);
            fieldGroup.bind(this.subView.getTxtCfop(),"cfop");
            fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
            fieldGroup.bind(this.subView.getTxtAplicacao(),"aplicacao");

			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		
		try {
			currentBean = new CfopEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		try {
			cfopDAO.deleteAllByIds(ids);
			mensagemRemovidoOK();
			
		}catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
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
	protected void removerEmCascata(List<Serializable> ids) {

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
	public CfopEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}