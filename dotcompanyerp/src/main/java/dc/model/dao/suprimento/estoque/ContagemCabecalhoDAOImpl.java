package dc.model.dao.suprimento.estoque;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContagemCabecalhoDAOImpl extends
		AbstractCrudDAO<ContagemCabecalhoEntity> implements
		ContagemCabecalhoDAO<ContagemCabecalhoEntity> {

	private static Logger logger = Logger
			.getLogger(ContagemCabecalhoDAOImpl.class);

	@Override
	public Class<ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	public List<ContagemCabecalhoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<ContagemCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ContagemCabecalhoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<ContagemCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ContagemCabecalhoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<ContagemCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Data da contagem" };
	}

}