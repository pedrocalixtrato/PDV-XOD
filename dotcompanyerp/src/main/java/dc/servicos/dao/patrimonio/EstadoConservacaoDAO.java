package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EstadoConservacaoDAO extends
		AbstractCrudDAO<EstadoConservacaoEntity> {

	@Override
	public Class<EstadoConservacaoEntity> getEntityClass() {
		return EstadoConservacaoEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<EstadoConservacaoEntity> listarTodos() {
		try {
			String sql = "FROM EstadoConservacaoEntity ent WHERE (1 = 1)";

			List<EstadoConservacaoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EstadoConservacaoEntity>();
		}
	}

	@Transactional
	public List<EstadoConservacaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM EstadoConservacaoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<EstadoConservacaoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EstadoConservacaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Código", "Nome", "Descrição" };
	}

}