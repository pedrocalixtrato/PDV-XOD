package dc.servicos.dao.suprimentos.estoque;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ContagemCabecalhoDAO extends
		AbstractCrudDAO<ContagemCabecalhoEntity> {

	@Override
	public Class<ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<ContagemCabecalhoEntity> listarTodos() {
		try {
			String sql = "FROM ContagemCabecalhoEntity ent WHERE (1 = 1)";

			List<ContagemCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContagemCabecalhoEntity>();
		}
	}

	@Transactional
	public List<ContagemCabecalhoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ContagemCabecalhoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<ContagemCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContagemCabecalhoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "estoque_atualizado" };
	}

}