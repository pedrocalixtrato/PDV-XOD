package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CodigoGpsEntity;
import dc.servicos.dao.geral.tabela.ICodigoGpsDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CodigoGpsFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CodigoGpsFormController extends CRUDFormController<CodigoGpsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CodigoGpsFormView subView;

	@Autowired
	private ICodigoGpsDAO codigoGpsDAO;

	private CodigoGpsEntity currentBean;

	@Override
	protected String getNome() {
		return "Código GPS";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			codigoGpsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = codigoGpsDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new CodigoGpsFormView();
			
			this.fieldGroup = new DCFieldGroup<>(CodigoGpsEntity.class);
			fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
            fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		
		try {
			
			currentBean = new CodigoGpsEntity();
			
			fieldGroup.setItemDataSource(this.currentBean);
			
        } catch (Exception e) {
			e.printStackTrace();
			
			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.codigoGpsDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected boolean validaSalvar() {
		
		try {
            // Commit tenta transferir os dados do View para o Model, levando em conta os critérios de validação
            fieldGroup.commit();

			return true;
		} catch (FieldGroup.CommitException ce) {
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
	public boolean isFullSized() {
		return true;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public CodigoGpsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}