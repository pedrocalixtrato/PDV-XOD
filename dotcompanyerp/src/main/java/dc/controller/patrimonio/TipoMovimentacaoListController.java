package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.TipoMovimentacaoEntity;
import dc.servicos.dao.patrimonio.TipoMovimentacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class TipoMovimentacaoListController extends CRUDListController<TipoMovimentacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private TipoMovimentacaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private TipoMovimentacaoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "tipo", "nome", "descricao" };
	}

	@Override
	public Class<? super TipoMovimentacaoEntity> getEntityClass() {
		return TipoMovimentacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo de movimentação";
	}

	@Override
	protected List<TipoMovimentacaoEntity> pesquisa(String valor) {
		try {
			List<TipoMovimentacaoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TipoMovimentacaoEntity>();
		}
	}

	@Override
	protected CRUDFormController<TipoMovimentacaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_tipo_movimentacao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TipoMovimentacaoEntity> pesquisaDefault() {
		try {
			List<TipoMovimentacaoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TipoMovimentacaoEntity>();
		}
	}

}