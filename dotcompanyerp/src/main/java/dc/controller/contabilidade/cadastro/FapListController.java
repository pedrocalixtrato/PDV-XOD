package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.cadastro.FapEntity;
import dc.servicos.dao.contabilidade.cadastro.IFapDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class FapListController extends CRUDListController<FapEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IFapDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private FapFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "fap", "dataInicial", "dataFinal" };
	}

	@Override
	public Class<? super FapEntity> getEntityClass() {
		return FapEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "FAP";
	}

	@Override
	protected List<FapEntity> pesquisa(String valor) {
		try {
			List<FapEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FapEntity>();
		}
	}

	@Override
	protected CRUDFormController<FapEntity> getFormController() {
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
	protected List<FapEntity> pesquisaDefault() {
		try {
			List<FapEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FapEntity>();
		}
	}

}