package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstCofinsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CstCofinsDAO extends AbstractCrudDAO<CstCofinsEntity> implements ICstCofinsDAO {

	@Override
	public Class<CstCofinsEntity> getEntityClass() {
		return CstCofinsEntity.class;
	}

	@Transactional
	public List<CstCofinsEntity> listaTodos() {
		return getSession().createQuery("from CstCofins").list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.tabela.ICstCofinsDAO#procuraPorCodigo(java.lang.String)
	 */
	@Override
	@Transactional
	public CstCofinsEntity procuraPorCodigo(String codigo) {
		CstCofinsEntity cst = null;
		Criteria c = getSession().createCriteria(CstCofinsEntity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		cst = (CstCofinsEntity) c.uniqueResult();

		return cst;
	}

	@Transactional
	public List<CstCofinsEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from CstCofins where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}