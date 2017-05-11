package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.folhapagamento.movimento.IPppDAO;
import dc.visao.folhapagamento.movimento.PppFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PppFormController extends CRUDFormController<PppEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PppFormView subView;

	/** DAO'S */

	@Autowired
	private IPppDAO pDAO;

	@Autowired
	private IColaboradorDAO cDAO;

	/** ENTITIES */

	private PppEntity pEntity;

	/** CONSTRUTOR */

	public PppFormController() {
	}

	@Override
	protected String getNome() {
		return "PPP";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
        try {
			
			this.subView = new PppFormView(this);
			this.fieldGroup = new DCFieldGroup<>(PppEntity.class);
			
			fieldGroup.bind(this.subView.getTfObservacao(),"observacao");
			fieldGroup.bind(this.subView.getCbColaborador(),"colaborador");
			
			 this.subView.getCbColaborador().configuraCombo(
					 "pessoa.nome", ColaboradorListController.class, this.cDAO, this.getMainController());
			
		}catch(Exception e) {
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
			this.pEntity = new PppEntity();
			
			fieldGroup.setItemDataSource(this.pEntity);

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
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
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PppEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}