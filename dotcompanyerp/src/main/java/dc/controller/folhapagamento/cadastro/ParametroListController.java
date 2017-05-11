package dc.controller.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.cadastro.ParametroEntity;
import dc.servicos.dao.folhapagamento.cadastro.ParametroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class ParametroListController extends
		CRUDListController<ParametroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ParametroDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ParametroFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "competencia", "contribuiPis",
				"percentualAdiantam13" };
	}

	@Override
	public Class<? super ParametroEntity> getEntityClass() {
		return ParametroEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<ParametroEntity> pesquisa(String valor) {
		try {
			List<ParametroEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametroEntity>();
		}
	}

	@Override
	protected CRUDFormController<ParametroEntity> getFormController() {
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
	protected List<ParametroEntity> pesquisaDefault() {
		try {
			List<ParametroEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametroEntity>();
		}
	}

}