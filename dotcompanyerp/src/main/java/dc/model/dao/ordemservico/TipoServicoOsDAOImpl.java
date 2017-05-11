package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.TipoServicoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TipoServicoOsDAOImpl extends AbstractCrudDAO<TipoServicoOsEntity>  {

	private static Logger logger = Logger.getLogger(TipoServicoOsDAOImpl.class);

	@Override
	public Class<TipoServicoOsEntity> getEntityClass() {
		return TipoServicoOsEntity.class;
	}

	public List<TipoServicoOsEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<TipoServicoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TipoServicoOsEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.descricao LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("descricao", value);

			List<TipoServicoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TipoServicoOsEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(descricao) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<TipoServicoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

}