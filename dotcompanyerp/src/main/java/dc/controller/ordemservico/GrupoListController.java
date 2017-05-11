package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.GrupoOsEntity;
import dc.servicos.dao.ordemservico.GrupoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component("ordemservicoGrupoListController")
@Scope("prototype")
public class GrupoListController extends CRUDListController<GrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	GrupoOsDAO dao;

	@Autowired
	GrupoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	protected String getTitulo() {
		return "Grupo";
	}

	@Override
	protected List<GrupoOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaGrupo";
	}

	@Override
	protected CRUDFormController<GrupoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super GrupoOsEntity> getEntityClass() {
		return GrupoOsEntity.class;
	}

	@Override
	protected List<GrupoOsEntity> pesquisaDefault() {
		return dao.getAll(GrupoOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}