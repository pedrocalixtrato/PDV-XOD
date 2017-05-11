package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.financeiro.IndiceEconomicoListController;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.servicos.dao.contabilidade.cadastro.IIndiceDAO;
import dc.servicos.dao.financeiro.IIndiceEconomicoDAO;
import dc.visao.contabilidade.cadastro.IndiceFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;


@Controller
@Scope("prototype")
public class IndiceFormController extends CRUDFormController<IndiceEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceFormView subView;

	/** DAO'S */

	@Autowired
	private IIndiceDAO pDAO;

	@Autowired
	private IIndiceEconomicoDAO ieDAO;

	/** ENTITIES */

	private IndiceEntity pEntity;

	/** CONSTRUTOR */

	public IndiceFormController() {
	}

	@Override
	protected String getNome() {
		return "Índice";
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
			
			        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
	        this.subView = new IndiceFormView(this);
	
	        // Cria o DCFieldGroup
	        this.fieldGroup = new DCFieldGroup<>(IndiceEntity.class);
	        // Mapeia os campos
	        fieldGroup.bind(this.subView.getTfPeriodicidade(),"periodicidade");
	        fieldGroup.bind(this.subView.getCbIndiceEconomico(),"indiceEconomico");
	        
	        this.subView.getCbIndiceEconomico().configuraCombo(
	        		"nome", IndiceEconomicoListController.class, this.ieDAO, this.getMainController());
	
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
			
			this.pEntity = new IndiceEntity();
			
			        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
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
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public IndiceEntity getModelBean() {
		return pEntity;
	}

}