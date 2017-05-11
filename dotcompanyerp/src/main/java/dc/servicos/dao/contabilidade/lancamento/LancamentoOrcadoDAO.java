package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoOrcadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoOrcadoDAO extends
		AbstractCrudDAO<LancamentoOrcadoEntity> implements ILancamentoOrcadoDAO {

	@Override
	public Class<LancamentoOrcadoEntity> getEntityClass() {
		return LancamentoOrcadoEntity.class;
	}

	@Transactional
	public List<LancamentoOrcadoEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoOrcadoEntity ent WHERE (1 = 1)";

			List<LancamentoOrcadoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoOrcadoEntity>();
		}
	}

	@Transactional
	public List<LancamentoOrcadoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoOrcadoEntity ent WHERE (1 = 1) AND ent.ano LIKE :q";

			List<LancamentoOrcadoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoOrcadoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Ano", "Janeiro", "Fevereiro", "Março" };
	}

}