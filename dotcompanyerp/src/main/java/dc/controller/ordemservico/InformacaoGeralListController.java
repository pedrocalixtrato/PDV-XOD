package dc.controller.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.servicos.dao.ordemservico.InformacaoGeralDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class InformacaoGeralListController extends
		CRUDListController<InformacaoGeralEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private InformacaoGeralDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private InformacaoGeralFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	public Class<? super Object> getEntityClass() {
		return Object.class;
	}

	@Override
	protected String getTitulo() {
		return "Informação geral";
	}

	@Override
	protected List<InformacaoGeralEntity> pesquisa(String valor) {
		try {
			List<InformacaoGeralEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	@Override
	protected CRUDFormController<InformacaoGeralEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "ordemservico_informacaogeral_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<InformacaoGeralEntity> pesquisaDefault() {
		try {
			List<InformacaoGeralEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<InformacaoGeralEntity>();
		}
	}

}