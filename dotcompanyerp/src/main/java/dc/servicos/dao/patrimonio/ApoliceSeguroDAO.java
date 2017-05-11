package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.ApoliceSeguroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ApoliceSeguroDAO extends AbstractCrudDAO<ApoliceSeguroEntity> implements IApoliceSeguroDAO {

	@Override
	public Class<ApoliceSeguroEntity> getEntityClass() {
		return ApoliceSeguroEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<ApoliceSeguroEntity> listarTodos() {
		try {
			String sql = "FROM ApoliceSeguroEntity ent WHERE (1 = 1)";

			List<ApoliceSeguroEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ApoliceSeguroEntity>();
		}
	}

	@Transactional
	public List<ApoliceSeguroEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ApoliceSeguroEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<ApoliceSeguroEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ApoliceSeguroEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "numero", "bem", "seguradora" };
	}

}