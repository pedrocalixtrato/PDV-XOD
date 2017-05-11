package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.PppExameMedicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PppExameMedicoDAO extends AbstractCrudDAO<PppExameMedicoEntity> {

	@Override
	public Class<PppExameMedicoEntity> getEntityClass() {
		return PppExameMedicoEntity.class;
	}

	@Transactional
	public List<PppExameMedicoEntity> listarTodos() {
		try {
			String sql = "FROM PppExameMedicoEntity ent WHERE (1 = 1)";

			List<PppExameMedicoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppExameMedicoEntity>();
		}
	}

	@Transactional
	public List<PppExameMedicoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PppExameMedicoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<PppExameMedicoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppExameMedicoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "dataUltimo", "tipo", "natureza", "ppp" };
	}

}