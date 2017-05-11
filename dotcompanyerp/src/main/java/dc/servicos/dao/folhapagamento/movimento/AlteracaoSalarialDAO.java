package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.AlteracaoSalarialEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class AlteracaoSalarialDAO extends
		AbstractCrudDAO<AlteracaoSalarialEntity> {

	@Override
	public Class<AlteracaoSalarialEntity> getEntityClass() {
		return AlteracaoSalarialEntity.class;
	}

	@Transactional
	public List<AlteracaoSalarialEntity> listarTodos() {
		try {
			String sql = "FROM AlteracaoSalarialEntity ent WHERE (1 = 1)";

			List<AlteracaoSalarialEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<AlteracaoSalarialEntity>();
		}
	}

	@Transactional
	public List<AlteracaoSalarialEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM AlteracaoSalarialEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<AlteracaoSalarialEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<AlteracaoSalarialEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Número", "Bem", "Seguradora" };
	}

}