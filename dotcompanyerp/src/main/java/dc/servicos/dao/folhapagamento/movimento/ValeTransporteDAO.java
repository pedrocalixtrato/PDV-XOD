package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.ValeTransporteEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class ValeTransporteDAO extends AbstractCrudDAO<ValeTransporteEntity> {

	@Override
	public Class<ValeTransporteEntity> getEntityClass() {
		return ValeTransporteEntity.class;
	}

	@Transactional
	public List<ValeTransporteEntity> listarTodos() {
		try {
			String sql = "FROM ValeTransporteEntity ent WHERE (1 = 1)";

			List<ValeTransporteEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ValeTransporteEntity>();
		}
	}

	@Transactional
	public List<ValeTransporteEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ValeTransporteEntity ent WHERE (1 = 1) AND ent.colaborador.matricula LIKE :q";

			List<ValeTransporteEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ValeTransporteEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Quantidade", "Colaborador" };
	}

}