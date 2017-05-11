package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.AcessorioEntity;
import dc.model.dao.ordemservico.IAcessorioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class AcessorioListController extends CRUDListController<AcessorioEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IAcessorioDAO dao;
	
	@Autowired
	AcessorioFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Acessório";
	}

	@Override
	protected List<AcessorioEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaAcessorio";
	}

	@Override
	protected CRUDFormController<AcessorioEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super AcessorioEntity> getEntityClass() {
		return AcessorioEntity.class;
	}

	@Override
	protected List<AcessorioEntity> pesquisaDefault() {
		return dao.getAll(AcessorioEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
