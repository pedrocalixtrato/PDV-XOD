package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.AlteracaoSalarialEntity;
import dc.servicos.dao.folhapagamento.movimento.AlteracaoSalarialDAO;
import dc.visao.folhapagamento.movimento.AlteracaoSalarialFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class AlteracaoSalarialFormController extends
		CRUDFormController<AlteracaoSalarialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AlteracaoSalarialFormView subView;

	/** DAO'S */

	@Autowired
	private AlteracaoSalarialDAO pDAO;

	/** ENTITIES */

	private AlteracaoSalarialEntity pEntity;

	/** CONSTRUTOR */

	public AlteracaoSalarialFormController() {
		if (this.pEntity == null) {
			this.pEntity = new AlteracaoSalarialEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Servico";
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
		} finally {

		}
	}

	@Override
	protected void carregar(Serializable id) {
		this.pEntity = this.pDAO.find(id);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		this.subView = new AlteracaoSalarialFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.pEntity = new AlteracaoSalarialEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		this.pDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public AlteracaoSalarialEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

	/** COMBOS */

}