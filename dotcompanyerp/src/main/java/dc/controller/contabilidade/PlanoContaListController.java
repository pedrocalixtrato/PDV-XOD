package dc.controller.contabilidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PlanoContaListController extends CRUDListController<PlanoContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPlanoContaDAO dao;

	@Autowired
	private PlanoContaFormController planoContaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "dataInclusao", "mascara", "niveis" };
	}

	@Override
	public Class<? super PlanoContaEntity> getEntityClass() {
		return PlanoContaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano Conta";
	}

	@Override
	protected List<PlanoContaEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PlanoContaEntity> getFormController() {
		return planoContaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaPlanoContas";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<PlanoContaEntity> pesquisaDefault() {
		return (List<PlanoContaEntity>) dao.getAll(getEntityClass());
	}

}
