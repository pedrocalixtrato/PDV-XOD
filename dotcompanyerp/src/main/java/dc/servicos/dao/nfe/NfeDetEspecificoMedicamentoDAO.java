package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetEspecificoMedicamentoDAO extends
		AbstractCrudDAO<NfeDetEspecificoMedicamentoEntity> implements INfeDetEspecificoMedicamentoDAO {

	@Override
	public Class<NfeDetEspecificoMedicamentoEntity> getEntityClass() {
		return NfeDetEspecificoMedicamentoEntity.class;
	}

	@Transactional
	public List<NfeDetEspecificoMedicamentoEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<NfeDetEspecificoMedicamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoMedicamentoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoMedicamentoEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", "%" + s + "%");

			List<NfeDetEspecificoMedicamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoMedicamentoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoMedicamentoEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			List<NfeDetEspecificoMedicamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoMedicamentoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "CST PIS" };
	}

}