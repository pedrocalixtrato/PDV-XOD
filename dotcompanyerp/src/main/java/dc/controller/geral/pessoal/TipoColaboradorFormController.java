package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.servicos.dao.geral.pessoal.ITipoColaboradorDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.TipoColaboradorFormView;

@Controller
@Scope("prototype")
public class TipoColaboradorFormController extends
		CRUDFormController<TipoColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoColaboradorFormView subView;

	@Autowired
	private ITipoColaboradorDAO tipoColaboradorDAO;

	private TipoColaboradorEntity currentBean;

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
			this.currentBean = new TipoColaboradorEntity();
			
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			subView = new TipoColaboradorFormView();
			
			this.fieldGroup = new DCFieldGroup<>(TipoColaboradorEntity.class);

	        fieldGroup.bind(this.subView.getTxtNome(), "nome");
	        fieldGroup.bind(this.subView.getTxtDescricao(), "descricao");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.tipoColaboradorDAO.find(id);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {
		try {
			tipoColaboradorDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			mensagemErro(ex.getMessage());
		}
	}

	@Override
	protected String getNome() {
		return "Tipo Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		try {
			this.tipoColaboradorDAO.deleteAllByIds(ids);

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
	public TipoColaboradorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}