package dc.controller.nfe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.servicos.business.nfe.NfeCabecalhoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class DadoNotaFiscalListController extends
		CRUDListController<NfeCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeCabecalhoBusiness<NfeCabecalhoEntity> pBusiness;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DadoNotaFiscalFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "tributOperacaoFiscal.nome", "ufEmitente",
				"codigoNumerico", "naturezaOperacao", "informacoesAddFisco",
				"informacoesAddContribuinte", "statusNota" };
	}

	@Override
	public Class<? super Object> getEntityClass() {
		return Object.class;
	}

	@Override
	protected String getTitulo() {
		return "Dados NF-e";
	}

	@Override
	protected List<NfeCabecalhoEntity> pesquisa(String valor) {
		try {
			List<NfeCabecalhoEntity> auxLista = this.pBusiness.find(valor);

			return auxLista;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected CRUDFormController<NfeCabecalhoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<NfeCabecalhoEntity> pesquisaDefault() {
		try {
			List<NfeCabecalhoEntity> auxLista = this.pBusiness.listAll();

			return auxLista;
		} catch (Exception e) {
			return null;
		}
	}

}