package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoIssqnDAO extends
		AbstractCrudDAO<NfeDetalheImpostoIssqnEntity> implements INfeDetalheImpostoIssqnDAO {

	@Override
	public Class<NfeDetalheImpostoIssqnEntity> getEntityClass() {
		return NfeDetalheImpostoIssqnEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoIssqnEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoIssqnEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIssqnEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIssqnEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoIssqnEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIssqnEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIssqnEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDetalheImpostoIssqnEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIssqnEntity>();
		}
	}

	@Transactional
	public NfeDetalheImpostoIssqnEntity getEntidade(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDetalheImpostoIssqnEntity entidade = (NfeDetalheImpostoIssqnEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDetalheImpostoIssqnEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDetalheImpostoIssqnEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Base do cálculo do ISSQN" };
	}

}