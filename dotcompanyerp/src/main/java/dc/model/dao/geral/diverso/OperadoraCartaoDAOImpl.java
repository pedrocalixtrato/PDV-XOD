package dc.model.dao.geral.diverso;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class OperadoraCartaoDAOImpl extends
		AbstractCrudDAO<OperadoraCartaoEntity> implements
		IOperadoraCartaoDAO {

	private static Logger logger = Logger
			.getLogger(OperadoraCartaoDAOImpl.class);

	@Override
	public Class<OperadoraCartaoEntity> getEntityClass() {
		return OperadoraCartaoEntity.class;
	}

	public List<OperadoraCartaoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<OperadoraCartaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OperadoraCartaoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<OperadoraCartaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OperadoraCartaoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<OperadoraCartaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "bandeira" };
	}

}