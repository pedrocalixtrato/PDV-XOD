package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoHorario;
import dc.servicos.dao.ponto.PontoHorarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoHorarioListController extends CRUDListController<PontoHorario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PontoHorarioDAO dao;

	@Autowired
	PontoHorarioFormController pontoHorarioFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "tipo", "codigo", "nome", "tipoTrabalho", "cargaHoraria", "entrada01", "saida01", "entrada02", "saida02", "entrada03",
				"saida03", "entrada04", "saida04", "entrada05", "saida05", "horaInicioJornada", "horaFimJornada" };
	}

	@Override
	public Class<? super PontoHorario> getEntityClass() {
		return PontoHorario.class;
	}

	@Override
	protected String getTitulo() {
		return "Ponto Horário";
	}

	@Override
	protected List<PontoHorario> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoHorario> getFormController() {
		return pontoHorarioFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoHorario";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoHorario> pesquisaDefault() {
		return (List<PontoHorario>) dao.getAll(getEntityClass());
	}

}
