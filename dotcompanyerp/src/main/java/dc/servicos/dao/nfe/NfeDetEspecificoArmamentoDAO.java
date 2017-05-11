package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetEspecificoArmamentoDAO extends AbstractCrudDAO<NfeDetEspecificoArmamentoEntity> implements INfeDetEspecificoArmamentoDAO {

	@Override
	public Class<NfeDetEspecificoArmamentoEntity> getEntityClass() {
		return NfeDetEspecificoArmamentoEntity.class;
	}

	@Transactional
	public List<NfeDetEspecificoArmamentoEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetEspecificoArmamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoArmamentoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoArmamentoEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetEspecificoArmamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoArmamentoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoArmamentoEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			List<NfeDetEspecificoArmamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoArmamentoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "CST PIS" };
	}

}