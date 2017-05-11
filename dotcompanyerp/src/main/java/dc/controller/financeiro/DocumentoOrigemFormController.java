package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.servicos.dao.financeiro.IDocumentoOrigemDAO;
import dc.visao.financeiro.DocumentoOrigemFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class DocumentoOrigemFormController extends CRUDFormController<DocumentoOrigem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentoOrigemFormView subView;

	@Autowired
	private IDocumentoOrigemDAO documentoorigemDAO;

	private DocumentoOrigem currentBean;

	@Override
	protected String getNome() {
		return "Documento Origem";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			documentoorigemDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = documentoorigemDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
        try {
			
			subView = new DocumentoOrigemFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(DocumentoOrigem.class);

	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
	        fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
	        fieldGroup.bind(this.subView.getTxtSiglaDocumento(),"siglaDocumento");
	        
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
	        this.currentBean = new DocumentoOrigem();

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
			this.documentoorigemDAO.deleteAll(ids);

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
	public DocumentoOrigem getModelBean() {
		return currentBean;
	}
}
