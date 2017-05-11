package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.TipoItemSpedEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TipoItemSpedDAO extends AbstractCrudDAO<TipoItemSpedEntity> implements ITipoItemSpedDAO {

	@Override
	public Class<TipoItemSpedEntity> getEntityClass() {
		return TipoItemSpedEntity.class;
	}

	@Transactional
	public List<TipoItemSpedEntity> listaTodos() {
		return getSession().createQuery("from TipoItemSped").list();
	}

	@Transactional
	public List<TipoItemSpedEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from TipoItemSped where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

}