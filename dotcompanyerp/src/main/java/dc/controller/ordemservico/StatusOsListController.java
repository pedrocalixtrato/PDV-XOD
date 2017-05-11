package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.StatusOsEntity;
import dc.model.dao.ordemservico.IStatusOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class StatusOsListController extends CRUDListController<StatusOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IStatusOsDAO dao;
	
	@Autowired
	StatusOsFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "StatusOs";
	}

	@Override
	protected List<StatusOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaStatusOs";
	}

	@Override
	protected CRUDFormController<StatusOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super StatusOsEntity> getEntityClass() {
		return StatusOsEntity.class;
	}

	@Override
	protected List<StatusOsEntity> pesquisaDefault() {
		return dao.getAll(StatusOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
