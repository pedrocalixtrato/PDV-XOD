package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoCofinsDAO extends
		AbstractCrudDAO<NfeDetalheImpostoCofinsEntity> implements INfeDetalheImpostoCofinsDAO {

	@Override
	public Class<NfeDetalheImpostoCofinsEntity> getEntityClass() {
		return NfeDetalheImpostoCofinsEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoCofinsEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoCofinsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoCofinsEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoCofinsEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoCofinsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoCofinsEntity>();
		}
	}

	@Transactional
	public NfeDetalheImpostoCofinsEntity getEntidade(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDetalheImpostoCofinsEntity entidade = (NfeDetalheImpostoCofinsEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDetalheImpostoCofinsEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDetalheImpostoCofinsEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "CST COFINS" };
	}

}