package dc.model.dao.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ClienteDAOImpl extends AbstractCrudDAO<ClienteEntity>  {
	
			private static Logger logger = Logger.getLogger(ClienteDAOImpl.class);

			@Override
			public Class<ClienteEntity> getEntityClass() {
				return ClienteEntity.class;
			}

			public List<ClienteEntity> listaTodos() {
				try {
					String sql = "FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);

					List<ClienteEntity> auxLista = query.list();

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
			public ClienteEntity find(Serializable id) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("id", id);

					ClienteEntity ent = (ClienteEntity) query.uniqueResult();

					return ent;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			
			public List<ClienteEntity> list() {
				try {
					String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());
					sql = sql.replace("-", this.getEntityClass().getSimpleName()
							+ "(ent.id, ent.nome)");

					Query query = super.getSession().createQuery(sql);

					List<ClienteEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<ClienteEntity> procuraNomeContendo(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("nome", value);

					List<ClienteEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<ClienteEntity> query(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
					sql = sql.replace("#", getEntityClass().getName());

					value = "%" + value.toLowerCase() + "%";

					Query query = super.getSession().createQuery(sql);
					query.setParameter("q", value);

					List<ClienteEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

}
