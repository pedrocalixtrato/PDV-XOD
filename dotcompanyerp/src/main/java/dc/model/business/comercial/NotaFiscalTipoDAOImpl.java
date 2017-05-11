package dc.model.business.comercial;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.model.dao.geral.pessoal.INotaFiscalTipoDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class NotaFiscalTipoDAOImpl extends AbstractCrudDAO<NotaFiscalTipo>  {
	
		private static Logger logger = Logger.getLogger(NotaFiscalTipoDAOImpl.class);

		@Override
		public Class<NotaFiscalTipo> getEntityClass() {
			return NotaFiscalTipo.class;
		}

		public List<NotaFiscalTipo> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);

				List<NotaFiscalTipo> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "serie", "nome", "descricao"};
		}

		@Override
		public NotaFiscalTipo find(Serializable id) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);
				query.setParameter("id", id);

				NotaFiscalTipo ent = (NotaFiscalTipo) query.uniqueResult();

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		
		public List<NotaFiscalTipo> list() {
			try {
				String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());
				sql = sql.replace("-", this.getEntityClass().getSimpleName()
						+ "(ent.id, ent.nome)");

				Query query = super.getSession().createQuery(sql);

				List<NotaFiscalTipo> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<NotaFiscalTipo> procuraNomeContendo(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);
				query.setParameter("nome", value);

				List<NotaFiscalTipo> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<NotaFiscalTipo> query(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
				sql = sql.replace("#", getEntityClass().getName());

				value = "%" + value.toLowerCase() + "%";

				Query query = super.getSession().createQuery(sql);
				query.setParameter("q", value);

				List<NotaFiscalTipo> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

}
