package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.TipoServicoOsEntity;
import dc.model.dao.ordemservico.ITipoServicoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoServicoListController extends CRUDListController<TipoServicoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoServicoOsDAO dao;
	
	@Autowired
	TipoServicoFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Tipo de Serviço";
	}

	@Override
	protected List<TipoServicoOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaTipoServico";
	}

	@Override
	protected CRUDFormController<TipoServicoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super TipoServicoOsEntity> getEntityClass() {
		return TipoServicoOsEntity.class;
	}

	@Override
	protected List<TipoServicoOsEntity> pesquisaDefault() {
		return dao.getAll(TipoServicoOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
