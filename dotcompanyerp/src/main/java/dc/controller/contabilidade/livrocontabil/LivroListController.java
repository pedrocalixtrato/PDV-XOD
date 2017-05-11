package dc.controller.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.servicos.dao.contabilidade.livrocontabil.ILivroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class LivroListController extends CRUDListController<LivroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ILivroDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LivroFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "competencia", "formaEscrituracao" };
	}

	@Override
	public Class<? super LivroEntity> getEntityClass() {
		return LivroEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Livro";
	}

	@Override
	protected List<LivroEntity> pesquisa(String valor) {
		try {
			List<LivroEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LivroEntity>();
		}
	}

	@Override
	protected CRUDFormController<LivroEntity> getFormController() {
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
	protected List<LivroEntity> pesquisaDefault() {
		try {
			List<LivroEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LivroEntity>();
		}
	}

}