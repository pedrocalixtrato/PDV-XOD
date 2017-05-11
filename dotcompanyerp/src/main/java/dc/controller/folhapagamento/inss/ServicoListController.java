package dc.controller.folhapagamento.inss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.inss.ServicoEntity;
import dc.servicos.dao.folhapagamento.inss.ServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class ServicoListController extends CRUDListController<ServicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ServicoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ServicoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome" };
	}

	@Override
	public Class<? super ServicoEntity> getEntityClass() {
		return ServicoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<ServicoEntity> pesquisa(String valor) {
		try {
			List<ServicoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			System.out.println("1");
			e.printStackTrace();

			return new ArrayList<ServicoEntity>();
		}
	}

	@Override
	protected CRUDFormController<ServicoEntity> getFormController() {
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
	protected List<ServicoEntity> pesquisaDefault() {
		try {
			List<ServicoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			System.out.println("2");
			e.printStackTrace();

			return new ArrayList<ServicoEntity>();
		}
	}

}