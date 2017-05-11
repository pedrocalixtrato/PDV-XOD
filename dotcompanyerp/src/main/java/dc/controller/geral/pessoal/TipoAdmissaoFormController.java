package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TipoAdmissaoEntity;
import dc.servicos.dao.geral.pessoal.ITipoAdmissaoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.TipoAdmissaoFormView;

@Controller
@Scope("prototype")
public class TipoAdmissaoFormController extends
		CRUDFormController<TipoAdmissaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoAdmissaoFormView subView;

	@Autowired
	private ITipoAdmissaoDAO tipoAdmissaoDAO;

	private TipoAdmissaoEntity currentBean;

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
			this.currentBean = new TipoAdmissaoEntity();
			
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new TipoAdmissaoFormView();
			
			this.fieldGroup = new DCFieldGroup<>(TipoAdmissaoEntity.class);

	        fieldGroup.bind(this.subView.getTxtNome(), "nome");
	        fieldGroup.bind(this.subView.getTxtCodigo(), "codigo");
	        fieldGroup.bind(this.subView.getTxtDescricao(), "descricao");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.tipoAdmissaoDAO.find(id);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {
		try {
			tipoAdmissaoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			mensagemErro(ex.getMessage());
		}
	}


	@Override
	protected String getNome() {
		return "Tipo Admissão";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.tipoAdmissaoDAO.deleteAllByIds(ids);

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
	public TipoAdmissaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}