package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.TaxaDepreciacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TaxaDepreciacaoDAO extends AbstractCrudDAO<TaxaDepreciacaoEntity> {

	@Override
	public Class<TaxaDepreciacaoEntity> getEntityClass() {
		return TaxaDepreciacaoEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<TaxaDepreciacaoEntity> listarTodos() {
		try {
			String sql = "FROM TaxaDepreciacaoEntity ent WHERE (1 = 1)";

			List<TaxaDepreciacaoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TaxaDepreciacaoEntity>();
		}
	}

	@Transactional
	public List<TaxaDepreciacaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM TaxaDepreciacaoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<TaxaDepreciacaoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TaxaDepreciacaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

}