package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.SeguradoraEntity;
import dc.servicos.dao.patrimonio.SeguradoraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class SeguradoraListController extends CRUDListController<SeguradoraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private SeguradoraDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private SeguradoraFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "contato", "telefone" };
	}

	@Override
	public Class<? super SeguradoraEntity> getEntityClass() {
		return SeguradoraEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Seguradora";
	}

	@Override
	protected List<SeguradoraEntity> pesquisa(String valor) {
		try {
			List<SeguradoraEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SeguradoraEntity>();
		}
	}

	@Override
	protected CRUDFormController<SeguradoraEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_seguradora_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SeguradoraEntity> pesquisaDefault() {
		try {
			List<SeguradoraEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SeguradoraEntity>();
		}
	}

}