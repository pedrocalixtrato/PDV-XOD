package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.PlanoCentroResultado;
import dc.servicos.dao.financeiro.IPlanoCentroResultadoDAO;
import dc.visao.financeiro.PlanoCentroResultadoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PlanoCentroResultadoFormController extends CRUDFormController<PlanoCentroResultado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoCentroResultadoFormView subView;

	@Autowired
	private IPlanoCentroResultadoDAO planocentroresultadoDAO;

	private PlanoCentroResultado currentBean;

	@Override
	protected String getNome() {
		return "Plano Centro Resultado";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			planocentroresultadoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = planocentroresultadoDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
        try {
			
			subView = new PlanoCentroResultadoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(PlanoCentroResultado.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtNome(),"nome");
	        fieldGroup.bind(this.subView.getTxtMascara(),"mascara");
	        fieldGroup.bind(this.subView.getTxtNiveis(),"niveis");
	        fieldGroup.bind(this.subView.getDtDataInclusao(),"dataInclusao");
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }


	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
	        this.currentBean = new PlanoCentroResultado();

	        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
	        fieldGroup.setItemDataSource(this.currentBean);

	    } catch (Exception e) {
	        e.printStackTrace();
	        mensagemErro(e.getMessage());
	    }
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			//this.business.deleteAll(ids);
			this.planocentroresultadoDAO.deleteAll(ids);

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
	protected boolean validaSalvar() {
		try {
			 // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {
		    return false;
		}
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
	public PlanoCentroResultado getModelBean() {
		return currentBean;
	}
}