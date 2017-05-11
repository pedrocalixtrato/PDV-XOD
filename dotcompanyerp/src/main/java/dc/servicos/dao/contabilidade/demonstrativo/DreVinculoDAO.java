package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.DreVinculoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class DreVinculoDAO extends AbstractCrudDAO<DreVinculoEntity> implements IDreVinculoDAO {

	@Override
	public Class<DreVinculoEntity> getEntityClass() {
		return DreVinculoEntity.class;
	}

	@Transactional
	public List<DreVinculoEntity> listarTodos() {
		try {
			String sql = "FROM DreVinculoEntity ent WHERE (1 = 1)";

			List<DreVinculoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreVinculoEntity>();
		}
	}

	@Transactional
	public List<DreVinculoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DreVinculoEntity ent WHERE (1 = 1) AND ent.id LIKE :q";

			List<DreVinculoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreVinculoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "DRE detalhe", "Conta" };
	}

}