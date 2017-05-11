package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.DocumentoBemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class DocumentoBemDAO extends AbstractCrudDAO<DocumentoBemEntity> {

	@Override
	public Class<DocumentoBemEntity> getEntityClass() {
		return DocumentoBemEntity.class;
	}

	@Transactional
	public List<DocumentoBemEntity> listarTodos() {
		try {
			String sql = "FROM DocumentoBemEntity ent WHERE (1 = 1)";

			List<DocumentoBemEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DocumentoBemEntity>();
		}
	}

	@Transactional
	public List<DocumentoBemEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DocumentoBemEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<DocumentoBemEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DocumentoBemEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

	@Transactional
	public List<DocumentoBemEntity> movimentacaoBemLista() {
		try {
			String sql = "SELECT new DocumentoBemEntity(ent.id, ent.responsavel) FROM DocumentoBemEntity ent"
					+ " WHERE (1 = 1)";

			List<DocumentoBemEntity> auxLista = getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DocumentoBemEntity>();
		}
	}

}