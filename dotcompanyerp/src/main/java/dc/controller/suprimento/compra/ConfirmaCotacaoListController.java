package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ConfirmaCotacaoListController extends
		CRUDListController<CotacaoCompraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ConfirmaCotacaoFormController confirmaCotacaoFormController;

	/**
	 * CONSTRUTOR
	 */

	public ConfirmaCotacaoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<CotacaoCompraEntity> getFormController() {
		return confirmaCotacaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "dataCotacao", "descricao", "situacao" };
	}

	@Override
	public Class<? super CotacaoCompraEntity> getEntityClass() {
		return CotacaoCompraEntity.class;
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
	protected List<CotacaoCompraEntity> pesquisa(String valor) {
		try {
			List<CotacaoCompraEntity> auxLista = (List<CotacaoCompraEntity>) this.confirmaCotacaoFormController.getBusiness()
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<CotacaoCompraEntity> pesquisaDefault() {
		try {
			List<CotacaoCompraEntity> auxLista = (List<CotacaoCompraEntity>) this.confirmaCotacaoFormController.getBusiness()
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}