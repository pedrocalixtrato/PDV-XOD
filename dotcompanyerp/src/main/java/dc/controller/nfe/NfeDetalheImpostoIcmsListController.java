package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIcmsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class NfeDetalheImpostoIcmsListController extends
		CRUDListController<NfeDetalheImpostoIcmsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheImpostoIcmsDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private NfeDetalheImpostoIcmsFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigoNumerico", "naturezaOperacao",
				"indicadorFormaPagamento" };
	}

	@Override
	public Class<? super NfeDetalheImpostoIcmsEntity> getEntityClass() {
		return NfeDetalheImpostoIcmsEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Produto / serviço";
	}

	@Override
	protected List<NfeDetalheImpostoIcmsEntity> pesquisa(String valor) {
		try {
			List<NfeDetalheImpostoIcmsEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoIcmsEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeDetalheImpostoIcmsEntity> getFormController() {
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
	protected List<NfeDetalheImpostoIcmsEntity> pesquisaDefault() {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}