package dc.controller.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.cadastro.EventoEntity;
import dc.servicos.dao.folhapagamento.cadastro.EventoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * 
 */

@Controller
@Scope("prototype")
public class EventoListController extends CRUDListController<EventoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EventoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EventoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "codigo", "tipo", "unidade", "taxa" };
	}

	@Override
	public Class<? super EventoEntity> getEntityClass() {
		return EventoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<EventoEntity> pesquisa(String valor) {
		try {
			List<EventoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EventoEntity>();
		}
	}

	@Override
	protected CRUDFormController<EventoEntity> getFormController() {
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
	protected List<EventoEntity> pesquisaDefault() {
		try {
			List<EventoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EventoEntity>();
		}
	}

}