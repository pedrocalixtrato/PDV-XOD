package dc.controller.folhapagamento.inss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.inss.InssEntity;
import dc.servicos.dao.folhapagamento.inss.InssDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class InssListController extends CRUDListController<InssEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private InssDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private InssFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "competencia" };
	}

	@Override
	public Class<? super InssEntity> getEntityClass() {
		return InssEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<InssEntity> pesquisa(String valor) {
		try {
			List<InssEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<InssEntity>();
		}
	}

	@Override
	protected CRUDFormController<InssEntity> getFormController() {
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
	protected List<InssEntity> pesquisaDefault() {
		try {
			List<InssEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<InssEntity>();
		}
	}

}