package dc.servicos.dao.suprimentos.estoque;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosEstoqueContagemDetalheDAO")
public class ContagemDetalheDAO extends AbstractCrudDAO<ContagemDetalheEntity> {

	@Override
	public Class<ContagemDetalheEntity> getEntityClass() {
		return ContagemDetalheEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}