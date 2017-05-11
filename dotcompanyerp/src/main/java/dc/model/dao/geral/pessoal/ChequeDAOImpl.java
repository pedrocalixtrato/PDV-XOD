package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.financeiro.Cheque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ChequeDAOImpl extends AbstractCrudDAO<Cheque> implements ChequeDAO<Cheque> {
	
		private static Logger logger = Logger.getLogger(ChequeDAOImpl.class);

		@Override
		public Class<Cheque> getEntityClass() {
			return Cheque.class;
		}

		public List<Cheque> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);

				List<Cheque> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<Cheque> procuraNomeContendo(String query) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				List<Cheque> auxLista = super.getSession()
						.createQuery(sql).setParameter("q", "%" + query + "%")
						.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<Cheque> query(String q) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				List<Cheque> auxLista = super.getSession()
						.createQuery(sql).setParameter("q", "%" + q + "%").list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "numero", "statusCheque" };
		}

}
