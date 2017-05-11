package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.servicos.dao.geral.pessoal.IAtividadeForCliDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class AtividadeForCliListController extends
		CRUDListController<AtividadeForCliEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IAtividadeForCliDAO dao;

	@Autowired
	AtividadeForCliFormController atividadeForCliFormController;

	@Override
	protected CRUDFormController<AtividadeForCliEntity> getFormController() {
		return atividadeForCliFormController;
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
	public Class<? super AtividadeForCliEntity> getEntityClass() {
		return AtividadeForCliEntity.class;
	}

	@Override
	protected List<AtividadeForCliEntity> pesquisa(String valor) {
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
	protected List<AtividadeForCliEntity> pesquisaDefault() {
		return (List<AtividadeForCliEntity>) dao.getAll(getEntityClass());
	}

}