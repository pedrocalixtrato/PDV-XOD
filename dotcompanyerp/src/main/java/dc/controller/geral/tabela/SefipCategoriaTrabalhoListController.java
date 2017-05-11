package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.SefipCategoriaTrabalhoEntity;
import dc.servicos.dao.geral.tabela.ISefipCategoriaTrabalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SefipCategoriaTrabalhoListController extends CRUDListController<SefipCategoriaTrabalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISefipCategoriaTrabalhoDAO dao;

	@Autowired
	SefipCategoriaTrabalhoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome" };
	}

	@Override
	public Class<? super SefipCategoriaTrabalhoEntity> getEntityClass() {
		return SefipCategoriaTrabalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Categoria de Trabalho - SEFIP";
	}

	@Override
	protected List<SefipCategoriaTrabalhoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SefipCategoriaTrabalhoEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSefipCategoriaTrabalho";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SefipCategoriaTrabalhoEntity> pesquisaDefault() {
		return (List<SefipCategoriaTrabalhoEntity>) dao.getAll(getEntityClass());
	}

}