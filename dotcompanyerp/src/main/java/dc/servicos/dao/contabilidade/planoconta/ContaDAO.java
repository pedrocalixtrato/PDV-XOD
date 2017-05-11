package dc.servicos.dao.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class ContaDAO extends AbstractCrudDAO<ContaEntity> implements IContaDAO {

	@Override
	public Class<ContaEntity> getEntityClass() {
		return ContaEntity.class;
	}

	@Transactional
	public List<ContaEntity> listarTodos() {
		try {
			String sql = "FROM ContaEntity ent WHERE (1 = 1)";

			List<ContaEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContaEntity>();
		}
	}

	@Transactional
	public List<ContaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ContaEntity ent WHERE (1 = 1) AND ent.descricao LIKE :q";

			List<ContaEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContaEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "classificação", "tipo", "descrição" };
	}

}