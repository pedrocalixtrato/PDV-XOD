package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.ordemservico.OrdemServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class OrdemServicoEfetivadaListController extends CRUDListController<OrdemServicoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	OrdemServicoDAO dao; 
	
	@Autowired
	private OrdemServicoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"dataCadastro","cliente","valorTotalOs"};
	}

	@Override
	protected String getTitulo() {
		return "Ordem de serviço Efetivada";
	}

	@Override
	protected List<OrdemServicoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	} 
	
	@Override
	public String getViewIdentifier() {
		return "listaOrdemServicoEfetivada";
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
		return false;
	}

}
