package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoIpiEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoIpiDAO extends
		AbstractCrudDAO<NfeDetalheImpostoIpiEntity> implements INfeDetalheImpostoIpiDAO {

	@Override
	public Class<NfeDetalheImpostoIpiEntity> getEntityClass() {
		return NfeDetalheImpostoIpiEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoIpiEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoIpiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIpiEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoIpiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIpiEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDetalheImpostoIpiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

	@Transactional
	public NfeDetalheImpostoIpiEntity getEntidade(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDetalheImpostoIpiEntity entidade = (NfeDetalheImpostoIpiEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDetalheImpostoIpiEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDetalheImpostoIpiEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Enquadramento do IPI" };
	}

}