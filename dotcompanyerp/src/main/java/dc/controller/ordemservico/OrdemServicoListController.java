package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.IOrdemServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class OrdemServicoListController extends CRUDListController<OrdemServicoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrdemServicoDAO dao; 
	
	@Autowired
	private OrdemServicoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"cliente","dataCadastro","valorServico","valorPeca","valorFrete","valorTotalOs","valorTotalServico","valorLucroServico","quantidadeParcelaCheque","primeiroVencimentoCheque",
				"quantidadeParcelaCarne","primeiroVencimentoCarne","quantidadeParcelaCartao","primeiroVencimentoCartao","quantidadeParcelaBoleto","primeiroVencimentoBoleto","dataExclusao","efetivada"};
	}

	@Override
	protected String getTitulo() {
		return "Ordem de serviço";
	}

	@Override
	protected List<OrdemServicoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	} 
	
	@Override
	public String getViewIdentifier() {
		return "listaOrdemServico";
	}

	@Override
	protected CRUDFormController<OrdemServicoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super OrdemServicoEntity> getEntityClass() {
		return OrdemServicoEntity.class;
	}

	@Override
	protected List<OrdemServicoEntity> pesquisaDefault() {
		return dao.getAll(OrdemServicoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}
