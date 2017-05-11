package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.EfdTabela4310Entity;
import dc.servicos.dao.geral.tabela.IEfdTabela4310DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela4310ListController extends CRUDListController<EfdTabela4310Entity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IEfdTabela4310DAO dao;

	@Autowired
	EfdTabela4310FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela4310Entity> getEntityClass() {
		return EfdTabela4310Entity.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 4310";
	}

	@Override
	protected List<EfdTabela4310Entity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela4310Entity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela4310";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela4310Entity> pesquisaDefault() {
		return (List<EfdTabela4310Entity>) dao.getAll(getEntityClass());
	}

}