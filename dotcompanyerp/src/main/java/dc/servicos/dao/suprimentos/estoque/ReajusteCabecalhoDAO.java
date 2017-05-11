package dc.servicos.dao.suprimentos.estoque;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.ReajusteCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosEstoqueReajusteCabecalhoDAO")
public class ReajusteCabecalhoDAO extends
		AbstractCrudDAO<ReajusteCabecalhoEntity> implements IReajusteCabecalhoDAO {

	@Override
	public Class<ReajusteCabecalhoEntity> getEntityClass() {
		return ReajusteCabecalhoEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "data" };
	}

}