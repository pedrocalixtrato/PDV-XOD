package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.servicos.dao.suprimentos.compra.ICotacaoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.FornecedorCotacaoFormView;

@Controller
@Scope("prototype")
public class FornecedorCotacaoFormController extends CRUDFormController<FornecedorCotacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FornecedorCotacaoFormView subView;

	@Autowired
	private ICotacaoDAO cotacaoDao;

	private FornecedorCotacaoEntity currentBean;

	@Override
	protected String getNome() {
		return "Fornecedor Cotação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			cotacaoDao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			//this.currentBean = this.cotacaoDao.find(id);
			
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			subView = new FornecedorCotacaoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(FornecedorCotacaoEntity.class);
		
     	   // fieldGroup.bind(this.subView.getCmbCotacaoCompra(), "cotacao");
     	    
     	  // this.subView.getCmbCotacaoCompra().configuraCombo(
			//		"situacao", CotacaoListController.class, this.cotacaoDao, this.getMainController());
     	   
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new FornecedorCotacaoEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.cotacaoDao.deleteAll(ids);

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
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			CotacaoCompraEntity cotacao = (CotacaoCompraEntity) id;

			try {
				cotacaoDao.delete(cotacao);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
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
	public FornecedorCotacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}