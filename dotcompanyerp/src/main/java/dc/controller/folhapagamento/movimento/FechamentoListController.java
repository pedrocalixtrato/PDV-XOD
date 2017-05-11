package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.FechamentoEntity;
import dc.servicos.dao.folhapagamento.movimento.FechamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class FechamentoListController extends
		CRUDListController<FechamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private FechamentoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private FechamentoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "fechamentoAtual", "proximoFechamento" };
	}

	@Override
	public Class<? super FechamentoEntity> getEntityClass() {
		return FechamentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<FechamentoEntity> pesquisa(String valor) {
		try {
			List<FechamentoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FechamentoEntity>();
		}
	}

	@Override
	protected CRUDFormController<FechamentoEntity> getFormController() {
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
	protected List<FechamentoEntity> pesquisaDefault() {
		try {
			List<FechamentoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FechamentoEntity>();
		}
	}

}