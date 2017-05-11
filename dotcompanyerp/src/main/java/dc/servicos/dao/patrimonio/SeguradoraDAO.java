package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.SeguradoraEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class SeguradoraDAO extends AbstractCrudDAO<SeguradoraEntity> implements ISeguradoraDAO {

	@Override
	public Class<SeguradoraEntity> getEntityClass() {
		return SeguradoraEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<SeguradoraEntity> listarTodos() {
		try {
			String sql = "FROM SeguradoraEntity ent WHERE (1 = 1)";

			List<SeguradoraEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<SeguradoraEntity>();
		}
	}

	@Transactional
	public List<SeguradoraEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM SeguradoraEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<SeguradoraEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<SeguradoraEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.patrimonio.ISeguradoraDAO#seguradoraLista()
	 */
	@Override
	@Transactional
	public List<SeguradoraEntity> seguradoraLista() {
		try {
			String sql = "SELECT new SeguradoraEntity(ent.id, ent.nome) FROM SeguradoraEntity ent"
					+ " WHERE (1 = 1)";

			List<SeguradoraEntity> auxLista = getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<SeguradoraEntity>();
		}
	}

}