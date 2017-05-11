package dc.servicos.dao.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository(value = "contabilidadePlanoContaDAO")
@SuppressWarnings("unchecked")
public class PlanoContaDAO extends AbstractCrudDAO<PlanoContaEntity> implements IPlanoContaDAO {

	@Override
	public Class<PlanoContaEntity> getEntityClass() {
		return PlanoContaEntity.class;
	}

	@Transactional
	public List<PlanoContaEntity> listarTodos() {
		try {
			String sql = "FROM PlanoContaEntity ent WHERE (1 = 1)";

			List<PlanoContaEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaEntity>();
		}
	}

	@Transactional
	public List<PlanoContaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PlanoContaEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<PlanoContaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Nome", "Data da inclusão" };
	}

}