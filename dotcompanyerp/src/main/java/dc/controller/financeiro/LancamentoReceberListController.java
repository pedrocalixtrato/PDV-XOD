package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.LancamentoReceber;
import dc.servicos.dao.financeiro.ILancamentoReceberDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class LancamentoReceberListController extends CRUDListController<LancamentoReceber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ILancamentoReceberDAO dao;

	@Autowired
	private LancamentoReceberFormController lancamentoReceberFormController;

	@Override
	public String[] getColunas() {
		return new String[] {"cliente", "documentoOrigem","numeroDocumento", "valorTotal", "valorAReceber", "dataLancamento",
				"taxaComissao","primeiroVencimento","quantidadeParcela"};
	}

	@Override
	public Class<? super LancamentoReceber> getEntityClass() {
		return LancamentoReceber.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento à Receber";
	}

	@Override
	protected List<LancamentoReceber> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<LancamentoReceber> getFormController() {
		return lancamentoReceberFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaLancamentoRecebers";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<LancamentoReceber> pesquisaDefault() {
		return (List<LancamentoReceber>) dao.getAll(getEntityClass());
	}

}