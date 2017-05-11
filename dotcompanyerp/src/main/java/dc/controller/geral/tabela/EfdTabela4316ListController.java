package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.EfdTabela4316Entity;
import dc.servicos.dao.geral.tabela.IEfdTabela4316DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela4316ListController extends CRUDListController<EfdTabela4316Entity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IEfdTabela4316DAO dao;

	@Autowired
	EfdTabela4316FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela4316Entity> getEntityClass() {
		return EfdTabela4316Entity.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 4316";
	}

	@Override
	protected List<EfdTabela4316Entity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela4316Entity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela4316";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela4316Entity> pesquisaDefault() {
		return (List<EfdTabela4316Entity>) dao.getAll(getEntityClass());
	}

}