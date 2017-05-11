package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CsosnaEntity;
import dc.servicos.dao.geral.tabela.ICsosnaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Controller
@Scope("prototype")
public class CsosnaListController extends CRUDListController<CsosnaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICsosnaDAO dao;

	@Autowired
	CsosnaFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super CsosnaEntity> getEntityClass() {
		return CsosnaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "CSOSN A";
	}

	@Override
	protected List<CsosnaEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CsosnaEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CsosnaEntity> pesquisaDefault() {
		return (List<CsosnaEntity>) dao.getAll(getEntityClass());
	}

}