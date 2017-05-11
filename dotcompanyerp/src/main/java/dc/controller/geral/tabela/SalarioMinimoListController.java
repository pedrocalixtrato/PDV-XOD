package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.SalarioMinimoEntity;
import dc.servicos.dao.geral.tabela.ISalarioMinimoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SalarioMinimoListController extends CRUDListController<SalarioMinimoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISalarioMinimoDAO dao;

	@Autowired
	private SalarioMinimoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "vigencia", "valorMensal", "valorDiario", "valorHora","normaLegal","dou" };
	}

	@Override
	public Class<? super SalarioMinimoEntity> getEntityClass() {
		return SalarioMinimoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Salário Mínimo";
	}

	@Override
	protected List<SalarioMinimoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SalarioMinimoEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSalarioMinimo";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SalarioMinimoEntity> pesquisaDefault() {
		return (List<SalarioMinimoEntity>) dao.getAll(getEntityClass());
	}

}