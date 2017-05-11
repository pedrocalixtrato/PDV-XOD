package dc.servicos.dao.framework.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.PapelMenu;
import dc.entidade.framework.SeguimentoEntity;

@Repository
public class SeguimentoDAO extends AbstractCrudDAO<SeguimentoEntity> implements ISeguimentoDAO {

	@Override
	public Class<SeguimentoEntity> getEntityClass() {
		return SeguimentoEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.ISeguimentoDAO#getSeguimentosMenusOrdered(dc.entidade.framework.SeguimentoEntity)
	 */
	@Override
	@Transactional
	public List<PapelMenu> getSeguimentosMenusOrdered(SeguimentoEntity p) {
		String hQ = "select pm from PapelMenu as pm "
				+ " where pm.papel.id= :papel_id order by menu.parent.id  nulls first , menu.caption";
		// return
		// getSession().createCriteria(PapelMenu.class).add(Restrictions.eq("papel_id",p.getId())).add(Order.asc("pm.m))addOrder(Order.asc("caption")).list();
		return getSession().createQuery(hQ).setInteger("papel_id", p.getId())
				.list();
	}

}