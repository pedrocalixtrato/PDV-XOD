package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ParcelaReceber;
import dc.servicos.dao.financeiro.IParcelaReceberDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ParcelaReceberListController extends CRUDListController<ParcelaReceber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IParcelaReceberDAO dao;

	@Autowired
	private ParcelaRecebimentoFormController parcelaRecebimentoFormController;
	
    public ParcelaReceberListController() {
		
	} 

	@Override
	public String[] getColunas() {
		return new String[] {  "lancamentoReceber.cliente","contaCaixa", "numeroParcela", "dataEmissao", "dataVencimento", "descontoAte", "valor", "valorFaltante", "taxaJuro",
				"valorJuro", "taxaMulta", "valorMulta", "taxaDesconto", "valorDesconto", "emitiuBoleto", "boletoNossoNumero" };
	}

	@Override
	public Class<? super ParcelaReceber> getEntityClass() {
		return ParcelaReceber.class;
	}

	@Override
	protected String getTitulo() {
		return "Parcela à Receber";
	}

	@Override
	protected List<ParcelaReceber> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ParcelaReceber> getFormController() {
		return parcelaRecebimentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		//return "listaParcelasReceber";
		return ClassUtils.getUrl(this);
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<ParcelaReceber> pesquisaDefault() {
		return (List<ParcelaReceber>) dao.getAll(getEntityClass());
	}

}
