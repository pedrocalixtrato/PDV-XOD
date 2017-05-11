package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IndiceEconomicoDAOImpl extends AbstractCrudDAO<IndiceEconomicoEntity> implements IndiceEconomicoDAO<IndiceEconomicoEntity> {

			private static Logger logger = Logger.getLogger(IndiceEconomicoDAOImpl.class);

			@Override
			public Class<IndiceEconomicoEntity> getEntityClass() {
				return IndiceEconomicoEntity.class;
			}

			public List<IndiceEconomicoEntity> listaTodos() {
				try {
					String sql = "FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
			    	List<IndiceEconomicoEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<IndiceEconomicoEntity> procuraNomeContendo(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("nome", value);

					List<IndiceEconomicoEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}

			public List<IndiceEconomicoEntity> query(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
					sql = sql.replace("#", getEntityClass().getName());

					value = "%" + value.toLowerCase() + "%";

					Query query = super.getSession().createQuery(sql);
					query.setParameter("q", value);
					List<IndiceEconomicoEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				
			}
			
			public String[] getDefaultSearchFields() {
				return new String[] { "nome" };
			}

}
