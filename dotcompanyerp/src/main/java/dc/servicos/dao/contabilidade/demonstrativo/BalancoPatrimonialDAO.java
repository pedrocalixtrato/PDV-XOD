package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.BalancoPatrimonialEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class BalancoPatrimonialDAO extends
		AbstractCrudDAO<BalancoPatrimonialEntity> implements IBalancoPatrimonialDAO {

	@Override
	public Class<BalancoPatrimonialEntity> getEntityClass() {
		return BalancoPatrimonialEntity.class;
	}

	@Transactional
	public List<BalancoPatrimonialEntity> listarTodos() {
		try {
			String sql = "FROM BalancoPatrimonialEntity ent WHERE (1 = 1)";

			List<BalancoPatrimonialEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<BalancoPatrimonialEntity>();
		}
	}

	@Transactional
	public List<BalancoPatrimonialEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM BalancoPatrimonialEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<BalancoPatrimonialEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<BalancoPatrimonialEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}