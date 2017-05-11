package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.FeriadoEntity;
import dc.servicos.dao.geral.tabela.IFeriadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class FeriadosListController extends CRUDListController<FeriadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IFeriadoDAO feriadosDAO;

	@Autowired
	private FeriadosFormController feriadosFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "ano", "nome","abrangencia","uf","tipo","municipioIbge","data" };
	}

	@Override
	public Class<? super FeriadoEntity> getEntityClass() {
		return FeriadoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Feriados";
	}

	@Override
	protected List<FeriadoEntity> pesquisa(String valor) {
		return feriadosDAO.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<FeriadoEntity> getFormController() {
		return feriadosFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaFeriados";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<FeriadoEntity> pesquisaDefault() {
		return (List<FeriadoEntity>) feriadosDAO.getAll(getEntityClass());
	}

}