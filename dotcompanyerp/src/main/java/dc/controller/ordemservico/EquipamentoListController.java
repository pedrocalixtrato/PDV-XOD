package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.EquipamentoEntity;
import dc.model.dao.ordemservico.IEquipamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class EquipamentoListController extends CRUDListController<EquipamentoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IEquipamentoDAO dao;
	
	@Autowired
	EquipamentoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"filial","equipamento","descricao","observacao"};
	}

	@Override
	protected String getTitulo() {
		return "Equipamento";
	}

	@Override
	protected List<EquipamentoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaEquipamento";
	}

	@Override
	protected CRUDFormController<EquipamentoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super EquipamentoEntity> getEntityClass() {
		return EquipamentoEntity.class;
	}

	@Override
	protected List<EquipamentoEntity> pesquisaDefault() {
		return dao.getAll(EquipamentoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
