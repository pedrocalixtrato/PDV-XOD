package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ordemservico.ObservacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.entidade.ordemservico.ParametroOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class ParametroOsDAOImpl extends AbstractCrudDAO<ParametroOsEntity> implements
ParametroOsDAO<ParametroOsEntity> {

	private static Logger logger = Logger.getLogger(ParametroOsDAOImpl.class);

	@Override
	public Class<ParametroOsEntity> getEntityClass() {
		return ParametroOsEntity.class;
	}

	public List<ParametroOsEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<ParametroOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ParametroOsEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<ParametroOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ParametroOsEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<ParametroOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ParametroOsEntity buscaParametroOs(EmpresaEntity empresa) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.empresa.id = :id";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", empresa.getId());

			ParametroOsEntity ent = (ParametroOsEntity) query.uniqueResult();
			
			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}
}