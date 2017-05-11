package dc.controller.geral.ged;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.ged.TipoDocumento;
import dc.servicos.dao.geral.ged.ITipoDocumentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoDocumentoListController extends
		CRUDListController<TipoDocumento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoDocumentoDAO dao;

	@Autowired
	TipoDocumentoFormController tipoDocumentoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "tamanhoMaximo" };
	}

	@Override
	public Class<? super TipoDocumento> getEntityClass() {
		return TipoDocumento.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Documento";
	}

	@Override
	protected List<TipoDocumento> pesquisa(String valor) {
		return new ArrayList<TipoDocumento>();
	}

	@Override
	protected CRUDFormController<TipoDocumento> getFormController() {
		return tipoDocumentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTipoDocumento";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<TipoDocumento> pesquisaDefault() {
		return new ArrayList<TipoDocumento>();
	}

}