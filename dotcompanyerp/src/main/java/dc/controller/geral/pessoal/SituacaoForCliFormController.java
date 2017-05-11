package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.servicos.dao.geral.pessoal.ISituacaoForCliDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.SituacaoForCliFormView;

@Controller
@Scope("prototype")
public class SituacaoForCliFormController extends
		CRUDFormController<SituacaoForCliEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SituacaoForCliFormView subView;

	@Autowired
	private ISituacaoForCliDAO situacaoForCliDAO;

	private SituacaoForCliEntity currentBean;

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
			currentBean = new SituacaoForCliEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SituacaoForCliFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(SituacaoForCliEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTxtNome(),"nome");
			fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.situacaoForCliDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			situacaoForCliDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			mensagemErro(ex.getMessage());
		}
	}

	@Override
	protected String getNome() {
		return "Situação Cliente/Fornecedor";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.situacaoForCliDAO.deleteAllByIds(ids);

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
	public SituacaoForCliEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}