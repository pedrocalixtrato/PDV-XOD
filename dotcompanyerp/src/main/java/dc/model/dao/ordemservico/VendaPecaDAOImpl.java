package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.VendaPecaEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class VendaPecaDAOImpl extends AbstractCrudDAO<VendaPecaEntity> implements
VendaPecaDAO<VendaPecaEntity> {

	private static Logger logger = Logger.getLogger(VendaPecaDAOImpl.class);

	@Override
	public Class<VendaPecaEntity> getEntityClass() {
		return VendaPecaEntity.class;
	}

	public List<VendaPecaEntity> listaTodos() {
		try {
			String sql = "SELECT ent FROM VendaPecaEntity ent INNER JOIN FETCH ent.ordemServico os LEFT JOIN FETCH ent.vendedor ve "+
			"LEFT JOIN FETCH ent.tecnico te INNER JOIN FETCH ent.produto pr "+ 
			"WHERE (1 = 1) ";

			Query query = super.getSession().createQuery(sql);

			List<VendaPecaEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<VendaPecaEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}
	
	public List<VendaPecaEntity> findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "SELECT ent FROM VendaPecaEntity ent INNER JOIN FETCH ent.ordemServico os LEFT JOIN FETCH ent.vendedor ve "+
			"LEFT JOIN FETCH ent.tecnico te INNER JOIN FETCH ent.produto pr "+ 
			"WHERE (1 = 1) AND ent.ordemServico.id = :id";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			List<VendaPecaEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}