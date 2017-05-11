package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class LaudoTecnicoDAOImpl extends AbstractCrudDAO<LaudoTecnicoEntity> implements
LaudoTecnicoDAO<LaudoTecnicoEntity> {

	private static Logger logger = Logger.getLogger(LaudoTecnicoDAOImpl.class);

	@Override
	public Class<LaudoTecnicoEntity> getEntityClass() {
		return LaudoTecnicoEntity.class;
	}

	public List<LaudoTecnicoEntity> listaTodos() {
		try {
			String sql = "SELECT ent FROM LaudoTecnicoEntity INNER JOIN FETCH ent.ordemServico os WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<LaudoTecnicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<LaudoTecnicoEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}
	
	public LaudoTecnicoEntity findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "SELECT ent FROM LaudoTecnicoEntity ent INNER JOIN FETCH ent.ordemServico os WHERE (1 = 1) AND ent.ordemServico.id = :id";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			LaudoTecnicoEntity ent = (LaudoTecnicoEntity) query.uniqueResult();
			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}