package dc.controller.contabilidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaRefSpedDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PlanoContaRefSpedListController extends CRUDListController<PlanoContaRefSped> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPlanoContaRefSpedDAO dao;

	@Autowired
	private PlanoContaRefSpedFormController planoContaRefSpedFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "codCtaRef", "descricao", "inicioValidade", "fimValidade", "tipo" };
	}

	@Override
	public Class<? super PlanoContaRefSped> getEntityClass() {
		return PlanoContaRefSped.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano Conta Ref Sped";
	}

	@Override
	protected List<PlanoContaRefSped> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PlanoContaRefSped> getFormController() {
		return planoContaRefSpedFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaPlanoContaRefSpeds";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<PlanoContaRefSped> pesquisaDefault() {
		return (List<PlanoContaRefSped>) dao.getAll(getEntityClass());
	}

}
