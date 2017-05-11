package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.PppExameMedicoEntity;
import dc.servicos.dao.folhapagamento.movimento.PppExameMedicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class PppExameMedicoListController extends
		CRUDListController<PppExameMedicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppExameMedicoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppExameMedicoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataUltimo", "tipo", "natureza", "ppp" };
	}

	@Override
	public Class<? super PppExameMedicoEntity> getEntityClass() {
		return PppExameMedicoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<PppExameMedicoEntity> pesquisa(String valor) {
		try {
			List<PppExameMedicoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppExameMedicoEntity>();
		}
	}

	@Override
	protected CRUDFormController<PppExameMedicoEntity> getFormController() {
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
	protected List<PppExameMedicoEntity> pesquisaDefault() {
		try {
			List<PppExameMedicoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppExameMedicoEntity>();
		}
	}

}