package dc.model.dao.geral.outro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.outro.SindicatoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SindicatoDAOImpl extends AbstractCrudDAO<SindicatoEntity> implements ISindicatoDAO {

		private static Logger logger = Logger.getLogger(SindicatoDAOImpl.class);

		@Override
		public Class<SindicatoEntity> getEntityClass() {
			return SindicatoEntity.class;
		}

		public List<SindicatoEntity> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);

				List<SindicatoEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "nome","codigoBanco","codigoAgencia","contaBanco","codigoCedente",
					"logradouro","numero","bairro","fone1","fone2","email","tipoSindicato","dataBase","cnpj" };
		}

		@Override
		public SindicatoEntity find(Serializable id) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);
				query.setParameter("id", id);

				SindicatoEntity ent = (SindicatoEntity) query.uniqueResult();

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		
		public List<SindicatoEntity> list() {
			try {
				String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());
				sql = sql.replace("-", this.getEntityClass().getSimpleName()
						+ "(ent.id, ent.nome)");

				Query query = super.getSession().createQuery(sql);

				List<SindicatoEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<SindicatoEntity> procuraNomeContendo(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);
				query.setParameter("nome", value);

				List<SindicatoEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<SindicatoEntity> query(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
				sql = sql.replace("#", getEntityClass().getName());

				value = "%" + value.toLowerCase() + "%";

				Query query = super.getSession().createQuery(sql);
				query.setParameter("q", value);

				List<SindicatoEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<SindicatoEntity> getSindicatoList() {
			try {
				List<SindicatoEntity> auxLista = new ArrayList<SindicatoEntity>();

				String sql = "SELECT new Sindicato(ent.id, ent.nome) FROM Sindicato ent";

				auxLista = getSession().createQuery(sql).list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

}
