package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.financeiro.IBancoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class BancoListController extends CRUDListController<BancoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IBancoDAO dao;

	@Autowired
	private BancoFormController bancoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "url" };
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super BancoEntity> getEntityClass() {
		return BancoEntity.class;
	}

	@Override
	protected CRUDFormController<BancoEntity> getFormController() {
		return bancoFormController;
	}

	@Override
	protected List<BancoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<BancoEntity> pesquisaDefault() {
		return (List<BancoEntity>) dao.getAll(getEntityClass());
	}

}