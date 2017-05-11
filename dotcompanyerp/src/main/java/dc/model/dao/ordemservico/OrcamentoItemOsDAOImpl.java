package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.entidade.ordemservico.OrcamentoOsItemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class OrcamentoItemOsDAOImpl extends AbstractCrudDAO<OrcamentoOsItemEntity> implements
	OrcamentoItemOsDAO<OrcamentoOsItemEntity> {

	private static Logger logger = Logger.getLogger(OrcamentoItemOsDAOImpl.class);

	@Override
	public Class<OrcamentoOsItemEntity> getEntityClass() {
		return OrcamentoOsItemEntity.class;
	}

	public List<OrcamentoOsItemEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<OrcamentoOsItemEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OrcamentoOsItemEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<OrcamentoOsItemEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OrcamentoOsItemEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<OrcamentoOsItemEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	public List<OrcamentoOsItemEntity> findByOrcamentoOs(OrcamentoOsEntity orcamentoOs) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.orcamentoOs.id = :q";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", orcamentoOs.getId());

			List<OrcamentoOsItemEntity> auxLista = query.list();
		
			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	
}