package dc.controller.nfe;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.servicos.dao.nfe.INfeDestinatarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.NfeDestinatarioFormView;

@Controller
@Scope("prototype")
public class NfeDestinatarioFormController extends CRUDFormController<NfeDestinatarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NfeDestinatarioFormView subView;

	private NfeCabecalhoFormController nfeCabecalhoFormController;

	public NfeCabecalhoFormController getNfeCabecalhoFormController() {
		return nfeCabecalhoFormController;
	}

	public void setNfeCabecalhoFormController(NfeCabecalhoFormController nfeCabecalhoFormController) {
		this.nfeCabecalhoFormController = nfeCabecalhoFormController;
	}

	/** DAO'S */

	@Autowired
	private INfeDestinatarioDAO nfeDestinatarioDAO;

	/** ENTITIES */

	private NfeDestinatarioEntity nfeDestinatario;

	/** CONSTRUTOR */

	public NfeDestinatarioFormController() {
		// this.ndiCofins = ndiCofins;
	}

	@Override
	protected String getNome() {
		return "NFE Destinatário";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

	}

	@Override
	protected void carregar(Serializable id) {

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
		//this.subView = new NfeDestinatarioFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {

	}

	@Override
	public void criarNovo() {

	}

	@Override
	protected void remover(List<Serializable> ids) {

	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		System.out.println(":::: cabecalho");

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

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

	public void nfeDestinatarioSetarValor(String id, Object obj) {
		System.out.println(" ::::::: " + this.getNfeCabecalhoFormController().validaSalvar());
		System.out.println();
	}

	@Override
	public NfeDestinatarioEntity getModelBean() {
		// TODO Auto-generated method stub
		return nfeDestinatario;
	}

}