package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.servicos.dao.contabilidade.lancamento.ILoteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LoteListController extends CRUDListController<LoteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILoteDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LoteFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "dataInclusao", "dataLiberacao" };
	}

	@Override
	public Class<? super LoteEntity> getEntityClass() {
		return LoteEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lote";
	}

	@Override
	protected List<LoteEntity> pesquisa(String valor) {
		try {
			List<LoteEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LoteEntity>();
		}
	}

	@Override
	protected CRUDFormController<LoteEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<LoteEntity> pesquisaDefault() {
		try {
			List<LoteEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LoteEntity>();
		}
	}

}