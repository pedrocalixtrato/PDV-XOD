package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.PppAtividadeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PppAtividadeDAO extends AbstractCrudDAO<PppAtividadeEntity> {

	@Override
	public Class<PppAtividadeEntity> getEntityClass() {
		return PppAtividadeEntity.class;
	}

	@Transactional
	public List<PppAtividadeEntity> listarTodos() {
		try {
			String sql = "FROM PppAtividadeEntity ent WHERE (1 = 1)";

			List<PppAtividadeEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppAtividadeEntity>();
		}
	}

	@Transactional
	public List<PppAtividadeEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PppAtividadeEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<PppAtividadeEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppAtividadeEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "dataInicio", "dataFim", "ppp" };
	}

}