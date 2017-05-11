package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.ServicoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ServicoOsDAOImpl extends AbstractCrudDAO<ServicoOsEntity> implements IServicoOsDAO {
	
		private static Logger logger = Logger.getLogger(ServicoOsDAOImpl.class);

		@Override
		public Class<ServicoOsEntity> getEntityClass() {
			return ServicoOsEntity.class;
		}

		public List<ServicoOsEntity> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);

				List<ServicoOsEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<ServicoOsEntity> procuraNomeContendo(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.descricao LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				Query query = super.getSession().createQuery(sql);
				query.setParameter("descricao", value);

				List<ServicoOsEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<ServicoOsEntity> query(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND LOWER(descricao) LIKE :q";
				sql = sql.replace("#", getEntityClass().getName());

				value = "%" + value.toLowerCase() + "%";

				Query query = super.getSession().createQuery(sql);
				query.setParameter("q", value);

				List<ServicoOsEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "nome","grupo","subGrupo","aliquotaIssqn","valorComissaoTecnico","valorComissaoVendedor","valorServico","valorMinimoServico",
					"dataCadastro","observacao" };
		}

}
