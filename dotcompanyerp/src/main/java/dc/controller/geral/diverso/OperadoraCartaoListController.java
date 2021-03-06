package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class OperadoraCartaoListController extends
		CRUDListController<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperadoraCartaoFormController operadoraCartaoFormController;

	/**
	 * CONSTRUTOR
	 */

	public OperadoraCartaoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<OperadoraCartaoEntity> getFormController() {
		return operadoraCartaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"contaCaixa", "nome", "bandeira","taxaAdm","taxaAdmDebito","valorAluguelPosPin","vencimentoAluguel",
				"fone1","fone2"};
	}

	@Override
	public Class<? super OperadoraCartaoEntity> getEntityClass() {
		return OperadoraCartaoEntity.class;
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
	protected List<OperadoraCartaoEntity> pesquisa(String valor) {
		try {
			List<OperadoraCartaoEntity> auxLista = (List<OperadoraCartaoEntity>) this.operadoraCartaoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<OperadoraCartaoEntity> pesquisaDefault() {
		try {
			List<OperadoraCartaoEntity> auxLista = (List<OperadoraCartaoEntity>) this.operadoraCartaoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}