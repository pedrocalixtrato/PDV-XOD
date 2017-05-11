package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoRelogio;
import dc.servicos.dao.ponto.PontoRelogioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoRelogioListController extends CRUDListController<PontoRelogio> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PontoRelogioDAO dao;

	@Autowired
	PontoRelogioFormController pontoBancoHorasFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "localizacao", "marca", "fabricante", "numeroSerie", "utilizacao" };
	}

	@Override
	public Class<? super PontoRelogio> getEntityClass() {
		return PontoRelogio.class;
	}

	@Override
	protected String getTitulo() {
		return "Ponto Relógio";
	}

	@Override
	protected List<PontoRelogio> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoRelogio> getFormController() {
		return pontoBancoHorasFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoRelogio";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoRelogio> pesquisaDefault() {
		return (List<PontoRelogio>) dao.getAll(getEntityClass());
	}

}
