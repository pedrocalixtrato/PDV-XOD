package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.TipoColaboradorOsEntity;
import dc.servicos.dao.ordemservico.TipoColaboradorOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoColaboradorOsListController extends CRUDListController<TipoColaboradorOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	TipoColaboradorOsDAO dao;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Tipo de coladorador O.S";
	}

	@Override
	protected List<TipoColaboradorOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaTipoColaboradorOs";
	}

	@Override
	protected CRUDFormController<TipoColaboradorOsEntity> getFormController() {
		return null;//formController;
	}

	@Override
	public Class<? super TipoColaboradorOsEntity> getEntityClass() {
		return TipoColaboradorOsEntity.class;
	}

	@Override
	protected List<TipoColaboradorOsEntity> pesquisaDefault() {
		return dao.getAll(TipoColaboradorOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
