package dc.controller.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.servicos.dao.ordemservico.LaudoTecnicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LaudoTecnicoListController extends
		CRUDListController<LaudoTecnicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LaudoTecnicoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LaudoTecnicoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	public Class<? super LaudoTecnicoEntity> getEntityClass() {
		return LaudoTecnicoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Laudo técnico";
	}

	@Override
	protected List<LaudoTecnicoEntity> pesquisa(String valor) {
		try {
		//	List<LaudoTecnicoEntity> auxLista = this.pDAO
		//			.procuraNomeContendo(valor);

			//return auxLista;
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LaudoTecnicoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LaudoTecnicoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "ordemservico_laudotecnico_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<LaudoTecnicoEntity> pesquisaDefault() {
		try {
//			List<LaudoTecnicoEntity> auxLista = this.pDAO.listarTodos();

//			return auxLista;
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LaudoTecnicoEntity>();
		}
	}

}