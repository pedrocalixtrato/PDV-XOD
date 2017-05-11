package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoParametro;
import dc.servicos.dao.ponto.PontoParametroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoParametroListController extends CRUDListController<PontoParametro> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PontoParametroDAO dao;

	@Autowired
	PontoParametroFormController pontoParametroFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "mesAno", "diaInicialApuracao", "horaNoturnaInicio", "horaNoturnaFim", "periodoMinimoInterjornada",
				"percentualHeDiurna", "percentualHeNoturna", "duracaoHoraNoturna", "tratamentoHoraMais", "tratamentoHoraMenos", };
	}

	@Override
	public Class<? super PontoParametro> getEntityClass() {
		return PontoParametro.class;
	}

	@Override
	protected String getTitulo() {
		return "Ponto Parâmetro";
	}

	@Override
	protected List<PontoParametro> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoParametro> getFormController() {
		return pontoParametroFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoParametro";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoParametro> pesquisaDefault() {
		return (List<PontoParametro>) dao.getAll(getEntityClass());
	}

}
