package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.RequisicaoCompraEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class RequisicaoCompraListController extends CRUDListController<RequisicaoCompraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RequisicaoCompraFormController requisicaoCompraFormController;

	/**
	 * CONSTRUTOR
	 */

	public RequisicaoCompraListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<RequisicaoCompraEntity> getFormController() {
		return requisicaoCompraFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "dataRequisicao", "observacao", "colaborador","tipoRequisicao" };
	}

	@Override
	public Class<? super RequisicaoCompraEntity> getEntityClass() {
		return RequisicaoCompraEntity.class;
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
	protected List<RequisicaoCompraEntity> pesquisa(String valor) {
		try {
			List<RequisicaoCompraEntity> auxLista = (List<RequisicaoCompraEntity>) this.requisicaoCompraFormController.getBusiness()
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<RequisicaoCompraEntity> pesquisaDefault() {
		try {
			List<RequisicaoCompraEntity> auxLista = (List<RequisicaoCompraEntity>) this.requisicaoCompraFormController.getBusiness()
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}