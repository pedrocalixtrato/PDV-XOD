package dc.controller.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.cadastro.GuiaAcumuladaEntity;
import dc.servicos.dao.folhapagamento.cadastro.GuiaAcumuladaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class GuiaAcumuladaListController extends
		CRUDListController<GuiaAcumuladaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private GuiaAcumuladaDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private GuiaAcumuladaFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "gpsTipo", "gpsCompetencia","gpsValorInss","gpsValorOutrasEnt","gpsDataPagamento", "irrfCompetencia",
				"irrfCodigoRecolhimento","irrfValorAcumulado","irrfDataPagamento","pisCompetencia" ,"pisValorAcumulado","pisDataPagamento"};
	}

	@Override
	public Class<? super GuiaAcumuladaEntity> getEntityClass() {
		return GuiaAcumuladaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<GuiaAcumuladaEntity> pesquisa(String valor) {
		try {
			List<GuiaAcumuladaEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GuiaAcumuladaEntity>();
		}
	}

	@Override
	protected CRUDFormController<GuiaAcumuladaEntity> getFormController() {
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
	protected List<GuiaAcumuladaEntity> pesquisaDefault() {
		try {
			List<GuiaAcumuladaEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GuiaAcumuladaEntity>();
		}
	}

}