package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.servicos.dao.geral.pessoal.ITipoRelacionamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoRelacionamentoListController extends
		CRUDListController<TipoRelacionamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoRelacionamentoDAO dao;

	@Autowired
	TipoRelacionamentoFormController tipoRelacionamentoFormController;

	@Override
	protected CRUDFormController<TipoRelacionamentoEntity> getFormController() {
		return tipoRelacionamentoFormController;
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
	public Class<? super TipoRelacionamentoEntity> getEntityClass() {
		return TipoRelacionamentoEntity.class;
	}

	@Override
	protected List<TipoRelacionamentoEntity> pesquisa(String valor) {
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
	protected List<TipoRelacionamentoEntity> pesquisaDefault() {
		return (List<TipoRelacionamentoEntity>) dao.getAll(getEntityClass());
	}

}