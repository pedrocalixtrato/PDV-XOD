package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.CombustivelEntity;
import dc.model.dao.ordemservico.ICombustivelDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CombustivelListController extends CRUDListController<CombustivelEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICombustivelDAO dao;
	
	@Autowired
	CombustivelFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Combustível";
	}

	@Override
	protected List<CombustivelEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaCombustivel";
	}

	@Override
	protected CRUDFormController<CombustivelEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super CombustivelEntity> getEntityClass() {
		return CombustivelEntity.class;
	}

	@Override
	protected List<CombustivelEntity> pesquisaDefault() {
		return dao.getAll(CombustivelEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
