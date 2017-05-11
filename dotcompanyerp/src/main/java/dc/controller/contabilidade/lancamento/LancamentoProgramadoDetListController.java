package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoDetEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoProgramadoDetDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoProgramadoDetListController extends
		CRUDListController<LancamentoProgramadoDetEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILancamentoProgramadoDetDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoProgramadoDetFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricaoHistorico", "tipo" };
	}

	@Override
	public Class<? super LancamentoProgramadoDetEntity> getEntityClass() {
		return LancamentoProgramadoDetEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado det";
	}

	@Override
	protected List<LancamentoProgramadoDetEntity> pesquisa(String valor) {
		try {
			List<LancamentoProgramadoDetEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoDetEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoProgramadoDetEntity> getFormController() {
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
	protected List<LancamentoProgramadoDetEntity> pesquisaDefault() {
		try {
			List<LancamentoProgramadoDetEntity> auxLista = this.pDAO
					.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoDetEntity>();
		}
	}

}