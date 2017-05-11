package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.servicos.dao.financeiro.IDocumentoOrigemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class DocumentoOrigemListController extends CRUDListController<DocumentoOrigem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDocumentoOrigemDAO dao;

	@Autowired
	private DocumentoOrigemFormController documentoorigemFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "siglaDocumento" };
	}

	@Override
	public Class<? super DocumentoOrigem> getEntityClass() {
		return DocumentoOrigem.class;
	}

	@Override
	protected String getTitulo() {
		return "Documento Origem";
	}

	@Override
	protected List<DocumentoOrigem> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<DocumentoOrigem> getFormController() {
		return documentoorigemFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<DocumentoOrigem> pesquisaDefault() {
		return (List<DocumentoOrigem>) dao.getAll(getEntityClass());
	}

}
