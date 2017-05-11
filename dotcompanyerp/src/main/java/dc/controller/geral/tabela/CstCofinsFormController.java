package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CstCofinsEntity;
import dc.servicos.dao.geral.tabela.ICstCofinsDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CstCofinsFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CstCofinsFormController extends CRUDFormController<CstCofinsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CstCofinsFormView subView;

	@Autowired
	private ICstCofinsDAO cstCofinsDAO;

	private CstCofinsEntity currentBean;

	@Override
	protected String getNome() {
		return "Cst Cofins";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			cstCofinsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = cstCofinsDAO.find(id);

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
            this.subView = new CstCofinsFormView(this);

            this.fieldGroup = new DCFieldGroup<>(CstCofinsEntity.class);
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

			this.currentBean = new CstCofinsEntity();

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
			this.cstCofinsDAO.deleteAll(ids);

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
	public CstCofinsEntity getModelBean() {
		return currentBean;
	}

}