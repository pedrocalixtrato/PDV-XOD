package dc.controller.suprimento.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.RequisicaoInternaCabecalhoEntity;
import dc.servicos.dao.suprimentos.estoque.IRequisicaoInternaCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class RequisicaoInternaListController extends
		CRUDListController<RequisicaoInternaCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IRequisicaoInternaCabecalhoDAO dao;

	@Autowired
	RequisicaoInternaFormController formController;

	public RequisicaoInternaListController() {

		// Empresa empresa = usuario.getConta().getEmpresa();
		// System.out.println("");
	}

	@Override
	public String[] getColunas() {
		return new String[] {"colaborador", "dataRequisicao" };
	}

	@Override
	protected String getTitulo() {
		return "Requisição Interna";
	}

	@Override
	protected List<RequisicaoInternaCabecalhoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<RequisicaoInternaCabecalhoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super RequisicaoInternaCabecalhoEntity> getEntityClass() {
		return RequisicaoInternaCabecalhoEntity.class;
	}

	@Override
	protected List<RequisicaoInternaCabecalhoEntity> pesquisaDefault() {
		return dao.getAll(RequisicaoInternaCabecalhoEntity.class);
		// //return dao.findBySetor();
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}