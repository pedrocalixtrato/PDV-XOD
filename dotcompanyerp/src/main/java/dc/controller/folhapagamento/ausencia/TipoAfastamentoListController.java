package dc.controller.folhapagamento.ausencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.ausencia.TipoAfastamentoEntity;
import dc.servicos.dao.folhapagamento.ausencia.TipoAfastamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class TipoAfastamentoListController extends
		CRUDListController<TipoAfastamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private TipoAfastamentoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private TipoAfastamentoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao" };
	}

	@Override
	public Class<? super TipoAfastamentoEntity> getEntityClass() {
		return TipoAfastamentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<TipoAfastamentoEntity> pesquisa(String valor) {
		List<TipoAfastamentoEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<TipoAfastamentoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
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
	protected List<TipoAfastamentoEntity> pesquisaDefault() {
		List<TipoAfastamentoEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}