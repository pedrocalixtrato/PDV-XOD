package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.TransportadoraFormView;

@Controller
@Scope("prototype")
public class TransportadoraFormController extends CRUDFormController<TransportadoraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TransportadoraFormView subView;

	/**
	 * ENTITY
	 */

	private TransportadoraEntity entity;

	/**
	 * BUSINESS
	 */

	/**
	 * DAO
	 */

	@Autowired
	private ITransportadoraDAO dao;

	@Autowired
	private IPessoaDAO pessoaDAO;

	/**
	 * CONSTRUTOR
	 */

	public TransportadoraFormController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getNome() {
		return "Transportadora";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public TransportadoraEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new TransportadoraFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(TransportadoraEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getMocPessoa(),"pessoa");
			fieldGroup.bind(this.subView.getTaObservacao(),"observacao");
			
			this.subView.getMocPessoa().configuraCombo(
					"nome", PessoaListController.class, this.pessoaDAO, this.getMainController());


		} catch (Exception e) {
			e.printStackTrace();
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
	protected void actionSalvar() {
		try {
			this.dao.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.dao.find(id);
			
			fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new TransportadoraEntity();
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.dao.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		for (Serializable id : objetos) {
			TransportadoraEntity transp = (TransportadoraEntity) id;

			try {
				dao.delete(transp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

}