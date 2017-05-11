package dc.controller.adm.dotcompany;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.servicos.dao.adm.dotcompany.IParametroClienteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ParametroClienteListController extends
		CRUDListController<ParametroCliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IParametroClienteDAO dao;

	@Autowired
	private ParametroClienteFormController parametroClienteFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "empresa","valorEntrada","valorMensalidade", "dataEntrada","nomeResponsavel","emailPrincipal"};
	}

	@Override
	public Class<? super ParametroCliente> getEntityClass() {
		return ParametroCliente.class;
	}

	@Override
	protected String getTitulo() {
		return "Parâmetro Cliente";
	}

	@Override
	protected List<ParametroCliente> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ParametroCliente> getFormController() {
		return parametroClienteFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaParametroCliente";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ParametroCliente> pesquisaDefault() {
		return dao.getAll(ParametroCliente.class);
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}