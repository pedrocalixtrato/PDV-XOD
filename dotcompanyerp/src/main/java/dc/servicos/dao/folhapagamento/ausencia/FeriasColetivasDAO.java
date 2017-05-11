package dc.servicos.dao.folhapagamento.ausencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.ausencia.FeriasColetivasEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class FeriasColetivasDAO extends AbstractCrudDAO<FeriasColetivasEntity> implements IFeriasColetivasDAO {

	@Override
	public Class<FeriasColetivasEntity> getEntityClass() {
		return FeriasColetivasEntity.class;
	}

	@Transactional
	public List<FeriasColetivasEntity> listarTodos() {
		try {
			String sql = "FROM FeriasColetivasEntity ent WHERE (1 = 1)";

			List<FeriasColetivasEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FeriasColetivasEntity>();
		}
	}

	@Transactional
	public List<FeriasColetivasEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM FeriasColetivasEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<FeriasColetivasEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FeriasColetivasEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Data início", "Data término",
				"Data do pagamento" };
	}

}