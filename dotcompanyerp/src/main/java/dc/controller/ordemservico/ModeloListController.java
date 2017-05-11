package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ModeloEntity;
import dc.servicos.dao.ordemservico.IModeloDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ModeloListController extends CRUDListController<ModeloEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IModeloDAO dao;
	
	@Autowired
	ModeloFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome","marca"};
	}

	@Override
	protected String getTitulo() {
		return "Modelo";
	}

	@Override
	protected List<ModeloEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaModelo";
	}

	@Override
	protected CRUDFormController<ModeloEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ModeloEntity> getEntityClass() {
		return ModeloEntity.class;
	}

	@Override
	protected List<ModeloEntity> pesquisaDefault() {
		return dao.getAll(ModeloEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
