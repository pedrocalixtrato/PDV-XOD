package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class OperadoraPlanoSaudeListController extends
		CRUDListController<OperadoraPlanoSaudeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperadoraPlanoSaudeFormController operadoraPlanoSaudeFormController;

	/**
	 * CONSTRUTOR
	 */

	public OperadoraPlanoSaudeListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<OperadoraPlanoSaudeEntity> getFormController() {
		return operadoraPlanoSaudeFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "registroAns", "nome" };
	}

	@Override
	public Class<? super OperadoraPlanoSaudeEntity> getEntityClass() {
		return OperadoraPlanoSaudeEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<OperadoraPlanoSaudeEntity> pesquisa(String valor) {
		try {
			List<OperadoraPlanoSaudeEntity> auxLista = (List<OperadoraPlanoSaudeEntity>) this.operadoraPlanoSaudeFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<OperadoraPlanoSaudeEntity> pesquisaDefault() {
		try {
			List<OperadoraPlanoSaudeEntity> auxLista = (List<OperadoraPlanoSaudeEntity>) this.operadoraPlanoSaudeFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}