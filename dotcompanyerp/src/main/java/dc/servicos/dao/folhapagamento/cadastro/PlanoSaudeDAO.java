package dc.servicos.dao.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PlanoSaudeDAO extends AbstractCrudDAO<PlanoSaudeEntity> {

	@Override
	public Class<PlanoSaudeEntity> getEntityClass() {
		return PlanoSaudeEntity.class;
	}

	@Transactional
	public List<PlanoSaudeEntity> listarTodos() {
		try {
			String sql = "FROM PlanoSaudeEntity ent WHERE (1 = 1)";

			List<PlanoSaudeEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoSaudeEntity>();
		}
	}

	@Transactional
	public List<PlanoSaudeEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PlanoSaudeEntity ent WHERE (1 = 1) AND ent.beneficiario LIKE :q";

			List<PlanoSaudeEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoSaudeEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Beneficiário", "Data início" };
	}

}