package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoEscala;
import dc.servicos.dao.ponto.PontoEscalaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoEscalaListController extends CRUDListController<PontoEscala> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PontoEscalaDAO dao;

	@Autowired
	PontoEscalaFormController pontoEscalaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descontoHoraDia", "descontoDsr", "codigoHorarioDomingo", "codigoHorarioSegunda", "codigoHorarioTerca",
				"codigoHorarioQuarta", "codigoHorarioQuinta", "codigoHorarioSexta", "codigoHorarioSabado" };
	}

	@Override
	public Class<? super PontoEscala> getEntityClass() {
		return PontoEscala.class;
	}

	@Override
	protected String getTitulo() {
		return "Ponto Escala";
	}

	@Override
	protected List<PontoEscala> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoEscala> getFormController() {
		return pontoEscalaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoEscala";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoEscala> pesquisaDefault() {
		return (List<PontoEscala>) dao.getAll(getEntityClass());
	}

}
