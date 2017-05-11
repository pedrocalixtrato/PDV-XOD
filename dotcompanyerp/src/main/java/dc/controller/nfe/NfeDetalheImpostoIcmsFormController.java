package dc.controller.nfe;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CstCofinsEn;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.ProdutoServicoFormView;


@Controller
@Scope("prototype")
public class NfeDetalheImpostoIcmsFormController extends CRUDFormController<NfeDetalheImpostoIcmsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormView subView;

	/** DAO'S */

	/** ENTITIES */

	private NfeDetalheImpostoCofinsEntity ndiCofins;

	/** CONSTRUTOR */

	public NfeDetalheImpostoIcmsFormController() {
		// this.ndiCofins = ndiCofins;
		if (this.ndiCofins == null) {
			this.ndiCofins = new NfeDetalheImpostoCofinsEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Produto / serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public void setSubView(ProdutoServicoFormView subView) {
		this.subView = subView;
	}

	public NfeDetalheImpostoCofinsEntity getNdiCofins() {
		return ndiCofins;
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

	/** NFEDETALHEIMPOSTOCOFINS */
	public NfeDetalheImpostoCofinsEntity ndiCofinsHinzufugen() {
		try {
			if (this.ndiCofins == null) {
				this.ndiCofins = new NfeDetalheImpostoCofinsEntity();
			}

			return this.ndiCofins;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public void ndiCofinsMachen() {
		try {
			/*
			 * this.subView.getTfQtdVendidaCofins().setValue(
			 * this.ndiCofins.getQuantidadeVendida().toString().trim());
			 * this.subView.getTfBaseCalculoBcCofins().setValue(
			 * this.ndiCofins.getBaseCalculoCofins().toString().trim());
			 * this.subView.getTfAliquotaPercentualCofins().setValue(
			 * this.ndiCofins.getAliquotaCofinsPercentual().toString() .trim());
			 * this.subView.getTfAliquotaReaisCofins().setValue(
			 * this.ndiCofins.getAliquotaCofinsReais().toString().trim());
			 * this.subView.getTfValorCofins().setValue(
			 * this.ndiCofins.getValorCofins().toString().trim());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** SETAR VALORES
	 * 
	 * @param id
	 * @param event */

	public void ndiCofinsSetarValor(String id, Object obj) {
		// TODO ndiCofinsSetarValor
		switch (id) {
		case "cbCstCofins":
			CstCofinsEn cstCofins = (CstCofinsEn) obj;
			String s1 = cstCofins.name().substring(1);

			this.ndiCofins.setCstCofins(s1);

			break;
		case "tfQtdVendidaCofins":
			this.ndiCofins.setQuantidadeVendida(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcCofins":
			this.ndiCofins.setBaseCalculoCofins(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfAliquotaPercentualCofins":
			this.ndiCofins.setAliquotaCofinsPercentual(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfAliquotaReaisCofins":
			this.ndiCofins.setAliquotaCofinsReais(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorCofins":
			this.ndiCofins.setValorCofins(ObjectConverter.stringToValue((String) obj));

			break;
		}

		/*
		 * Integer index = this.subView.getSfNfeDetalhe().getDados()
		 * .indexOf(this.nfeCabecalho.getNfeDetalhe());
		 * 
		 * this.subView.getSfNfeDetalhe().getDados()
		 * .remove(this.nfeCabecalho.getNfeDetalhe());
		 * 
		 * this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoCofins(ndiCofins
		 * );
		 * 
		 * this.subView.getSfNfeDetalhe().getDados() .add(index,
		 * this.nfeCabecalho.getNfeDetalhe());
		 */
	}

	@Override
	public NfeDetalheImpostoIcmsEntity getModelBean() {
		// TODO Auto-generated method stub
		return null;
	}

}