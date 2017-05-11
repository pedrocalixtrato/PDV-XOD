package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TipoAdmissaoEntity;
import dc.servicos.dao.geral.pessoal.ITipoAdmissaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoAdmissaoListController extends
		CRUDListController<TipoAdmissaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoAdmissaoDAO dao;

	@Autowired
	TipoAdmissaoFormController tipoAdmissaoFormController;

	@Override
	protected CRUDFormController<TipoAdmissaoEntity> getFormController() {
		return tipoAdmissaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super TipoAdmissaoEntity> getEntityClass() {
		return TipoAdmissaoEntity.class;
	}

	@Override
	protected List<TipoAdmissaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TipoAdmissaoEntity> pesquisaDefault() {
		return (List<TipoAdmissaoEntity>) dao.getAll(getEntityClass());
	}

}