package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.SefipCodigoMovimentacaoEntity;
import dc.servicos.dao.geral.tabela.ISefipCodigoMovimentacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SefipCodigoMovimentacaoListController extends CRUDListController<SefipCodigoMovimentacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISefipCodigoMovimentacaoDAO dao;

	@Autowired
	SefipCodigoMovimentacaoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "aplicacao" };
	}

	@Override
	public Class<? super SefipCodigoMovimentacaoEntity> getEntityClass() {
		return SefipCodigoMovimentacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Código Movimentação - SEFIP";
	}

	@Override
	protected List<SefipCodigoMovimentacaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SefipCodigoMovimentacaoEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSefipCodigoMovimentacao";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SefipCodigoMovimentacaoEntity> pesquisaDefault() {
		return (List<SefipCodigoMovimentacaoEntity>) dao.getAll(getEntityClass());
	}

}