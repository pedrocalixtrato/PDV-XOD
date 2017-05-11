package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class StatusParcelaDAOImpl extends AbstractCrudDAO<StatusParcela> implements StatusParcelaIDAO<StatusParcela> {

		private static Logger logger = Logger
				.getLogger(StatusParcelaDAOImpl.class);

		@Override
		public Class<StatusParcela> getEntityClass() {
			return StatusParcela.class;
		}

		public List<StatusParcela> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);

				List<StatusParcela> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<StatusParcela> procuraNomeContendo(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);
				query.setParameter("nome", value);

				List<StatusParcela> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<StatusParcela> query(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
				sql = sql.replace("#", getEntityClass().getName());

				value = "%" + value.toLowerCase() + "%";

				Query query = super.getSession().createQuery(sql);
				query.setParameter("q", value);

				List<StatusParcela> auxLista = query.list();

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
