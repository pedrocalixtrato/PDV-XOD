package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.DefeitoEntity;
import dc.model.dao.ordemservico.IDefeitoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class DefeitoListController extends CRUDListController<DefeitoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IDefeitoDAO dao;
	
	@Autowired
	DefeitoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Defeito";
	}

	@Override
	protected List<DefeitoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaDefeito";
	}

	@Override
	protected CRUDFormController<DefeitoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super DefeitoEntity> getEntityClass() {
		return DefeitoEntity.class;
	}

	@Override
	protected List<DefeitoEntity> pesquisaDefault() {
		return dao.getAll(DefeitoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
