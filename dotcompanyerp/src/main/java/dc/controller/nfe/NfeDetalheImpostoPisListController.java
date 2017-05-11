package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoPisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class NfeDetalheImpostoPisListController extends
		CRUDListController<NfeDetalheImpostoPisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheImpostoPisDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private NfeDetalheImpostoPisFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigoNumerico", "naturezaOperacao",
				"indicadorFormaPagamento" };
	}

	@Override
	public Class<? super NfeDetalheImpostoPisEntity> getEntityClass() {
		return NfeDetalheImpostoPisEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Produto / serviço";
	}

	@Override
	protected List<NfeDetalheImpostoPisEntity> pesquisa(String valor) {
		try {
			List<NfeDetalheImpostoPisEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoPisEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeDetalheImpostoPisEntity> getFormController() {
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
	protected List<NfeDetalheImpostoPisEntity> pesquisaDefault() {
		try {
			List<NfeDetalheImpostoPisEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoPisEntity>();
		}
	}

}