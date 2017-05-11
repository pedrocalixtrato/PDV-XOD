package dc.servicos.dao.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.cadastro.EventoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class EventoDAO extends AbstractCrudDAO<EventoEntity> {

	@Override
	public Class<EventoEntity> getEntityClass() {
		return EventoEntity.class;
	}

	@Transactional
	public List<EventoEntity> listarTodos() {
		try {
			String sql = "FROM EventoEntity ent WHERE (1 = 1)";

			List<EventoEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EventoEntity>();
		}
	}

	@Transactional
	public List<EventoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM EventoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<EventoEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EventoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Nome", "Código", "Tipo", "Unidade", "Taxa" };
	}

}