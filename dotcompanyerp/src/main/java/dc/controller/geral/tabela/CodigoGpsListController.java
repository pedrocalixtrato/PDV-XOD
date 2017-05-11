package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CodigoGpsEntity;
import dc.servicos.dao.geral.tabela.ICodigoGpsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CodigoGpsListController extends CRUDListController<CodigoGpsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICodigoGpsDAO dao;

	@Autowired
	private CodigoGpsFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super CodigoGpsEntity> getEntityClass() {
		return CodigoGpsEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Código GPS";
	}

	@Override
	protected List<CodigoGpsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CodigoGpsEntity> getFormController() {
		return pController;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CodigoGpsEntity> pesquisaDefault() {
		return (List<CodigoGpsEntity>) dao.getAll(getEntityClass());
	}

}