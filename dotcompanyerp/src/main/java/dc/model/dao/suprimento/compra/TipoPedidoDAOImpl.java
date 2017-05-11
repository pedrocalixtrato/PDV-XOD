package dc.model.dao.suprimento.compra;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.suprimentos.compra.ITipoPedidoDAO;

@Repository
public class TipoPedidoDAOImpl extends AbstractCrudDAO<TipoPedidoEntity> implements ITipoPedidoDAO {

	private static Logger logger = Logger.getLogger(TipoPedidoDAOImpl.class);

	@Override
	public Class<TipoPedidoEntity> getEntityClass() {
		return TipoPedidoEntity.class;
	}

	public List<TipoPedidoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<TipoPedidoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TipoPedidoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<TipoPedidoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TipoPedidoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<TipoPedidoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Código", "Nome", "Descrição" };
	}

}