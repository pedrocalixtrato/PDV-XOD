package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CstCofinsEntity;
import dc.servicos.dao.geral.tabela.ICstCofinsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstCofinsListController extends CRUDListController<CstCofinsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICstCofinsDAO dao;

	@Autowired
	CstCofinsFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super CstCofinsEntity> getEntityClass() {
		return CstCofinsEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Cofins";
	}

	@Override
	protected List<CstCofinsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstCofinsEntity> getFormController() {
		return pController;
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
	protected List<CstCofinsEntity> pesquisaDefault() {
		return (List<CstCofinsEntity>) dao.getAll(getEntityClass());
	}

}