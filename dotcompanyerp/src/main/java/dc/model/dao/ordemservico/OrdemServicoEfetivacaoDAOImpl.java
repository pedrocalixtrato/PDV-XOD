package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.OrdemServicoEfetivacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class OrdemServicoEfetivacaoDAOImpl extends AbstractCrudDAO<OrdemServicoEfetivacaoEntity> implements
OrdemServicoEfetivacaoDAO<OrdemServicoEfetivacaoEntity> {

	private static Logger logger = Logger.getLogger(OrdemServicoEfetivacaoDAOImpl.class);

	@Override
	public Class<OrdemServicoEfetivacaoEntity> getEntityClass() {
		return OrdemServicoEfetivacaoEntity.class;
	}

	public List<OrdemServicoEfetivacaoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<OrdemServicoEfetivacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<OrdemServicoEfetivacaoEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	public OrdemServicoEfetivacaoEntity findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			OrdemServicoEfetivacaoEntity ent = (OrdemServicoEfetivacaoEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}