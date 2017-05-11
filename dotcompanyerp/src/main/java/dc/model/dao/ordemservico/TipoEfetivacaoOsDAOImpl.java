package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.TipoEfetivacaoOsEntity;
import dc.entidade.ordemservico.VendaPecaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class TipoEfetivacaoOsDAOImpl extends AbstractCrudDAO<TipoEfetivacaoOsEntity> implements
		TipoEfetivacaoOsDAO<TipoEfetivacaoOsEntity> {

	private static Logger logger = Logger.getLogger(TipoServicoOsDAOImpl.class);

	@Override
	public Class<TipoEfetivacaoOsEntity> getEntityClass() {
		return TipoEfetivacaoOsEntity.class;
	}

	public List<TipoEfetivacaoOsEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<TipoEfetivacaoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TipoEfetivacaoOsEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.descricao LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("descricao", value);

			List<TipoEfetivacaoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TipoEfetivacaoOsEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(descricao) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<TipoEfetivacaoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public TipoEfetivacaoOsEntity findByCodigo(Integer codigo){
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.codigo = :codigo";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("codigo", codigo);

			TipoEfetivacaoOsEntity ent =(TipoEfetivacaoOsEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

}