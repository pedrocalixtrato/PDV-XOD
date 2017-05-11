package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.TipoRecebimento;
import dc.servicos.dao.financeiro.ITipoRecebimentoDAO;
import dc.visao.financeiro.TipoRecebimentoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class TipoRecebimentoFormController extends CRUDFormController<TipoRecebimento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoRecebimentoFormView subView;

	@Autowired
	private ITipoRecebimentoDAO tipoRecebimentoDAO;

	private TipoRecebimento currentBean;

	@Override
	protected String getNome() {
		return "Tipo Recebimento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			tipoRecebimentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = tipoRecebimentoDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			
			subView = new TipoRecebimentoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(TipoRecebimento.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtTipo(),"tipo");
	        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");

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
	        this.currentBean = new TipoRecebimento();

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
			this.tipoRecebimentoDAO.deleteAll(ids);

			mensagemRemovidoOK();
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
	public TipoRecebimento getModelBean() {
		return currentBean;
	}
}
