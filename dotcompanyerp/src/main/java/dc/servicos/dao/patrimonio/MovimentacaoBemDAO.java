package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.MovimentacaoBemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class MovimentacaoBemDAO extends AbstractCrudDAO<MovimentacaoBemEntity> {

	@Override
	public Class<MovimentacaoBemEntity> getEntityClass() {
		return MovimentacaoBemEntity.class;
	}

	@Transactional
	public List<MovimentacaoBemEntity> listarTodos() {
		try {
			String sql = "FROM MovimentacaoBemEntity ent WHERE (1 = 1)";

			List<MovimentacaoBemEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<MovimentacaoBemEntity>();
		}
	}

	@Transactional
	public List<MovimentacaoBemEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM MovimentacaoBemEntity ent WHERE (1 = 1) AND ent.responsavel LIKE :q";

			List<MovimentacaoBemEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<MovimentacaoBemEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

	@Transactional
	public List<MovimentacaoBemEntity> movimentacaoBemLista() {
		try {
			String sql = "SELECT new MovimentacaoBemEntity(ent.id, ent.responsavel) FROM MovimentacaoBemEntity ent"
					+ " WHERE (1 = 1)";

			List<MovimentacaoBemEntity> auxLista = getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<MovimentacaoBemEntity>();
		}
	}

}