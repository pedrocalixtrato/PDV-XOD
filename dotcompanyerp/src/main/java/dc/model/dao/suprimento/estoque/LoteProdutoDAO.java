package dc.model.dao.suprimento.estoque;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LoteProdutoDAO extends AbstractCrudDAO<LoteProdutoEntity> {

	@Override
	public Class<LoteProdutoEntity> getEntityClass() {
		return LoteProdutoEntity.class;
	}

	@Transactional
	public List<LoteProdutoEntity> listaTodos() {
		return getSession().createQuery("from LoteProduto").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}
	
	@Transactional
	public List<LoteProdutoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from LoteProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

}
