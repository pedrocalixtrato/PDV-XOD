package dc.servicos.dao.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LivroDAO extends AbstractCrudDAO<LivroEntity> implements ILivroDAO {

	@Override
	public Class<LivroEntity> getEntityClass() {
		return LivroEntity.class;
	}

	@Transactional
	public List<LivroEntity> listarTodos() {
		try {
			String sql = "FROM LivroEntity ent WHERE (1 = 1)";

			List<LivroEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LivroEntity>();
		}
	}

	@Transactional
	public List<LivroEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LivroEntity ent WHERE (1 = 1) AND ent.descricao LIKE :q";

			List<LivroEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LivroEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Descrição", "Competência",
				"Forma de escrituração" };
	}

}