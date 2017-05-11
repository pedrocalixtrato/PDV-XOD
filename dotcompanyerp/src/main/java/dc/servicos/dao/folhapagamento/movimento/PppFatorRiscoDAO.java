package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.PppFatorRiscoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PppFatorRiscoDAO extends AbstractCrudDAO<PppFatorRiscoEntity> {

	@Override
	public Class<PppFatorRiscoEntity> getEntityClass() {
		return PppFatorRiscoEntity.class;
	}

	@Transactional
	public List<PppFatorRiscoEntity> listarTodos() {
		try {
			String sql = "FROM PppFatorRiscoEntity ent WHERE (1 = 1)";

			List<PppFatorRiscoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppFatorRiscoEntity>();
		}
	}

	@Transactional
	public List<PppFatorRiscoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PppFatorRiscoEntity ent WHERE (1 = 1) AND ent.tipo LIKE :q";

			List<PppFatorRiscoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppFatorRiscoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Data do ínicio", "Data do término", "Tipo" };
	}

}