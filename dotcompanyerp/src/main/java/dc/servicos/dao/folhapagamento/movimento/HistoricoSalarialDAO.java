package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.HistoricoSalarialEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class HistoricoSalarialDAO extends
		AbstractCrudDAO<HistoricoSalarialEntity> {

	@Override
	public Class<HistoricoSalarialEntity> getEntityClass() {
		return HistoricoSalarialEntity.class;
	}

	@Transactional
	public List<HistoricoSalarialEntity> listarTodos() {
		try {
			String sql = "FROM HistoricoSalarialEntity ent WHERE (1 = 1)";

			List<HistoricoSalarialEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<HistoricoSalarialEntity>();
		}
	}

	@Transactional
	public List<HistoricoSalarialEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM HistoricoSalarialEntity ent WHERE (1 = 1) AND ent.colaborador.matricula LIKE :q";

			List<HistoricoSalarialEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<HistoricoSalarialEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "competencia", "salarioAtual",
				"percentualAumento", "salarioNovo", "colaborador" };
	}

}