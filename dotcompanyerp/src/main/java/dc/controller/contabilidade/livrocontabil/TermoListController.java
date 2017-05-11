package dc.controller.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.servicos.dao.contabilidade.livrocontabil.ITermoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class TermoListController extends CRUDListController<TermoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ITermoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private TermoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "aberturaEncerramento", "numero",
				"numeroRegistro" };
	}

	@Override
	public Class<? super TermoEntity> getEntityClass() {
		return TermoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Termo";
	}

	@Override
	protected List<TermoEntity> pesquisa(String valor) {
		try {
			List<TermoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TermoEntity>();
		}
	}

	@Override
	protected CRUDFormController<TermoEntity> getFormController() {
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
	protected List<TermoEntity> pesquisaDefault() {
		try {
			List<TermoEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TermoEntity>();
		}
	}

}