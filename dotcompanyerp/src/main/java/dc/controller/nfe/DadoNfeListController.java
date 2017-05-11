package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.servicos.business.nfe.NfeCabecalhoBusiness;
import dc.servicos.dao.nfe.INfeDestinatarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class DadoNfeListController extends
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

	@Autowired
	private INfeDestinatarioDAO nfeDestinatarioDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DadoNfeFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigoNumerico", "naturezaOperacao",
				"indicadorFormaPagamento" };
	}

	@Override
	public Class<? super NfeCabecalhoEntity> getEntityClass() {
		return NfeCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Dado da nota fiscal eletrônica";
	}

	@Override
	protected List<NfeCabecalhoEntity> pesquisa(String valor) {
		try {
			List<NfeCabecalhoEntity> auxLista = this.pBusiness.find(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeCabecalhoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
			e.printStackTrace();

			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

}