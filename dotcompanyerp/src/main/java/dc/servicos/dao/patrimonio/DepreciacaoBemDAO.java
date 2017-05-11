package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.DepreciacaoBemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class DepreciacaoBemDAO extends AbstractCrudDAO<DepreciacaoBemEntity> {

	@Override
	public Class<DepreciacaoBemEntity> getEntityClass() {
		return DepreciacaoBemEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<DepreciacaoBemEntity> listarTodos() {
		try {
			String sql = "FROM DepreciacaoBemEntity ent WHERE (1 = 1)";

			List<DepreciacaoBemEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DepreciacaoBemEntity>();
		}
	}

	@Transactional
	public List<DepreciacaoBemEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DepreciacaoBemEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<DepreciacaoBemEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DepreciacaoBemEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Número", "Bem", "Seguradora" };
	}

}