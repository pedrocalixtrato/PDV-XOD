package dc.servicos.dao.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class TermoDAO extends AbstractCrudDAO<TermoEntity> implements ITermoDAO {

	@Override
	public Class<TermoEntity> getEntityClass() {
		return TermoEntity.class;
	}

	@Transactional
	public List<TermoEntity> listarTodos() {
		try {
			String sql = "FROM TermoEntity ent WHERE (1 = 1)";

			List<TermoEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TermoEntity>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.contabilidade.livrocontabil.ITermoDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<TermoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM TermoEntity ent WHERE (1 = 1) AND ent.numero LIKE :q";

			List<TermoEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TermoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Abertura encerramento", "Número",
				"Número do registro" };
	}

}