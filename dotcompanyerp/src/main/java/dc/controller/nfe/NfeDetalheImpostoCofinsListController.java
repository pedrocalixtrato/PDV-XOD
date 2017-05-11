package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoCofinsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class NfeDetalheImpostoCofinsListController extends
		CRUDListController<NfeDetalheImpostoCofinsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheImpostoCofinsDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private NfeDetalheImpostoCofinsFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigoNumerico", "naturezaOperacao",
				"indicadorFormaPagamento" };
	}

	@Override
	public Class<? super NfeDetalheImpostoCofinsEntity> getEntityClass() {
		return NfeDetalheImpostoCofinsEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Produto / serviço";
	}

	@Override
	protected List<NfeDetalheImpostoCofinsEntity> pesquisa(String valor) {
		try {
			List<NfeDetalheImpostoCofinsEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoCofinsEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeDetalheImpostoCofinsEntity> getFormController() {
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
	protected List<NfeDetalheImpostoCofinsEntity> pesquisaDefault() {
		try {
			List<NfeDetalheImpostoCofinsEntity> auxLista = this.pDAO
					.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheImpostoCofinsEntity>();
		}
	}

}