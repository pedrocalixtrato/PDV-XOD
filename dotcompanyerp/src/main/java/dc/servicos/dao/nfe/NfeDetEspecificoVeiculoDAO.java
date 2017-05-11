package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetEspecificoVeiculoDAO extends
		AbstractCrudDAO<NfeDetEspecificoVeiculoEntity> {

	@Override
	public Class<NfeDetEspecificoVeiculoEntity> getEntityClass() {
		return NfeDetEspecificoVeiculoEntity.class;
	}

	@Transactional
	public List<NfeDetEspecificoVeiculoEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<NfeDetEspecificoVeiculoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoVeiculoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoVeiculoEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", "%" + s + "%");

			List<NfeDetEspecificoVeiculoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoVeiculoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoVeiculoEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDetEspecificoVeiculoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoVeiculoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "CST PIS" };
	}

}