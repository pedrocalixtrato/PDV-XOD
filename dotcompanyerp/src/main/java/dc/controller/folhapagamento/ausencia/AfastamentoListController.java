package dc.controller.folhapagamento.ausencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.servicos.dao.folhapagamento.ausencia.IAfastamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class AfastamentoListController extends
		CRUDListController<AfastamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IAfastamentoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private AfastamentoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "colaborador", "diasAfastado", "dataInicio",
				"dataFim" };
	}

	@Override
	public Class<? super AfastamentoEntity> getEntityClass() {
		return AfastamentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<AfastamentoEntity> pesquisa(String valor) {
		List<AfastamentoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<AfastamentoEntity> getFormController() {
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
	protected List<AfastamentoEntity> pesquisaDefault() {
		List<AfastamentoEntity> auxLista = this.pDAO.getAll();

		return auxLista;
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}