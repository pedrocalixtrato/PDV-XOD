package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.EncerramentoExeCabEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class EncerramentoExeCabDAO extends
		AbstractCrudDAO<EncerramentoExeCabEntity> implements IEncerramentoExeCabDAO {

	@Override
	public Class<EncerramentoExeCabEntity> getEntityClass() {
		return EncerramentoExeCabEntity.class;
	}

	@Transactional
	public List<EncerramentoExeCabEntity> listarTodos() {
		try {
			String sql = "FROM EncerramentoExeCabEntity ent WHERE (1 = 1)";

			List<EncerramentoExeCabEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EncerramentoExeCabEntity>();
		}
	}

	@Transactional
	public List<EncerramentoExeCabEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM EncerramentoExeCabEntity ent WHERE (1 = 1) AND ent.motivo LIKE :q";

			List<EncerramentoExeCabEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EncerramentoExeCabEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Data do início", "Data do término", "Motivo" };
	}

}