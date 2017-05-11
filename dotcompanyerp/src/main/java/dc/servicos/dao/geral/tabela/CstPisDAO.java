package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstPisEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CstPisDAO extends AbstractCrudDAO<CstPisEntity> implements ICstPisDAO {

	@Override
	public Class<CstPisEntity> getEntityClass() {
		return CstPisEntity.class;
	}

	@Transactional
	public List<CstPisEntity> listaTodos() {
		return getSession().createQuery("from CstPis").list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.tabela.ICstPisDAO#procuraPorCodigo(java.lang.String)
	 */
	@Override
	@Transactional
	public CstPisEntity procuraPorCodigo(String codigo) {
		CstPisEntity cst = null;
		Criteria c = getSession().createCriteria(CstPisEntity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		cst = (CstPisEntity) c.uniqueResult();

		return cst;
	}

	@Transactional
	public List<CstPisEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstPis where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

}