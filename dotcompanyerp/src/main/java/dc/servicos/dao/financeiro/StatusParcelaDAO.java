package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class StatusParcelaDAO extends AbstractCrudDAO<StatusParcela> implements IStatusParcelaDAO{

	@Override
	public Class<StatusParcela> getEntityClass() {
		return StatusParcela.class;
	}

	@Transactional
	public List<StatusParcela> listaTodos() {
		return getSession().createQuery("from StatusParcela").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[]{"situacao", "descricao","procedimento"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IStatusParcelaDAO#procura(java.lang.String)
	 */
	@Override
	@Transactional
	public List<StatusParcela> procura(String query) {
		return getSession().createQuery("from StatusParcela where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IStatusParcelaDAO#query(java.lang.String)
	 */
	@Override
	@Transactional
	public List<StatusParcela> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from StatusParcela where lower(descricao) like :q")
				.setParameter("q", q).list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IStatusParcelaDAO#findBySituacao(java.lang.String)
	 */
	@Override
	@Transactional
	public StatusParcela findBySituacao(String descricao) {
		Criteria criteria = getSession().createCriteria(StatusParcela.class);
        criteria.add(Restrictions.eq("descricao", descricao));
        StatusParcela statusParcela = (StatusParcela) criteria.list().get(0);
        
        return statusParcela;
		
	}
	

}
