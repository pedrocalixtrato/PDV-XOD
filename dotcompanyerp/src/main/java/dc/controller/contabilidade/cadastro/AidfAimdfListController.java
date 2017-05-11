package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.cadastro.AidfAimdfEntity;
import dc.servicos.dao.contabilidade.cadastro.IAidfAimdfDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class AidfAimdfListController extends
		CRUDListController<AidfAimdfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private IAidfAimdfDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private AidfAimdfFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] {"numero","dataValidade","dataAutorizacao", "numeroAutorizacao", "formularioDisponivel" };
	}

	@Override
	public Class<? super AidfAimdfEntity> getEntityClass() {
		return AidfAimdfEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "AIDF / AIMDF";
	}

	@Override
	protected List<AidfAimdfEntity> pesquisa(String valor) {
		try {
			List<AidfAimdfEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<AidfAimdfEntity>();
		}
	}

	@Override
	protected CRUDFormController<AidfAimdfEntity> getFormController() {
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
	protected List<AidfAimdfEntity> pesquisaDefault() {
		try {
			List<AidfAimdfEntity> auxLista = this.pDAO.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<AidfAimdfEntity>();
		}
	}

}