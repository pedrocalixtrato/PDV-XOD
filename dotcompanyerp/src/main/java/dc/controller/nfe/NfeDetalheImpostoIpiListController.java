package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheImpostoIpiEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIpiDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class NfeDetalheImpostoIpiListController extends
		CRUDListController<NfeDetalheImpostoIpiEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheImpostoIpiDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private NfeDetalheImpostoIpiFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigoNumerico", "naturezaOperacao",
				"indicadorFormaPagamento" };
	}

	@Override
	public Class<? super NfeDetalheImpostoIpiEntity> getEntityClass() {
		return NfeDetalheImpostoIpiEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Produto / serviço";
	}

	@Override
	protected List<NfeDetalheImpostoIpiEntity> pesquisa(String valor) {
		try {
			List<NfeDetalheImpostoIpiEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeDetalheImpostoIpiEntity> getFormController() {
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
	protected List<NfeDetalheImpostoIpiEntity> pesquisaDefault() {
		try {
			List<NfeDetalheImpostoIpiEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

}