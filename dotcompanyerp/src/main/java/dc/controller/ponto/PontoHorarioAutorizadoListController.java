package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoHorarioAutorizado;
import dc.servicos.dao.ponto.PontoHorarioAutorizadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoHorarioAutorizadoListController extends CRUDListController<PontoHorarioAutorizado> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PontoHorarioAutorizadoDAO dao;

	@Autowired
	PontoHorarioAutorizadoFormController pontoHorarioAutorizadoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "colaborador", "dataHorario", "tipo", "cargaHoraria", "entrada01", "saida01", "entrada02", "saida02", "entrada03",
				"saida03", "entrada04", "saida04", "entrada05", "saida05" };
	}

	@Override
	public Class<? super PontoHorarioAutorizado> getEntityClass() {
		return PontoHorarioAutorizado.class;
	}

	@Override
	protected String getTitulo() {
		return "Ponto Horário Autorizado";
	}

	@Override
	protected List<PontoHorarioAutorizado> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoHorarioAutorizado> getFormController() {
		return pontoHorarioAutorizadoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoHorarioAutorizado";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoHorarioAutorizado> pesquisaDefault() {
		return (List<PontoHorarioAutorizado>) dao.getAll(getEntityClass());
	}

}
