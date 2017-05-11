package dc.controller.suprimento.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.ReajusteCabecalhoEntity;
import dc.servicos.dao.suprimentos.estoque.IReajusteCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ReajusteEstoqueListController extends
		CRUDListController<ReajusteCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IReajusteCabecalhoDAO dao;

	@Autowired
	ReajusteEstoqueFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] {"colaborador", "porcentagem", "dataReajuste", "tipoString" };
	}

	@Override
	protected String getTitulo() {
		return "Reajuste de Preço";
	}

	@Override
	protected List<ReajusteCabecalhoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<ReajusteCabecalhoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ReajusteCabecalhoEntity> getEntityClass() {
		return ReajusteCabecalhoEntity.class;
	}

	@Override
	protected List<ReajusteCabecalhoEntity> pesquisaDefault() {
		return dao.getAll(ReajusteCabecalhoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

}