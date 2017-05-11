package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.servicos.dao.geral.tabela.ICstIcmsbDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstIcmsBListController extends CRUDListController<CstIcmsbEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICstIcmsbDAO dao;

	@Autowired
	CstIcmsBFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super CstIcmsbEntity> getEntityClass() {
		return CstIcmsbEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Icms B";
	}

	@Override
	protected List<CstIcmsbEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstIcmsbEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstIcmsB";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstIcmsbEntity> pesquisaDefault() {
		return (List<CstIcmsbEntity>) dao.getAll(getEntityClass());
	}

}