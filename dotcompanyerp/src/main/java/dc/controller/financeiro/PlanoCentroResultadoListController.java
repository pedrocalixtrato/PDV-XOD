package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.PlanoCentroResultado;
import dc.servicos.dao.financeiro.IPlanoCentroResultadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PlanoCentroResultadoListController extends CRUDListController<PlanoCentroResultado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPlanoCentroResultadoDAO dao;

	@Autowired
	PlanoCentroResultadoFormController planocentroresultadoFormController;

	@Override
	public String[] getColunas() {
		return new String[] {"nome", "mascara","niveis","dataInclusao" };
	}

	@Override
	public Class<? super PlanoCentroResultado> getEntityClass() {
		return PlanoCentroResultado.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano Centro Resultado";
	}

	@Override
	protected List<PlanoCentroResultado> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PlanoCentroResultado> getFormController() {
		return planocentroresultadoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<PlanoCentroResultado> pesquisaDefault() {
		return (List<PlanoCentroResultado>) dao.getAll(getEntityClass());
	}

}