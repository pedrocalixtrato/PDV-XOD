package dc.model.dao.tributario;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IcmsCustomizadoDAOImpl extends AbstractCrudDAO<IcmsCustomizadoCabecalhoEntity> implements IIcmsCustomizadoDAO {

	private static Logger logger = Logger
			.getLogger(IcmsCustomizadoDAOImpl.class);

	@Override
	public Class<IcmsCustomizadoCabecalhoEntity> getEntityClass() {
		return IcmsCustomizadoCabecalhoEntity.class;
	}

	public List<IcmsCustomizadoCabecalhoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<IcmsCustomizadoCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<IcmsCustomizadoCabecalhoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<IcmsCustomizadoCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<IcmsCustomizadoCabecalhoEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<IcmsCustomizadoCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao","origemMercadoria" };
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> list() throws Exception {
		try {
			String sql = "SELECT new - FROM IcmsCustomizadoCabecalhoEntity WHERE (1 = 1)";
			sql = sql.replace("IcmsCustomizadoCabecalhoEntity", this.getEntityClass().getName());
			sql = sql.replace("-", this.getEntityClass().getSimpleName()+ "(id, descricao)");

			Query query = super.getSession().createQuery(sql);

			List<IcmsCustomizadoCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}