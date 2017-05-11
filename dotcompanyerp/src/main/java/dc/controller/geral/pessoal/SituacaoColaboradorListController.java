package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.servicos.dao.geral.pessoal.ISituacaoColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SituacaoColaboradorListController extends
		CRUDListController<SituacaoColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISituacaoColaboradorDAO dao;

	@Autowired
	private SituacaoColaboradorFormController situacaoColaboradorFormController;

	@Override
	protected CRUDFormController<SituacaoColaboradorEntity> getFormController() {
		return situacaoColaboradorFormController;
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
	public Class<? super SituacaoColaboradorEntity> getEntityClass() {
		return SituacaoColaboradorEntity.class;
	}

	@Override
	protected List<SituacaoColaboradorEntity> pesquisa(String valor) {
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
	protected List<SituacaoColaboradorEntity> pesquisaDefault() {
		return (List<SituacaoColaboradorEntity>) dao.getAll(getEntityClass());
	}

}