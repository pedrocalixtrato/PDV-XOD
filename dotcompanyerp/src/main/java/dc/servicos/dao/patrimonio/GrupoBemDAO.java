package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.GrupoBemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class GrupoBemDAO extends AbstractCrudDAO<GrupoBemEntity> {

	@Override
	public Class<GrupoBemEntity> getEntityClass() {
		return GrupoBemEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<GrupoBemEntity> listarTodos() {
		try {
			String sql = "FROM GrupoBemEntity ent WHERE (1 = 1)";

			List<GrupoBemEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<GrupoBemEntity>();
		}
	}

	@Transactional
	public List<GrupoBemEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM GrupoBemEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<GrupoBemEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<GrupoBemEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Código", "Nome" };
	}

}