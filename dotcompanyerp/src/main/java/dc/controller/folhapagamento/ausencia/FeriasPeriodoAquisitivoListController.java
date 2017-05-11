package dc.controller.folhapagamento.ausencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class FeriasPeriodoAquisitivoListController extends
		CRUDListController<FeriasPeriodoAquisitivoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IFeriasPeriodoAquisitivoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private FeriasPeriodoAquisitivoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataInicio", "dataFim" };
	}

	@Override
	public Class<? super FeriasPeriodoAquisitivoEntity> getEntityClass() {
		return FeriasPeriodoAquisitivoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<FeriasPeriodoAquisitivoEntity> pesquisa(String valor) {
		List<FeriasPeriodoAquisitivoEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<FeriasPeriodoAquisitivoEntity> getFormController() {
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
	protected List<FeriasPeriodoAquisitivoEntity> pesquisaDefault() {
		List<FeriasPeriodoAquisitivoEntity> auxLista = this.pDAO.getAll();

		return auxLista;
	}

}