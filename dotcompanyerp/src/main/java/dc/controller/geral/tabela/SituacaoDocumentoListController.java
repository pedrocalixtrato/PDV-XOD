package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.SituacaoDocumentoEntity;
import dc.servicos.dao.geral.tabela.ISituacaoDocumentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SituacaoDocumentoListController extends CRUDListController<SituacaoDocumentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISituacaoDocumentoDAO dao;

	@Autowired
	SituacaoDocumentoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super SituacaoDocumentoEntity> getEntityClass() {
		return SituacaoDocumentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Situação Documento";
	}

	@Override
	protected List<SituacaoDocumentoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SituacaoDocumentoEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSituacaoDocumento";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SituacaoDocumentoEntity> pesquisaDefault() {
		return (List<SituacaoDocumentoEntity>) dao.getAll(getEntityClass());
	}

}