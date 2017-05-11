package dc.servicos.dao.geral;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class UfDAO extends AbstractCrudDAO<UfEntity> implements IUfDAO {

	@Override
	public Class<UfEntity> getEntityClass() {
		return UfEntity.class;
	}

	@Transactional
	public List<UfEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			// sql = sql.replace("-", this.getEntityClass().getSimpleName()
			// + "(ent.id, ent.nome, ent.sigla)");

			return getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<UfEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			return getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<UfEntity> query(String q) {
		String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
		sql = sql.replace("#", getEntityClass().getName());

		q = "%" + q.toLowerCase() + "%";

		return getSession().createQuery(sql).setParameter("q", q).list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "sigla" };
	}

	@Transactional
	public UfEntity find(String sigla) throws Exception {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.sigla = :sigla";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("sigla", sigla);

			UfEntity entity = (UfEntity) query.uniqueResult();

			// if (entity == null) {
			// entity = new UfEntity();
			// }

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public UfEntity getObject(String sigla) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.sigla = :sigla";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("sigla", sigla);

			UfEntity entity = (UfEntity) query.uniqueResult();

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}


}