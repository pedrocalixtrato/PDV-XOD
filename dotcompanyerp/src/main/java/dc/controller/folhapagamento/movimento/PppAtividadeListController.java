package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.PppAtividadeEntity;
import dc.servicos.dao.folhapagamento.movimento.PppAtividadeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class PppAtividadeListController extends
		CRUDListController<PppAtividadeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppAtividadeDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppAtividadeFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataInicio", "dataFim", "ppp" };
	}

	@Override
	public Class<? super PppAtividadeEntity> getEntityClass() {
		return PppAtividadeEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<PppAtividadeEntity> pesquisa(String valor) {
		try {
			List<PppAtividadeEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppAtividadeEntity>();
		}
	}

	@Override
	protected CRUDFormController<PppAtividadeEntity> getFormController() {
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
	protected List<PppAtividadeEntity> pesquisaDefault() {
		try {
			List<PppAtividadeEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppAtividadeEntity>();
		}
	}

}