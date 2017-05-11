package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.model.dao.ordemservico.ISubGrupoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component("ordemservicoSubGrupoListController")
@Scope("prototype")
public class SubGrupoListController extends
		CRUDListController<SubGrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubGrupoOsDAO dao;

	@Autowired
	SubGrupoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "grupo" };
	}

	@Override
	protected String getTitulo() {
		return "SubGrupo";
	}

	@Override
	protected List<SubGrupoOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaSubGrupo";
	}

	@Override
	protected CRUDFormController<SubGrupoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super SubGrupoOsEntity> getEntityClass() {
		return SubGrupoOsEntity.class;
	}

	@Override
	protected List<SubGrupoOsEntity> pesquisaDefault() {
		return dao.getAll(SubGrupoOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

}