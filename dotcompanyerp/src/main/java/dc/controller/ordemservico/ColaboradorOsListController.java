package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ColaboradorOsEntity;
import dc.servicos.dao.ordemservico.ColaboradorOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ColaboradorOsListController extends CRUDListController<ColaboradorOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ColaboradorOsDAO dao;
	
	@Autowired
	ColaboradorOsFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"nome","apelido","dataAdmissao"};
	}

	@Override
	protected String getTitulo() {
		return "Colaborador O.S";
	}

	@Override
	protected List<ColaboradorOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaColaboradorOs";
	}

	@Override
	protected CRUDFormController<ColaboradorOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ColaboradorOsEntity> getEntityClass() {
		return ColaboradorOsEntity.class;
	}

	@Override
	protected List<ColaboradorOsEntity> pesquisaDefault() {
		return dao.getAll(ColaboradorOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
