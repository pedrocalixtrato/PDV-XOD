package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.ConfiguracaoBoleto;
import dc.servicos.dao.financeiro.IConfiguracaoBoletoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ConfiguracaoBoletoListController extends CRUDListController<ConfiguracaoBoleto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IConfiguracaoBoletoDAO dao;

	@Autowired
	private ConfiguracaoBoletoFormController configuracaoBoletoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "contaCaixa", "instrucao01", "instrucao02", "mensagem", "localPagamento", "aceite", "layoutRemessa", "especie",
				"carteira", "codigoConvenio", "codigoCedente", "taxaMulta" };
	}

	@Override
	public Class<? super ConfiguracaoBoleto> getEntityClass() {
		return ConfiguracaoBoleto.class;
	}

	@Override
	protected String getTitulo() {
		return "Configuração Boleto";
	}

	@Override
	protected List<ConfiguracaoBoleto> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ConfiguracaoBoleto> getFormController() {
		return configuracaoBoletoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaConfiguracaoBoletos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<ConfiguracaoBoleto> pesquisaDefault() {
		return (List<ConfiguracaoBoleto>) dao.getAll(getEntityClass());
	}

}
