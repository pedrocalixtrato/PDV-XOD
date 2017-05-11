package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.CorEntity;
import dc.model.dao.ordemservico.ICorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CorListController extends CRUDListController<CorEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICorDAO dao;
	
	@Autowired
	CorFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Cor";
	}

	@Override
	protected List<CorEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaCor";
	}

	@Override
	protected CRUDFormController<CorEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super CorEntity> getEntityClass() {
		return CorEntity.class;
	}

	@Override
	protected List<CorEntity> pesquisaDefault() {
		return dao.getAll(CorEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
