package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ClienteListController extends CRUDListController<ClienteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IClienteDAO dao;

	@Autowired
	private ClienteFormController clienteFormController;

	@Override
	protected CRUDFormController<ClienteEntity> getFormController() {
		return clienteFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "pessoa","situacaoForCli","atividadeForCli","operacaoFiscal", "desde", "contaTomador",
				"observacao", "gerarFinanceiro", "indicadorPreco", "tipoFrete",
				"formaDesconto", "porcentoDesconto", "limiteCredito" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super ClienteEntity> getEntityClass() {
		return ClienteEntity.class;
	}

	@Override
	protected List<ClienteEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
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
	protected List<ClienteEntity> pesquisaDefault() {
		return (List<ClienteEntity>) dao.getAll(getEntityClass());
	}

}