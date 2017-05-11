package dc.controller.nfe;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.NfeCabecalhoFormView;

@Controller
@Scope("prototype")
public class NfeCabecalhoFormController extends CRUDFormController<NfeCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NfeCabecalhoFormView subView;

	private NfeDestinatarioFormController nfeDestinatarioFormController;

	public NfeDestinatarioFormController getNfeDestinatarioFormController() {
		return nfeDestinatarioFormController;
	}

	public void setNfeDestinatarioFormController(NfeDestinatarioFormController nfeDestinatarioFormController) {
		this.nfeDestinatarioFormController = nfeDestinatarioFormController;
	}

	/** DAO'S */

	/** ENTITIES */

	/** CONSTRUTOR */

	public NfeCabecalhoFormController() {
		// this.ndiCofins = ndiCofins;
	}

	@Override
	protected String getNome() {
		return "Produto / serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {

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
		this.subView = new NfeCabecalhoFormView(this);
		this.nfeDestinatarioFormController.initSubView();
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
	public void criarNovo() {
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
		System.out.println(":::: cabecalho");

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		return "";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ************************************** */

	/** SETAR VALORES
	 * 
	 * @param id
	 * @param event */

	public void nfeCabecalhoSetarValor(String id, Object obj) {
		System.out.println(" ::::::: " + this.getNfeDestinatarioFormController().validaSalvar());
		System.out.println();
	}

	@Override
	public NfeCabecalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return null;
	}

}