package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoProgramadoCabDAO extends
		AbstractCrudDAO<LancamentoProgramadoCabEntity> implements ILancamentoProgramadoCabDAO {

	@Override
	public Class<LancamentoProgramadoCabEntity> getEntityClass() {
		return LancamentoProgramadoCabEntity.class;
	}

	@Transactional
	public List<LancamentoProgramadoCabEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoProgramadoCabEntity ent WHERE (1 = 1)";

			List<LancamentoProgramadoCabEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoProgramadoCabEntity>();
		}
	}

	@Transactional
	public List<LancamentoProgramadoCabEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoProgramadoCabEntity ent WHERE (1 = 1) AND ent.tipo LIKE :q";

			List<LancamentoProgramadoCabEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoProgramadoCabEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Data de inclusão", "Tipo", "Liberado" };
	}

}