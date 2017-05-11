package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.servicos.dao.patrimonio.EstadoConservacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class EstadoConservacaoListController extends CRUDListController<EstadoConservacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EstadoConservacaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EstadoConservacaoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao" };
	}

	@Override
	public Class<? super EstadoConservacaoEntity> getEntityClass() {
		return EstadoConservacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Estado de conservação";
	}

	@Override
	protected List<EstadoConservacaoEntity> pesquisa(String valor) {
		try {
			List<EstadoConservacaoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EstadoConservacaoEntity>();
		}
	}

	@Override
	protected CRUDFormController<EstadoConservacaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_estado_conservacao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EstadoConservacaoEntity> pesquisaDefault() {
		try {
			List<EstadoConservacaoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EstadoConservacaoEntity>();
		}
	}

}