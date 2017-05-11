package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.servicos.dao.geral.tabela.ICsosnbDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Controller
@Scope("prototype")
public class CsosnbListController extends CRUDListController<CsosnbEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICsosnbDAO dao;

	@Autowired
	CsosnbFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super CsosnbEntity> getEntityClass() {
		return CsosnbEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "CSOSN B";
	}

	@Override
	protected List<CsosnbEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CsosnbEntity> getFormController() {
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
	protected List<CsosnbEntity> pesquisaDefault() {
		return (List<CsosnbEntity>) dao.getAll(getEntityClass());
	}

}