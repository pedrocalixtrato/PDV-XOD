package dc.servicos.dao.geral.diverso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Wesley Jr
 */

@Repository
@SuppressWarnings("unchecked")
public class OperadoraPlanoSaudeDAO extends	AbstractCrudDAO<OperadoraPlanoSaudeEntity> {

	@Override
	public Class<OperadoraPlanoSaudeEntity> getEntityClass() {
		return OperadoraPlanoSaudeEntity.class;
	}

	@Transactional
	public List<OperadoraPlanoSaudeEntity> listaTodos() {
		return getSession().createQuery("from OperadoraPlanoSaude").list();
	}

	@Transactional
	public List<OperadoraPlanoSaudeEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from OperadoraPlanoSaude where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "registroAns", "nome" };
	}

	/**
	 * ********************************************************
	 */

	/**
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@Transactional
	public List<OperadoraPlanoSaudeEntity> operadoraPlanoSaudeLista() {
		try {
			String sql = "SELECT new OperadoraPlanoSaude(ent.id, ent.nome) FROM OperadoraPlanoSaude ent"
					+ " WHERE (1 = 1)";

			List<OperadoraPlanoSaudeEntity> auxLista = getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<OperadoraPlanoSaudeEntity>();
		}
	}

}