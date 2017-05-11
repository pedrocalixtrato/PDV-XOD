package dc.model.dao.suprimento.estoque;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContagemDetalheDAOImpl extends
		AbstractCrudDAO<ContagemDetalheEntity> implements
		ContagemDetalheDAO<ContagemDetalheEntity> {

	private static Logger logger = Logger
			.getLogger(ContagemDetalheDAOImpl.class);

	@Override
	public Class<ContagemDetalheEntity> getEntityClass() {
		return ContagemDetalheEntity.class;
	}

	public List<ContagemDetalheEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<ContagemDetalheEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ContagemDetalheEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<ContagemDetalheEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ContagemDetalheEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<ContagemDetalheEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "" };
	}

	/**
	 * 
	 */

	public List<ContagemDetalheEntity> list(ContagemCabecalhoEntity entity) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.contagemCabecalho.id = :id";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", entity.getId());

			List<ContagemDetalheEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}