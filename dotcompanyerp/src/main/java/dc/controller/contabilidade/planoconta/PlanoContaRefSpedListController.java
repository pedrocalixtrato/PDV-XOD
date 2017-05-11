package dc.controller.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaRefSpedDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller(value = "contabilidadePlanoContaRefSpedListController")
@Scope("prototype")
public class PlanoContaRefSpedListController extends
		CRUDListController<PlanoContaRefSped> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IPlanoContaRefSpedDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PlanoContaRefSpedFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "orientacoes", "inicioValidade",
				"fimValidade" };
	}

	@Override
	public Class<? super PlanoContaRefSped> getEntityClass() {
		return PlanoContaRefSped.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano conta - SPED";
	}

	@Override
	protected List<PlanoContaRefSped> pesquisa(String valor) {
		try {
			List<PlanoContaRefSped> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaRefSped>();
		}
	}

	@Override
	protected CRUDFormController<PlanoContaRefSped> getFormController() {
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
	protected List<PlanoContaRefSped> pesquisaDefault() {
		try {
			List<PlanoContaRefSped> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaRefSped>();
		}
	}

}