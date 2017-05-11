package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.SefipCodigoRecolhimentoEntity;
import dc.servicos.dao.geral.tabela.ISefipCodigoRecolhimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SefipCodigoRecolhimentoListController extends CRUDListController<SefipCodigoRecolhimentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISefipCodigoRecolhimentoDAO dao;

	@Autowired
	SefipCodigoRecolhimentoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "aplicacao" };
	}

	@Override
	public Class<? super SefipCodigoRecolhimentoEntity> getEntityClass() {
		return SefipCodigoRecolhimentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Código Recolhimento - SEFIP";
	}

	@Override
	protected List<SefipCodigoRecolhimentoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SefipCodigoRecolhimentoEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSefipCodigoRecolhimento";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SefipCodigoRecolhimentoEntity> pesquisaDefault() {
		return (List<SefipCodigoRecolhimentoEntity>) dao.getAll(getEntityClass());
	}

}