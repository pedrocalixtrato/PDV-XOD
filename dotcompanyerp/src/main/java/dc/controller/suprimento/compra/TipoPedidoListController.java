package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoPedidoListController extends
		CRUDListController<TipoPedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoPedidoFormController tipoPedidoFormController;

	/**
	 * CONSTRUTOR
	 */

	public TipoPedidoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<TipoPedidoEntity> getFormController() {
		return tipoPedidoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super TipoPedidoEntity> getEntityClass() {
		return TipoPedidoEntity.class;
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
	protected List<TipoPedidoEntity> pesquisa(String valor) {
		try {
			List<TipoPedidoEntity> auxLista = (List<TipoPedidoEntity>) this.tipoPedidoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<TipoPedidoEntity> pesquisaDefault() {
		try {
			List<TipoPedidoEntity> auxLista = (List<TipoPedidoEntity>) this.tipoPedidoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}