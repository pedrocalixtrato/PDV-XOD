package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoIiDAO extends
		AbstractCrudDAO<NfeDetalheImpostoIiEntity> implements INfeDetalheImpostoIiDAO {

	@Override
	public Class<NfeDetalheImpostoIiEntity> getEntityClass() {
		return NfeDetalheImpostoIiEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoIiEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoIiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIiEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIiEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoIiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIiEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIiEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDetalheImpostoIiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIiEntity>();
		}
	}

	@Transactional
	public NfeDetalheImpostoIiEntity getEntidade(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDetalheImpostoIiEntity entidade = (NfeDetalheImpostoIiEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDetalheImpostoIiEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDetalheImpostoIiEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Valor (BC)" };
	}

}