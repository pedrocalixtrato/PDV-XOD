package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.demonstrativo.DreDetalheEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDreDetalheDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class DreDetalheListController extends
		CRUDListController<DreDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IDreDetalheDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DreDetalheFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "classificacao", "descricao", "formaCalculo",
				"sinal" };
	}

	@Override
	public Class<? super DreDetalheEntity> getEntityClass() {
		return DreDetalheEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "DRE detalhe";
	}

	@Override
	protected List<DreDetalheEntity> pesquisa(String valor) {
		try {
			List<DreDetalheEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreDetalheEntity>();
		}
	}

	@Override
	protected CRUDFormController<DreDetalheEntity> getFormController() {
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
	protected List<DreDetalheEntity> pesquisaDefault() {
		try {
			List<DreDetalheEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreDetalheEntity>();
		}
	}

}