package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.TipoMovimentacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoMovimentacaoDAO extends
		AbstractCrudDAO<TipoMovimentacaoEntity> {

	@Override
	public Class<TipoMovimentacaoEntity> getEntityClass() {
		return TipoMovimentacaoEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<TipoMovimentacaoEntity> listarTodos() {
		try {
			String sql = "FROM TipoMovimentacaoEntity ent WHERE (1 = 1)";

			List<TipoMovimentacaoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoMovimentacaoEntity>();
		}
	}

	@Transactional
	public List<TipoMovimentacaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM TipoMovimentacaoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<TipoMovimentacaoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoMovimentacaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

	@Transactional
	public List<TipoMovimentacaoEntity> tipoMovimnentacaoLista() {
		try {
			String sql = "SELECT new TipoMovimentacaoEntity(ent.id, ent.nome) FROM TipoMovimentacaoEntity ent"
					+ " WHERE (1 = 1)";

			List<TipoMovimentacaoEntity> auxLista = getSession().createQuery(
					sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoMovimentacaoEntity>();
		}
	}

}