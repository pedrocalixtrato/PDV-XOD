package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CfopEntity;
import dc.servicos.dao.geral.tabela.ICfopDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CfopListController extends CRUDListController<CfopEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICfopDAO dao;

	@Autowired
	CfopFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] {"cfop", "descricao", "aplicacao" };
	}

	@Override
	public Class<? super CfopEntity> getEntityClass() {
		return CfopEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Cfop";
	}

	@Override
	protected List<CfopEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CfopEntity> getFormController() {
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
	protected List<CfopEntity> pesquisaDefault() {
		return (List<CfopEntity>) dao.getAll(getEntityClass());
	}

}