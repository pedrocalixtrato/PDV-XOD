package dc.model.dao.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.CarroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CarroDAOImpl extends AbstractCrudDAO<CarroEntity> implements ICarroDAO {

	private static Logger logger = Logger.getLogger(CarroDAOImpl.class);

	@Override
	public Class<CarroEntity> getEntityClass() {
		return CarroEntity.class;
	}

	public List<CarroEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<CarroEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "GTIN", "Código interno", "Nome", "Descrição" };
	}

	@Override
	public CarroEntity find(Serializable id) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", id);

			CarroEntity ent = (CarroEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	
	public List<CarroEntity> list() {
		try {
			String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			sql = sql.replace("-", this.getEntityClass().getSimpleName()
					+ "(ent.id, ent.nome)");

			Query query = super.getSession().createQuery(sql);

			List<CarroEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<CarroEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<CarroEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<CarroEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<CarroEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}