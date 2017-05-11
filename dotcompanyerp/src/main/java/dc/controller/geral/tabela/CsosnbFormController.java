package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.servicos.dao.geral.tabela.ICsosnbDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CsosnbFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CsosnbFormController extends CRUDFormController<CsosnbEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CsosnbFormView subView;

	@Autowired
	private ICsosnbDAO csosnbDAO;

	private CsosnbEntity currentBean;

	@Override
	protected String getNome() {
		return "CSOSN B";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			csosnbDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.csosnbDAO.find(id);

			// Atribui a entidade carregada como origem de dados dos campos do formulario
            // no FieldGroup
            fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		try {
            this.subView = new CsosnbFormView(this);

            this.fieldGroup = new DCFieldGroup<>(CsosnbEntity.class);
            fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
            fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
            fieldGroup.bind(this.subView.getTxtObservacao(),"observacao");

        } catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {

		try {

			this.currentBean = new CsosnbEntity();

            // Atribui a entidade nova como origem de dados dos campos do formulario
            // no FieldGroup
            fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.csosnbDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected boolean validaSalvar() {
		
		try {
            // Commit tenta transferir os dados do View para o Model, levando em conta os critérios de validação
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
	public boolean isFullSized() {
		return true;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public CsosnbEntity getModelBean() {
		return currentBean;
	}

}