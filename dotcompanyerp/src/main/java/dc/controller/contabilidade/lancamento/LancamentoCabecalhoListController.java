package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LancamentoCabecalhoEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoCabecalhoListController extends
		CRUDListController<LancamentoCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILancamentoCabecalhoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoCabecalhoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataLancamento", "dataInclusao", "liberado", "valor" };
	}

	@Override
	public Class<? super LancamentoCabecalhoEntity> getEntityClass() {
		return LancamentoCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento cabeçalho";
	}

	@Override
	protected List<LancamentoCabecalhoEntity> pesquisa(String valor) {
		try {
			List<LancamentoCabecalhoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoCabecalhoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoCabecalhoEntity> getFormController() {
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
	protected List<LancamentoCabecalhoEntity> pesquisaDefault() {
		try {
			List<LancamentoCabecalhoEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoCabecalhoEntity>();
		}
	}

}