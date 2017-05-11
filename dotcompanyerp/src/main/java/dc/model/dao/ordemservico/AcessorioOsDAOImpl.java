package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.AcessorioOsEntity;
import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.entidade.ordemservico.OrcamentoOsItemEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class AcessorioOsDAOImpl extends AbstractCrudDAO<AcessorioOsEntity> implements
		AcessorioOsDAO<AcessorioOsEntity> {

	private static Logger logger = Logger.getLogger(AcessorioOsDAOImpl.class);

	@Override
	public Class<AcessorioOsEntity> getEntityClass() {
		return AcessorioOsEntity.class;
	}

	public List<AcessorioOsEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<AcessorioOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<AcessorioOsEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<AcessorioOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<AcessorioOsEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<AcessorioOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	@Override
	public List<AcessorioOsEntity> findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			List<AcessorioOsEntity> auxLista = query.list();
		
			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
}