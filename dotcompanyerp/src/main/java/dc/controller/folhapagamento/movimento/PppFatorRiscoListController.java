package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.PppFatorRiscoEntity;
import dc.servicos.dao.folhapagamento.movimento.PppFatorRiscoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class PppFatorRiscoListController extends
		CRUDListController<PppFatorRiscoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppFatorRiscoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppFatorRiscoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "ppp","dataInicio", "dataTermino", "tipo","fatorRisco","intensidade","tecnicaUtilizada","epcEficaz","epiEficaz",
				"caEpi","atendimentoNr061","atendimentoNr062","atendimentoNr063","atendimentoNr064","atendimentoNr065"};
	}

	@Override
	public Class<? super PppFatorRiscoEntity> getEntityClass() {
		return PppFatorRiscoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<PppFatorRiscoEntity> pesquisa(String valor) {
		try {
			List<PppFatorRiscoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppFatorRiscoEntity>();
		}
	}

	@Override
	protected CRUDFormController<PppFatorRiscoEntity> getFormController() {
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
	protected List<PppFatorRiscoEntity> pesquisaDefault() {
		try {
			List<PppFatorRiscoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppFatorRiscoEntity>();
		}
	}

}