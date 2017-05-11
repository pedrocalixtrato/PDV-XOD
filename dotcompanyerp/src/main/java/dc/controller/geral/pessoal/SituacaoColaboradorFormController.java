package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.servicos.dao.geral.pessoal.ISituacaoColaboradorDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.SituacaoColaboradorFormView;

@Controller
@Scope("prototype")
public class SituacaoColaboradorFormController extends
		CRUDFormController<SituacaoColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SituacaoColaboradorFormView subView;

	@Autowired
	private ISituacaoColaboradorDAO situacaoColaboradorDAO;

	private SituacaoColaboradorEntity currentBean;

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
	protected void criarNovoBean() {
		try {
			this.currentBean = new SituacaoColaboradorEntity();
			
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SituacaoColaboradorFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(SituacaoColaboradorEntity.class);

	        fieldGroup.bind(this.subView.getTfNome(), "nome");
	        fieldGroup.bind(this.subView.getTfCodigo(), "codigo");
	        fieldGroup.bind(this.subView.getTfDescricao(), "descricao");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.situacaoColaboradorDAO.find(id);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.situacaoColaboradorDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected String getNome() {
		return "Situação do Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.situacaoColaboradorDAO.deleteAllByIds(ids);

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
	protected Component getSubView() {
		return subView;
	}

	@Override
	public SituacaoColaboradorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}