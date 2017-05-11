package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.TaxaDepreciacaoEntity;
import dc.servicos.dao.patrimonio.TaxaDepreciacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class TaxaDepreciacaoListController extends CRUDListController<TaxaDepreciacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private TaxaDepreciacaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private TaxaDepreciacaoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "ncm", "bem", "vida", "taxa" };
	}

	@Override
	public Class<? super TaxaDepreciacaoEntity> getEntityClass() {
		return TaxaDepreciacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Taxa de depreciação";
	}

	@Override
	protected List<TaxaDepreciacaoEntity> pesquisa(String valor) {
		try {
			List<TaxaDepreciacaoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TaxaDepreciacaoEntity>();
		}
	}

	@Override
	protected CRUDFormController<TaxaDepreciacaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_taxa_depreciacao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TaxaDepreciacaoEntity> pesquisaDefault() {
		try {
			List<TaxaDepreciacaoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TaxaDepreciacaoEntity>();
		}
	}

}