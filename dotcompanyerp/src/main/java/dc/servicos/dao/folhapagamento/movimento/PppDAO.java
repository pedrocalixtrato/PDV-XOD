package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PppDAO extends AbstractCrudDAO<PppEntity> implements IPppDAO {

	@Override
	public Class<PppEntity> getEntityClass() {
		return PppEntity.class;
	}

	@Transactional
	public List<PppEntity> listarTodos() {
		try {
			String sql = "FROM PppEntity ent WHERE (1 = 1)";

			List<PppEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppEntity>();
		}
	}

	@Transactional
	public List<PppEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PppEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<PppEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "observacao", "colaborador" };
	}

}