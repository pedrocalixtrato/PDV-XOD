package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.EfdTabela439Entity;
import dc.servicos.dao.geral.tabela.IEfdTabela439DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela439ListController extends CRUDListController<EfdTabela439Entity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IEfdTabela439DAO dao;

	@Autowired
	EfdTabela439FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela439Entity> getEntityClass() {
		return EfdTabela439Entity.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 439";
	}

	@Override
	protected List<EfdTabela439Entity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela439Entity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela439";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela439Entity> pesquisaDefault() {
		return (List<EfdTabela439Entity>) dao.getAll(getEntityClass());
	}

}