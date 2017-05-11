package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.servicos.dao.geral.tabela.ICfopDAO;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tributario.OperacaoFiscalFormView;

@Controller
@Scope("prototype")
public class OperacaoFiscalFormController extends CRUDFormController<OperacaoFiscalEntity> {
	
	private static final long serialVersionUID = 1L;

	OperacaoFiscalFormView subView;

	@Autowired
	private IOperacaoFiscalDAO dao;

	private OperacaoFiscalEntity currentBean;

	@Autowired
	private ICfopDAO cfopDAO;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
	protected void criarNovoBean() {
		try {
			this.currentBean = new OperacaoFiscalEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			this.subView = new OperacaoFiscalFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(OperacaoFiscalEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getDescricao(),"descricao");
			fieldGroup.bind(this.subView.getDescricaoNaNf(),"descricaoNaNf");
			
			//this.subView.getCfop().configuraCombo(
			//		"descricao", CfopListController.class, this.cfopDAO, this.getMainController());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.dao.find(id);
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			mensagemErro("Erro!!");
			e.printStackTrace();
		}
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Operação Fiscal";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.dao.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public OperacaoFiscalEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}