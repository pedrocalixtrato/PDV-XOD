package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.MaterialServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class MaterialServicoDAOImpl extends AbstractCrudDAO<MaterialServicoEntity> implements
MaterialServicoDAO<MaterialServicoEntity> {

	private static Logger logger = Logger.getLogger(MaterialServicoDAOImpl.class);

	@Override
	public Class<MaterialServicoEntity> getEntityClass() {
		return MaterialServicoEntity.class;
	}

	public List<MaterialServicoEntity> listaTodos() {
		try {
			String sql = "SELECT ent FROM MaterialServicoEntity ent INNER JOIN FETCH ent.ordemServico os LEFT JOIN FETCH ent.tecnico te "+
					"INNER JOIN FETCH ent.produto pr where (1=1) ";

			Query query = super.getSession().createQuery(sql);

			List<MaterialServicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<MaterialServicoEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	public List<MaterialServicoEntity> findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "SELECT ent FROM MaterialServicoEntity ent INNER JOIN FETCH ent.ordemServico os LEFT JOIN FETCH ent.tecnico te "+
					"INNER JOIN FETCH ent.produto pr where (1=1) AND ent.ordemServico.id = :id ";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			List<MaterialServicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}