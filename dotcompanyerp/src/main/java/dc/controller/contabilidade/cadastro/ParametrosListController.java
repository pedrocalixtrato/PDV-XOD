package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.cadastro.ParametrosEntity;
import dc.servicos.dao.contabilidade.cadastro.IParametrosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Component
@Scope("prototype")
public class ParametrosListController extends
		CRUDListController<ParametrosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IParametrosDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ParametrosFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "mascara", "niveis" };
	}

	@Override
	public Class<? super ParametrosEntity> getEntityClass() {
		return ParametrosEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Parâmetros";
	}

	@Override
	protected List<ParametrosEntity> pesquisa(String valor) {
		try {
			List<ParametrosEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametrosEntity>();
		}
	}

	@Override
	protected CRUDFormController<ParametrosEntity> getFormController() {
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
	protected List<ParametrosEntity> pesquisaDefault() {
		try {
			List<ParametrosEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametrosEntity>();
		}
	}

}