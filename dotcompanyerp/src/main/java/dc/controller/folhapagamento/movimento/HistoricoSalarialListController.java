package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.HistoricoSalarialEntity;
import dc.servicos.dao.folhapagamento.movimento.HistoricoSalarialDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class HistoricoSalarialListController extends
		CRUDListController<HistoricoSalarialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private HistoricoSalarialDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private HistoricoSalarialFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "competencia", "salarioAtual",
				"percentualAumento", "salarioNovo", "colaborador"  };
	}

	@Override
	public Class<? super HistoricoSalarialEntity> getEntityClass() {
		return HistoricoSalarialEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<HistoricoSalarialEntity> pesquisa(String valor) {
		try {
			List<HistoricoSalarialEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<HistoricoSalarialEntity>();
		}
	}

	@Override
	protected CRUDFormController<HistoricoSalarialEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<HistoricoSalarialEntity> pesquisaDefault() {
		try {
			List<HistoricoSalarialEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<HistoricoSalarialEntity>();
		}
	}

}