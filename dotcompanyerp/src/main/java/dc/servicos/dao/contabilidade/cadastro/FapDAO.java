package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.FapEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class FapDAO extends AbstractCrudDAO<FapEntity> implements IFapDAO {

	@Override
	public Class<FapEntity> getEntityClass() {
		return FapEntity.class;
	}

	@Transactional
	public List<FapEntity> listarTodos() {
		try {
			String sql = "FROM FapEntity ent WHERE (1 = 1)";

			List<FapEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FapEntity>();
		}
	}

	@Transactional
	public List<FapEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM FapEntity ent WHERE (1 = 1) AND ent.fap LIKE :q";

			List<FapEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FapEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Fap", "Data inicial", "Data término" };
	}

}