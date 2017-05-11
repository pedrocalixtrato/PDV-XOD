package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.IndiceAtualizacaoEntity;
import dc.servicos.dao.patrimonio.IndiceAtualizacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class IndiceAtualizacaoListController extends CRUDListController<IndiceAtualizacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IndiceAtualizacaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private IndiceAtualizacaoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataIndice", "nome", "valor" };
	}

	@Override
	public Class<? super IndiceAtualizacaoEntity> getEntityClass() {
		return IndiceAtualizacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Índice de Atualização";
	}

	@Override
	protected List<IndiceAtualizacaoEntity> pesquisa(String valor) {
		try {
			List<IndiceAtualizacaoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceAtualizacaoEntity>();
		}
	}

	@Override
	protected CRUDFormController<IndiceAtualizacaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_indice_atualizacao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<IndiceAtualizacaoEntity> pesquisaDefault() {
		try {
			List<IndiceAtualizacaoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceAtualizacaoEntity>();
		}
	}

}