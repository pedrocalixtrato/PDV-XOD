package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoIcmsDAO extends
		AbstractCrudDAO<NfeDetalheImpostoIcmsEntity> implements INfeDetalheImpostoIcmsDAO {

	@Override
	public Class<NfeDetalheImpostoIcmsEntity> getEntityClass() {
		return NfeDetalheImpostoIcmsEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoIcmsEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoIcmsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIcmsEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIcmsEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoIcmsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIcmsEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIcmsEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDetalheImpostoIcmsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIcmsEntity>();
		}
	}

	@Transactional
	public NfeDetalheImpostoIcmsEntity getEntidade(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDetalheImpostoIcmsEntity entidade = (NfeDetalheImpostoIcmsEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDetalheImpostoIcmsEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDetalheImpostoIcmsEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "CST ICMS" };
	}

}