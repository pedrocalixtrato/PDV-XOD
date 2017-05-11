package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.CstIpiEntity;
import dc.servicos.dao.geral.tabela.ICstIpiDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstIpiListController extends CRUDListController<CstIpiEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICstIpiDAO dao;

	@Autowired
	CstIpiFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super CstIpiEntity> getEntityClass() {
		return CstIpiEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Ipi";
	}

	@Override
	protected List<CstIpiEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstIpiEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstIpi";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstIpiEntity> pesquisaDefault() {
		return (List<CstIpiEntity>) dao.getAll(getEntityClass());
	}

}