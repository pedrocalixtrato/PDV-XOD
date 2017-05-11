package dc.visao.framework;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.framework.FmModulo;
import dc.servicos.dao.framework.geral.IFmModuloDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FmModuloListController extends CRUDListController<FmModulo> {

	@Autowired
	private IFmModuloDAO dao;

	@Autowired
	FmModuloFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "caption", "urlID", "viewName" };
	}

	@Override
	public Class<? super FmModulo> getEntityClass() {
		return FmModulo.class;
	}

	@Override
	protected String getTitulo() {
		return "Módulo";
	}

	@Override
	protected List<FmModulo> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<FmModulo> getFormController() {
		return formController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaModulos";
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<FmModulo> pesquisaDefault() {
		return (List<FmModulo>) dao.getAll(getEntityClass());
	}

}
