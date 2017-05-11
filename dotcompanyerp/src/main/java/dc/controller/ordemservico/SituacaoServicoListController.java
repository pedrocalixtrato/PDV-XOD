package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.SituacaoServicoEntity;
import dc.model.dao.ordemservico.ISituacaoServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class SituacaoServicoListController extends CRUDListController<SituacaoServicoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISituacaoServicoDAO dao;
	
	@Autowired
	SituacaoServicoFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Situação de Serviço";
	}

	@Override
	protected List<SituacaoServicoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaSituacaoServico";
	}

	@Override
	protected CRUDFormController<SituacaoServicoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super SituacaoServicoEntity> getEntityClass() {
		return SituacaoServicoEntity.class;
	}

	@Override
	protected List<SituacaoServicoEntity> pesquisaDefault() {
		return dao.getAll(SituacaoServicoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
