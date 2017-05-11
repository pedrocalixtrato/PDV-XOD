package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.servicos.dao.ordemservico.InformacaoGeralDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.InformacaoGeralFormView;

/** @author Gutemberg A. Da Silva */

@Controller
@Scope("prototype")
public class InformacaoGeralFormController extends CRUDFormController<InformacaoGeralEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InformacaoGeralFormView subView;

	/** DAO'S */

	@Autowired
	private InformacaoGeralDAO pDAO;

	/** ENTITIES */

	private InformacaoGeralEntity pEntity;

	/** CONSTRUTOR */

	public InformacaoGeralFormController() {
		if (this.pEntity == null) {
			this.pEntity = new InformacaoGeralEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Informação geral";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			// notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {

		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		// this.subView = new InformacaoGeralFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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
		return "ordemservico_informacaogeral_fc";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public InformacaoGeralEntity getModelBean() {
		// TODO Auto-generated method stub
		return null;
	}

}