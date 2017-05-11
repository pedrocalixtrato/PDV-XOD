package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.servicos.dao.geral.pessoal.IAtividadeForCliDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.AtividadeForCliFormView;

@Controller
@Scope("prototype")
public class AtividadeForCliFormController extends
		CRUDFormController<AtividadeForCliEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AtividadeForCliFormView subView;

	private AtividadeForCliEntity currentBean;

	@Autowired
	private IAtividadeForCliDAO atividadeForCliDAO;

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
	protected void criarNovoBean() {
		try {
			currentBean = new AtividadeForCliEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new AtividadeForCliFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(AtividadeForCliEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			fieldGroup.bind(this.subView.getTfDescricao(),"descricao");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.atividadeForCliDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.atividadeForCliDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected String getNome() {
		return "Atividade fornecedor / cliente";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.atividadeForCliDAO.deleteAllByIds(ids);

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
	public AtividadeForCliEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}