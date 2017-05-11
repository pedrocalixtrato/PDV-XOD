package dc.controller.contabilidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.ContabilContaEntity;
import dc.servicos.dao.contabilidade.IContabilContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContabilContaListController extends CRUDListController<ContabilContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IContabilContaDAO dao;

	@Autowired
	private ContabilContaFormController contabilContaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "classificacao", "tipo", "descricao", "dataInclusao", "situacao", "natureza", "patrimonioResultado", "livroCaixa",
				"dfc", "ordem", "codigoReduzido", "codigoEfd" };
	}

	@Override
	public Class<? super ContabilContaEntity> getEntityClass() {
		return ContabilContaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Contabil Conta";
	}

	@Override
	protected List<ContabilContaEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ContabilContaEntity> getFormController() {
		return contabilContaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaContabilContas";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<ContabilContaEntity> pesquisaDefault() {
		return (List<ContabilContaEntity>) dao.getAll(getEntityClass());
	}

}
