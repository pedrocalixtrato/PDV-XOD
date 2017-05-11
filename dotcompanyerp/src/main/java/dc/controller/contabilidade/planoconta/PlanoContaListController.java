package dc.controller.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller(value = "contabilidadePlanoContaListController")
@Scope("prototype")
public class PlanoContaListController extends
		CRUDListController<PlanoContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IPlanoContaDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PlanoContaFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "dataInclusao" };
	}

	@Override
	public Class<? super PlanoContaEntity> getEntityClass() {
		return PlanoContaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano conta";
	}

	@Override
	protected List<PlanoContaEntity> pesquisa(String valor) {
		try {
			List<PlanoContaEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaEntity>();
		}
	}

	@Override
	protected CRUDFormController<PlanoContaEntity> getFormController() {
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
	protected List<PlanoContaEntity> pesquisaDefault() {
		try {
			List<PlanoContaEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaEntity>();
		}
	}

}