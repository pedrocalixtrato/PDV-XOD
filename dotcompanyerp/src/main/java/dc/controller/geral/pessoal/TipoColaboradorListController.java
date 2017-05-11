package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.servicos.dao.geral.pessoal.ITipoColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoColaboradorListController extends
		CRUDListController<TipoColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoColaboradorDAO dao;

	@Autowired
	TipoColaboradorFormController tipoColaboradorFormController;

	@Override
	protected CRUDFormController<TipoColaboradorEntity> getFormController() {
		return tipoColaboradorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super TipoColaboradorEntity> getEntityClass() {
		return TipoColaboradorEntity.class;
	}

	@Override
	protected List<TipoColaboradorEntity> pesquisa(String valor) {
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
	protected List<TipoColaboradorEntity> pesquisaDefault() {
		return (List<TipoColaboradorEntity>) dao.getAll(getEntityClass());
	}

}