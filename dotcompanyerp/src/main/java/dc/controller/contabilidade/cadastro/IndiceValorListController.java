package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.cadastro.IndiceValorEntity;
import dc.servicos.dao.contabilidade.cadastro.IIndiceValorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class IndiceValorListController extends
		CRUDListController<IndiceValorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IIndiceValorDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private IndiceValorFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] {"indice", "dataIndice", "valor" };
	}

	@Override
	public Class<? super IndiceValorEntity> getEntityClass() {
		return IndiceValorEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Índice Valor";
	}

	@Override
	protected List<IndiceValorEntity> pesquisa(String valor) {
		try {
			List<IndiceValorEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceValorEntity>();
		}
	}

	@Override
	protected CRUDFormController<IndiceValorEntity> getFormController() {
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
	protected List<IndiceValorEntity> pesquisaDefault() {
		try {
			List<IndiceValorEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceValorEntity>();
		}
	}

}