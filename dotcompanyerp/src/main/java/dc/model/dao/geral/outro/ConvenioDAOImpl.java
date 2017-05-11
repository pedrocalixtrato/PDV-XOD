package dc.model.dao.geral.outro;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.outro.ConvenioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ConvenioDAOImpl extends AbstractCrudDAO<ConvenioEntity> implements IConvenioDAO {

			private static Logger logger = Logger.getLogger(ConvenioDAOImpl.class);

			@Override
			public Class<ConvenioEntity> getEntityClass() {
				return ConvenioEntity.class;
			}

			public List<ConvenioEntity> listaTodos() {
				try {
					String sql = "FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);

					List<ConvenioEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public String[] getDefaultSearchFields() {
				return new String[] { "pessoa","nome", "logradouro","numero", "bairro","dataVencimento","dataCadastro","email","telefone","contato",
						"cnpj","cep","site","descricao" };
			}

			@Override
			public ConvenioEntity find(Serializable id) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("id", id);

					ConvenioEntity ent = (ConvenioEntity) query.uniqueResult();

					return ent;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			
			public List<ConvenioEntity> list() {
				try {
					String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());
					sql = sql.replace("-", this.getEntityClass().getSimpleName()
							+ "(ent.id, ent.nome)");

					Query query = super.getSession().createQuery(sql);

					List<ConvenioEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<ConvenioEntity> procuraNomeContendo(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("nome", value);

					List<ConvenioEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<ConvenioEntity> query(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
					sql = sql.replace("#", getEntityClass().getName());

					value = "%" + value.toLowerCase() + "%";

					Query query = super.getSession().createQuery(sql);
					query.setParameter("q", value);

					List<ConvenioEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

}
