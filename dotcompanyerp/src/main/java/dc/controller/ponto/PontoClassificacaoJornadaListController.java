package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoClassificacaoJornada;
import dc.servicos.dao.ponto.PontoClassificacaoJornadaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoClassificacaoJornadaListController extends CRUDListController<PontoClassificacaoJornada> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	PontoClassificacaoJornadaDAO dao;

	@Autowired
	PontoClassificacaoJornadaFormController pontoClassificacaoJornadaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao", "padrao", "descontarHoras" };
	}

	@Override
	public Class<? super PontoClassificacaoJornada> getEntityClass() {
		return PontoClassificacaoJornada.class;
	}

	@Override
	protected String getTitulo() {
		return "Ponto Classificação Jornada";
	}

	@Override
	protected List<PontoClassificacaoJornada> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoClassificacaoJornada> getFormController() {
		return pontoClassificacaoJornadaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoClassificacaoJornada";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoClassificacaoJornada> pesquisaDefault() {
		return (List<PontoClassificacaoJornada>) dao.getAll(getEntityClass());
	}

}
