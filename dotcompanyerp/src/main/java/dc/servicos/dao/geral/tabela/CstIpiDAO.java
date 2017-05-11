package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstIpiEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CstIpiDAO extends AbstractCrudDAO<CstIpiEntity> implements ICstIpiDAO {

	@Override
	public Class<CstIpiEntity> getEntityClass() {
		return CstIpiEntity.class;
	}

	@Transactional
	public List<CstIpiEntity> listaTodos() {
		return getSession().createQuery("from CstIpi").list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.tabela.ICstIpiDAO#procuraPorCodigo(java.lang.String)
	 */
	@Override
	@Transactional
	public CstIpiEntity procuraPorCodigo(String codigo) {
		CstIpiEntity cst = null;
		Criteria c = getSession().createCriteria(CstIpiEntity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		cst = (CstIpiEntity) c.uniqueResult();

		return cst;
	}

	@Transactional
	public List<CstIpiEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstIpi where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}