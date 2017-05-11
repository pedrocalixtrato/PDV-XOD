package dc.controller.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ContaCaixa;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContaCaixaListController extends CRUDListController<ContaCaixa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IContaCaixaDAO dao;

	@Autowired
	private ContaCaixaFormController contaCaixaFormController;

	@Override
	protected CRUDFormController<ContaCaixa> getFormController() {
		return contaCaixaFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"agenciaBanco","codigo", "digito", "nome", "tipoConta","descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super ContaCaixa> getEntityClass() {
		return ContaCaixa.class;
	}

	@Override
	protected List<ContaCaixa> pesquisa(String valor) {
		try {
			List<ContaCaixa> auxLista = (List<ContaCaixa>) this.dao.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContaCaixa>();
		}
	}

	@Override
	protected String getTitulo() {
		return "Conta Caixa";
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<ContaCaixa> pesquisaDefault() {
		try {
			List<ContaCaixa> auxLista = (List<ContaCaixa>) this.dao.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContaCaixa>();
		}
	}

}