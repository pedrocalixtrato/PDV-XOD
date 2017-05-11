package dc.controller.suprimento.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.suprimentos.estoque.INotaFiscalDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class NotaFiscalListController extends CRUDListController<NotaFiscal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private INotaFiscalDAO dao;

	@Autowired
	NotaFiscalFormController formController;

	@Override
	protected CRUDFormController<NotaFiscal> getFormController() {
		return formController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"status","chaveAcesso","modelo", "serie", "numero","dataEmissao","dataEntradaSaida","horaEntradaSaida",
				"tipoOperacao","formaPagamento","formaEmissao","finalidadeEmissao","tipoImpressaoDanfe","naturezaOperacao",
				"baseCalculoIcms","valorIcms","baseCalculoIcmsSt","valorIcmsSt","valorCofins","valorFrete","valorSeguro","outrasDespesasAcessorias",
				"valorPis","valorDescontos","valorTotalProdutos","valorTotalNota","informacoesFisco","informacoesContribuinte"};
	}

	@Override
	protected String getTitulo() {
		return "Entrada de Nota Fiscal";
	}

	@Override
	protected List<NotaFiscal> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super NotaFiscal> getEntityClass() {
		return NotaFiscal.class;
	}

	@Override
	protected List<NotaFiscal> pesquisaDefault() {
		return dao.getAll(NotaFiscal.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

}