package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoProgramadoCabDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoProgramadoCabListController extends
		CRUDListController<LancamentoProgramadoCabEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILancamentoProgramadoCabDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoProgramadoCabFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataInclusao", "tipo", "liberado" };
	}

	@Override
	public Class<? super LancamentoProgramadoCabEntity> getEntityClass() {
		return LancamentoProgramadoCabEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado cab";
	}

	@Override
	protected List<LancamentoProgramadoCabEntity> pesquisa(String valor) {
		try {
			List<LancamentoProgramadoCabEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoCabEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoProgramadoCabEntity> getFormController() {
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
	protected List<LancamentoProgramadoCabEntity> pesquisaDefault() {
		try {
			List<LancamentoProgramadoCabEntity> auxLista = this.pDAO
					.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoCabEntity>();
		}
	}

}