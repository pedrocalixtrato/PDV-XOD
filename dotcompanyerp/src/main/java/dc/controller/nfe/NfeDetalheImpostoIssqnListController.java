package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIssqnDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class NfeDetalheImpostoIssqnListController extends
		CRUDListController<NfeDetalheImpostoIssqnEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheImpostoIssqnDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private NfeDetalheImpostoIssqnFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigoNumerico", "naturezaOperacao",
				"indicadorFormaPagamento" };
	}

	@Override
	public Class<? super NfeDetalheImpostoIssqnEntity> getEntityClass() {
		return NfeDetalheImpostoIssqnEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Produto / serviço";
	}

	@Override
	protected List<NfeDetalheImpostoIssqnEntity> pesquisa(String valor) {
		try {
			List<NfeDetalheImpostoIssqnEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoIssqnEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeDetalheImpostoIssqnEntity> getFormController() {
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
	protected List<NfeDetalheImpostoIssqnEntity> pesquisaDefault() {
		try {
			List<NfeDetalheImpostoIssqnEntity> auxLista = this.pDAO
					.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoIssqnEntity>();
		}
	}

}