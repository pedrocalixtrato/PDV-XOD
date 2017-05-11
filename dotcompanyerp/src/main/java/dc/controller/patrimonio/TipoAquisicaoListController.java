package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.servicos.dao.patrimonio.TipoAquisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class TipoAquisicaoListController extends CRUDListController<TipoAquisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private TipoAquisicaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private TipoAquisicaoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "tipo", "nome", "descricao" };
	}

	@Override
	public Class<? super TipoAquisicaoEntity> getEntityClass() {
		return TipoAquisicaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo de aquisição";
	}

	@Override
	protected List<TipoAquisicaoEntity> pesquisa(String valor) {
		try {
			List<TipoAquisicaoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TipoAquisicaoEntity>();
		}
	}

	@Override
	protected CRUDFormController<TipoAquisicaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_tipo_aquisicao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TipoAquisicaoEntity> pesquisaDefault() {
		try {
			List<TipoAquisicaoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TipoAquisicaoEntity>();
		}
	}

}