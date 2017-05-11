package dc.model.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@Repository("pessoalEstadoCivilDAO")
public class EstadoCivilDAOImpl extends AbstractCrudDAO<EstadoCivilEntity>
		implements IEstadoCivilDAO {

	private static Logger logger = Logger.getLogger(EstadoCivilDAOImpl.class);

	@Override
	public Class<EstadoCivilEntity> getEntityClass() {
		return EstadoCivilEntity.class;
	}

	public List<EstadoCivilEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<EstadoCivilEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<EstadoCivilEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<EstadoCivilEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<EstadoCivilEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<EstadoCivilEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}