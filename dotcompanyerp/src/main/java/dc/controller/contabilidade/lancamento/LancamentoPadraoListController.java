package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LancamentoPadraoEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoPadraoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoPadraoListController extends
		CRUDListController<LancamentoPadraoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILancamentoPadraoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoPadraoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "historico" };
	}

	@Override
	public Class<? super LancamentoPadraoEntity> getEntityClass() {
		return LancamentoPadraoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento padrão";
	}

	@Override
	protected List<LancamentoPadraoEntity> pesquisa(String valor) {
		try {
			List<LancamentoPadraoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoPadraoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoPadraoEntity> getFormController() {
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
	protected List<LancamentoPadraoEntity> pesquisaDefault() {
		try {
			List<LancamentoPadraoEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoPadraoEntity>();
		}
	}

}