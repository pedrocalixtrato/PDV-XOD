package dc.model.dao.suprimento.estoque;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class LoteProdutoDAOImpl extends AbstractCrudDAO<LoteProdutoEntity> implements ILoteProdutoDAO  {
	
	
		private static Logger logger = Logger.getLogger(LoteProdutoDAOImpl.class);

		@Override
		public Class<LoteProdutoEntity> getEntityClass() {
			return LoteProdutoEntity.class;
		}

		public List<LoteProdutoEntity> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);

				List<LoteProdutoEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<LoteProdutoEntity> procuraNomeContendo(String query) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				List<LoteProdutoEntity> auxLista = super.getSession()
						.createQuery(sql).setParameter("q", "%" + query + "%")
						.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<LoteProdutoEntity> query(String q) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				List<LoteProdutoEntity> auxLista = super.getSession()
						.createQuery(sql).setParameter("q", "%" + q + "%").list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "nome"};
		}

}
