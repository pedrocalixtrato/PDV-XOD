package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.DreDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class DreDetalheDAO extends AbstractCrudDAO<DreDetalheEntity> implements IDreDetalheDAO {

	@Override
	public Class<DreDetalheEntity> getEntityClass() {
		return DreDetalheEntity.class;
	}

	@Transactional
	public List<DreDetalheEntity> listarTodos() {
		try {
			String sql = "FROM DreDetalheEntity ent WHERE (1 = 1)";

			List<DreDetalheEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreDetalheEntity>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.contabilidade.demonstrativo.IDreDetalheDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<DreDetalheEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DreDetalheEntity ent WHERE (1 = 1) AND ent.classificacao LIKE :q";

			List<DreDetalheEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreDetalheEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Classificação", "Descrição", "Forma do cálculo",
				"Sinal" };
	}

}