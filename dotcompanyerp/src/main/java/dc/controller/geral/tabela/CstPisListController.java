package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.CstPisEntity;
import dc.servicos.dao.geral.tabela.ICstPisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstPisListController extends CRUDListController<CstPisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICstPisDAO dao;

	@Autowired
	CstPisFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super CstPisEntity> getEntityClass() {
		return CstPisEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Pis";
	}

	@Override
	protected List<CstPisEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstPisEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstPis";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstPisEntity> pesquisaDefault() {
		return (List<CstPisEntity>) dao.getAll(getEntityClass());
	}

}