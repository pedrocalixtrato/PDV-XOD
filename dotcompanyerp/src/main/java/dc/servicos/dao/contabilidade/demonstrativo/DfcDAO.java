package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.DfcEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class DfcDAO extends AbstractCrudDAO<DfcEntity> implements IDfcDAO {

	@Override
	public Class<DfcEntity> getEntityClass() {
		return DfcEntity.class;
	}

	@Transactional
	public List<DfcEntity> listarTodos() {
		try {
			String sql = "FROM DfcEntity ent WHERE (1 = 1)";

			List<DfcEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DfcEntity>();
		}
	}

	@Transactional
	public List<DfcEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DfcEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<DfcEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DfcEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}