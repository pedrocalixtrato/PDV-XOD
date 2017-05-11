package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class FluxoCaixaDAOImpl extends AbstractCrudDAO<FluxoCaixaEntity> implements IFluxoCaixaDAO {
	
			private static Logger logger = Logger.getLogger(FluxoCaixaDAOImpl.class);

			@Override
			public Class<FluxoCaixaEntity> getEntityClass() {
				return FluxoCaixaEntity.class;
			}

			public List<FluxoCaixaEntity> listaTodos() {
				try {
					String sql = "FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
			    	List<FluxoCaixaEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<FluxoCaixaEntity> procuraNomeContendo(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("nome", value);

					List<FluxoCaixaEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}

			public List<FluxoCaixaEntity> query(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
					sql = sql.replace("#", getEntityClass().getName());

					value = "%" + value.toLowerCase() + "%";

					Query query = super.getSession().createQuery(sql);
					query.setParameter("q", value);
					List<FluxoCaixaEntity> auxLista = query.list();

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
