package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoProgramadoDetEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoProgramadoDetDAO extends
		AbstractCrudDAO<LancamentoProgramadoDetEntity> implements ILancamentoProgramadoDetDAO {

	@Override
	public Class<LancamentoProgramadoDetEntity> getEntityClass() {
		return LancamentoProgramadoDetEntity.class;
	}

	@Transactional
	public List<LancamentoProgramadoDetEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoProgramadoDetEntity ent WHERE (1 = 1)";

			List<LancamentoProgramadoDetEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoProgramadoDetEntity>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.contabilidade.lancamento.ILancamentoProgramadoDetDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<LancamentoProgramadoDetEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoProgramadoDetEntity ent WHERE (1 = 1) AND ent.descricaoHistorico LIKE :q";

			List<LancamentoProgramadoDetEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoProgramadoDetEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Descrição do histórico", "Tipo" };
	}

}