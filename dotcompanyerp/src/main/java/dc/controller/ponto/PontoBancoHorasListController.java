package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoBancoHoras;
import dc.servicos.dao.ponto.PontoBancoHorasDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoBancoHorasListController extends CRUDListController<PontoBancoHoras> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PontoBancoHorasDAO dao;

	@Autowired
	PontoBancoHorasFormController pontoBancoHorasFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "colaborador", "dataTrabalho", "quantidade", "situacao" };
	}

	@Override
	public Class<? super PontoBancoHoras> getEntityClass() {
		return PontoBancoHoras.class;
	}

	@Override
	protected String getTitulo() {
		return "Banco de Horas";
	}

	@Override
	protected List<PontoBancoHoras> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PontoBancoHoras> getFormController() {
		return pontoBancoHorasFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoBancoHoras";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PontoBancoHoras> pesquisaDefault() {
		try {
			List<PontoBancoHoras> auxLista = (List<PontoBancoHoras>) this.dao.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/*private List<PontoBancoHoras> ajustaEnums(List<PontoBancoHoras> all) {
		for (PontoBancoHoras pontoBancoHoras : all) {
			pontoBancoHoras.setSituacao(SituacaoBancoHoras.getSituacao(pontoBancoHoras.getSituacao()).getLabel());
		}
		return all;
	}*/

}
