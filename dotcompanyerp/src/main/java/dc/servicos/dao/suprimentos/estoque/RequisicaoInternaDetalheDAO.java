package dc.servicos.dao.suprimentos.estoque;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.RequisicaoInternaDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosEstoqueRequisicaoInternaDetalheDAO")
public class RequisicaoInternaDetalheDAO extends
		AbstractCrudDAO<RequisicaoInternaDetalheEntity> {

	@Override
	public Class<RequisicaoInternaDetalheEntity> getEntityClass() {
		return RequisicaoInternaDetalheEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}