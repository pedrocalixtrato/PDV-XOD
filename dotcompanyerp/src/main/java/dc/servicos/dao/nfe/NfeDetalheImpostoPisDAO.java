package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoPisDAO extends
		AbstractCrudDAO<NfeDetalheImpostoPisEntity> implements INfeDetalheImpostoPisDAO {

	@Override
	public Class<NfeDetalheImpostoPisEntity> getEntityClass() {
		return NfeDetalheImpostoPisEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoPisEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoPisEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoPisEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoPisEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoPisEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoPisEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoPisEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDetalheImpostoPisEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoPisEntity>();
		}
	}

	@Transactional
	public NfeDetalheImpostoPisEntity getEntidade(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDetalheImpostoPisEntity entidade = (NfeDetalheImpostoPisEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDetalheImpostoPisEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDetalheImpostoPisEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "CST PIS" };
	}

}