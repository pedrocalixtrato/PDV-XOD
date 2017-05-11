package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.PppCatEntity;
import dc.servicos.dao.folhapagamento.movimento.PppCatDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class PppCatListController extends CRUDListController<PppCatEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppCatDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppCatFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "numeroCat", "dataAfastamento",
				"dataRegistro", "ppp" };
	}

	@Override
	public Class<? super PppCatEntity> getEntityClass() {
		return PppCatEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<PppCatEntity> pesquisa(String valor) {
		try {
			List<PppCatEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppCatEntity>();
		}
	}

	@Override
	protected CRUDFormController<PppCatEntity> getFormController() {
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
	protected List<PppCatEntity> pesquisaDefault() {
		try {
			List<PppCatEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppCatEntity>();
		}
	}

}