package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.demonstrativo.DreCabecalhoEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDreCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class DreCabecalhoListController extends
		CRUDListController<DreCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IDreCabecalhoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DreCabecalhoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "padrao", "periodoInicial",
				"periodoFinal" };
	}

	@Override
	public Class<? super DreCabecalhoEntity> getEntityClass() {
		return DreCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "DRE cabeçalho";
	}

	@Override
	protected List<DreCabecalhoEntity> pesquisa(String valor) {
		try {
			List<DreCabecalhoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreCabecalhoEntity>();
		}
	}

	@Override
	protected CRUDFormController<DreCabecalhoEntity> getFormController() {
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
	protected List<DreCabecalhoEntity> pesquisaDefault() {
		try {
			List<DreCabecalhoEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreCabecalhoEntity>();
		}
	}

}