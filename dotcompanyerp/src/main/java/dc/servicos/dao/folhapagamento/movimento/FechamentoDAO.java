package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.FechamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class FechamentoDAO extends AbstractCrudDAO<FechamentoEntity> {

	@Override
	public Class<FechamentoEntity> getEntityClass() {
		return FechamentoEntity.class;
	}

	@Transactional
	public List<FechamentoEntity> listarTodos() {
		try {
			String sql = "FROM FechamentoEntity ent WHERE (1 = 1)";

			List<FechamentoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FechamentoEntity>();
		}
	}

	@Transactional
	public List<FechamentoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM FechamentoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<FechamentoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FechamentoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Fechamento atual", "Próximo fechamento" };
	}

}