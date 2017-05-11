package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FornecedorCotacaoListController extends
		CRUDListController<FornecedorCotacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FornecedorCotacaoFormController fornecedorCotacaoFormController;

	/**
	 * CONSTRUTOR
	 */

	public FornecedorCotacaoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<FornecedorCotacaoEntity> getFormController() {
		return fornecedorCotacaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "fornecedor" };
	}

	@Override
	public Class<? super FornecedorCotacaoEntity> getEntityClass() {
		return FornecedorCotacaoEntity.class;
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
	protected List<FornecedorCotacaoEntity> pesquisa(String valor) {
		return null;
		/*try {
			List<FornecedorCotacaoEntity> auxLista = (List<FornecedorCotacaoEntity>) this.fornecedorCotacaoFormController.getBusiness()
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}*/
	}

	@Override
	protected List<FornecedorCotacaoEntity> pesquisaDefault() {
		return null;
		/*try {
			List<FornecedorCotacaoEntity> auxLista = (List<FornecedorCotacaoEntity>) this.fornecedorCotacaoFormController.getBusiness()
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}*/
	}

}