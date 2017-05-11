package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LancamentoOrcadoEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoOrcadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoOrcadoListController extends
		CRUDListController<LancamentoOrcadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILancamentoOrcadoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoOrcadoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "ano", "janeiro", "fevereiro", "marco" };
	}

	@Override
	public Class<? super LancamentoOrcadoEntity> getEntityClass() {
		return LancamentoOrcadoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento orçado";
	}

	@Override
	protected List<LancamentoOrcadoEntity> pesquisa(String valor) {
		try {
			List<LancamentoOrcadoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoOrcadoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoOrcadoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<LancamentoOrcadoEntity> pesquisaDefault() {
		try {
			List<LancamentoOrcadoEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoOrcadoEntity>();
		}
	}

}