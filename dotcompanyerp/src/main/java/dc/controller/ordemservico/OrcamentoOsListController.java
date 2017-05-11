package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.model.dao.ordemservico.IOrcamentoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class OrcamentoOsListController extends CRUDListController<OrcamentoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrcamentoOsDAO dao; 
	
	@Autowired
	private OrcamentoOsFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nomeVendedor","formaPagamento","nome","endereco","fone", "placa","marca","modelo","cor",
				"ano","motor","motorizacao"};
	}

	@Override
	protected String getTitulo() {
		return "O.S Simples";
	}

	@Override
	protected List<OrcamentoOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaOrcamentoOs";
	}

	@Override
	protected CRUDFormController<OrcamentoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super OrcamentoOsEntity> getEntityClass() {
		return OrcamentoOsEntity.class;
	}

	@Override
	protected List<OrcamentoOsEntity> pesquisaDefault() {
		return dao.getAll(OrcamentoOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

}
