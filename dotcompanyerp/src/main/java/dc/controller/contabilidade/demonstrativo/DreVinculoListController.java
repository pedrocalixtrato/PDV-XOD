package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.demonstrativo.DreVinculoEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDreVinculoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class DreVinculoListController extends
		CRUDListController<DreVinculoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IDreVinculoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DreVinculoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dreDetalhe", "conta" };
	}

	@Override
	public Class<? super DreVinculoEntity> getEntityClass() {
		return DreVinculoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "DRE vínculo";
	}

	@Override
	protected List<DreVinculoEntity> pesquisa(String valor) {
		try {
			List<DreVinculoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreVinculoEntity>();
		}
	}

	@Override
	protected CRUDFormController<DreVinculoEntity> getFormController() {
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
	protected List<DreVinculoEntity> pesquisaDefault() {
		try {
			List<DreVinculoEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreVinculoEntity>();
		}
	}

}