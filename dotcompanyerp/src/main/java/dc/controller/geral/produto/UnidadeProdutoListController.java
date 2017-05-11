package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class UnidadeProdutoListController extends
		CRUDListController<UnidadeProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UnidadeProdutoFormController unidadeProdutoFormController;

	/**
	 * CONSTRUTOR
	 */

	public UnidadeProdutoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<UnidadeProdutoEntity> getFormController() {
		return unidadeProdutoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "sigla", "descricao", "podeFracionar" };
	}

	@Override
	public Class<? super UnidadeProdutoEntity> getEntityClass() {
		return UnidadeProdutoEntity.class;
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
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<UnidadeProdutoEntity> pesquisa(String valor) {
		try {
			List<UnidadeProdutoEntity> auxLista = (List<UnidadeProdutoEntity>) this.unidadeProdutoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<UnidadeProdutoEntity> pesquisaDefault() {
		try {
			List<UnidadeProdutoEntity> auxLista = (List<UnidadeProdutoEntity>) this.unidadeProdutoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}