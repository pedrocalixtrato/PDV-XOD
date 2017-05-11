package dc.controller.relatorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.relatorio.Relatorio;
import dc.servicos.dao.relatorio.IRelatorioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class RelatorioListController extends CRUDListController<Relatorio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IRelatorioDAO dao;

	@Autowired
	private RelatorioFormController relatorioFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super Relatorio> getEntityClass() {
		return Relatorio.class;
	}

	@Override
	protected String getTitulo() {
		return "Relatório";
	}

	@Override
	protected List<Relatorio> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Relatorio> getFormController() {
		return relatorioFormController;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaRelatorio";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Relatorio> pesquisaDefault() {
		return (List<Relatorio>) dao.getAll(getEntityClass());
	}

}
