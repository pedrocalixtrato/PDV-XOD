package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.ApoliceSeguroEntity;
import dc.servicos.dao.patrimonio.ApoliceSeguroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class ApoliceSeguroListController extends CRUDListController<ApoliceSeguroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ApoliceSeguroDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ApoliceSeguroFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "numero", "bem", "seguradora" };
	}

	@Override
	public Class<? super ApoliceSeguroEntity> getEntityClass() {
		return ApoliceSeguroEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Apólice do seguro";
	}

	@Override
	protected List<ApoliceSeguroEntity> pesquisa(String valor) {
		try {
			List<ApoliceSeguroEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ApoliceSeguroEntity>();
		}
	}

	@Override
	protected CRUDFormController<ApoliceSeguroEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_apolice_seguro_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ApoliceSeguroEntity> pesquisaDefault() {
		try {
			List<ApoliceSeguroEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ApoliceSeguroEntity>();
		}
	}

}