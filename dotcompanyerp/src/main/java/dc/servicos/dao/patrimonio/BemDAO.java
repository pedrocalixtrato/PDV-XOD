package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.BemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class BemDAO extends AbstractCrudDAO<BemEntity> implements IBemDAO {

	@Override
	public Class<BemEntity> getEntityClass() {
		return BemEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<BemEntity> listarTodos() {
		try {
			String sql = "FROM BemEntity ent WHERE (1 = 1)";

			List<BemEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<BemEntity>();
		}
	}

	@Transactional
	public List<BemEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM BemEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<BemEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<BemEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.patrimonio.IBemDAO#bemLista()
	 */
	@Override
	@Transactional
	public List<BemEntity> bemLista() {
		try {
			String sql = "SELECT new BemEntity(ent.id, ent.nome) FROM BemEntity ent"
					+ " WHERE (1 = 1)";

			List<BemEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<BemEntity>();
		}
	}

}