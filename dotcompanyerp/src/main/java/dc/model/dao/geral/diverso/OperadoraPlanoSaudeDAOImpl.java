package dc.model.dao.geral.diverso;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class OperadoraPlanoSaudeDAOImpl extends
		AbstractCrudDAO<OperadoraPlanoSaudeEntity> implements
		OperadoraPlanoSaudeDAO<OperadoraPlanoSaudeEntity> {

	private static Logger logger = Logger
			.getLogger(OperadoraPlanoSaudeDAOImpl.class);

	@Override
	public Class<OperadoraPlanoSaudeEntity> getEntityClass() {
		return OperadoraPlanoSaudeEntity.class;
	}

	public List<OperadoraPlanoSaudeEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<OperadoraPlanoSaudeEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OperadoraPlanoSaudeEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<OperadoraPlanoSaudeEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OperadoraPlanoSaudeEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<OperadoraPlanoSaudeEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "registroAns", "nome" };
	}

}