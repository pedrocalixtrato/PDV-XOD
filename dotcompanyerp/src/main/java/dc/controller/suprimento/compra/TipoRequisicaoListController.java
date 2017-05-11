package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.TipoRequisicaoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoRequisicaoListController extends
		CRUDListController<TipoRequisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoRequisicaoFormController tipoRequisicaoFormController;

	/**
	 * CONSTRUTOR
	 */

	public TipoRequisicaoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<TipoRequisicaoEntity> getFormController() {
		return tipoRequisicaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao" };
	}

	@Override
	public Class<? super TipoRequisicaoEntity> getEntityClass() {
		return TipoRequisicaoEntity.class;
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
		return true;
	}

	@Override
	protected List<TipoRequisicaoEntity> pesquisa(String valor) {
		try {
			List<TipoRequisicaoEntity> auxLista = (List<TipoRequisicaoEntity>) this.tipoRequisicaoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<TipoRequisicaoEntity> pesquisaDefault() {
		try {
			List<TipoRequisicaoEntity> auxLista = (List<TipoRequisicaoEntity>) this.tipoRequisicaoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}