package dc.controller.folhapagamento.movimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.AlteracaoSalarialEntity;
import dc.servicos.dao.folhapagamento.movimento.AlteracaoSalarialDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class AlteracaoSalarialListController extends
		CRUDListController<AlteracaoSalarialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private AlteracaoSalarialDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private AlteracaoSalarialFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "numero", "bem.nome", "seguradora.nome" };
	}

	@Override
	public Class<? super AlteracaoSalarialEntity> getEntityClass() {
		return AlteracaoSalarialEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<AlteracaoSalarialEntity> pesquisa(String valor) {
		List<AlteracaoSalarialEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<AlteracaoSalarialEntity> getFormController() {
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
	protected List<AlteracaoSalarialEntity> pesquisaDefault() {
		List<AlteracaoSalarialEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}