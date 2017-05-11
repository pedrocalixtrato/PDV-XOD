package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.BemEntity;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class BemListController extends CRUDListController<BemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private BemDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private BemFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "estadoConservacao", "fornecedor" };
	}

	@Override
	public Class<? super BemEntity> getEntityClass() {
		return BemEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Bem";
	}

	@Override
	protected List<BemEntity> pesquisa(String valor) {
		try {
			List<BemEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<BemEntity>();
		}
	}

	@Override
	protected CRUDFormController<BemEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_bem_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<BemEntity> pesquisaDefault() {
		try {
			List<BemEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<BemEntity>();
		}
	}

}