package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.TipoRecebimento;
import dc.servicos.dao.financeiro.ITipoRecebimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoRecebimentoListController extends CRUDListController<TipoRecebimento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoRecebimentoDAO dao;

	@Autowired
	private TipoRecebimentoFormController tipoRecebimentoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "tipo", "descricao" };
	}

	@Override
	public Class<? super TipoRecebimento> getEntityClass() {
		return TipoRecebimento.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Recebimento";
	}

	@Override
	protected List<TipoRecebimento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TipoRecebimento> getFormController() {
		return tipoRecebimentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<TipoRecebimento> pesquisaDefault() {
		return (List<TipoRecebimento>) dao.getAll(getEntityClass());
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}
