package dc.servicos.dao.folhapagamento.ausencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.ausencia.TipoAfastamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class TipoAfastamentoDAO extends AbstractCrudDAO<TipoAfastamentoEntity> implements ITipoAfastamentoDAO {

	@Override
	public Class<TipoAfastamentoEntity> getEntityClass() {
		return TipoAfastamentoEntity.class;
	}

	@Transactional
	public List<TipoAfastamentoEntity> listarTodos() {
		try {
			String sql = "FROM TipoAfastamentoEntity ent WHERE (1 = 1)";

			List<TipoAfastamentoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoAfastamentoEntity>();
		}
	}

	@Transactional
	public List<TipoAfastamentoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM TipoAfastamentoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<TipoAfastamentoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoAfastamentoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Código", "Nome", "Descrição" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.folhapagamento.ausencia.ITipoAfastamentoDAO#tipoAfastamentoLista()
	 */
	@Override
	@Transactional
	public List<TipoAfastamentoEntity> tipoAfastamentoLista() {
		try {
			String sql = "SELECT new TipoAfastamentoEntity(ent.id, ent.nome) FROM TipoAfastamentoEntity ent"
					+ " WHERE (1 = 1)";

			List<TipoAfastamentoEntity> auxLista = getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoAfastamentoEntity>();
		}
	}

}