package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.movimento.ValeTransporteEntity;
import dc.servicos.dao.folhapagamento.movimento.ValeTransporteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class ValeTransporteListController extends
		CRUDListController<ValeTransporteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ValeTransporteDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ValeTransporteFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "quantidade", "colaborador.matricula" };
	}

	@Override
	public Class<? super ValeTransporteEntity> getEntityClass() {
		return ValeTransporteEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<ValeTransporteEntity> pesquisa(String valor) {
		try {
			List<ValeTransporteEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ValeTransporteEntity>();
		}
	}

	@Override
	protected CRUDFormController<ValeTransporteEntity> getFormController() {
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
	protected List<ValeTransporteEntity> pesquisaDefault() {
		try {
			List<ValeTransporteEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ValeTransporteEntity>();
		}
	}

}