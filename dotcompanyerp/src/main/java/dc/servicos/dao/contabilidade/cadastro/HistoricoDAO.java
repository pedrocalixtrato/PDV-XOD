package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class HistoricoDAO extends AbstractCrudDAO<HistoricoEntity> implements IHistoricoDAO {

	@Override
	public Class<HistoricoEntity> getEntityClass() {
		return HistoricoEntity.class;
	}

	@Transactional
	public List<HistoricoEntity> listarTodos() {
		try {
			String sql = "FROM HistoricoEntity ent WHERE (1 = 1)";

			List<HistoricoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<HistoricoEntity>();
		}
	}

	@Transactional
	public List<HistoricoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM HistoricoEntity ent WHERE (1 = 1) AND ent.descricao LIKE :q";

			List<HistoricoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<HistoricoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Descrição", "Histórico", "Pede complemento" };
	}

}