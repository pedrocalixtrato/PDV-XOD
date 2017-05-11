package dc.servicos.dao.folhapagamento.ausencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.controller.folhapagamento.ausencia.IFeriasPeriodoAquisitivoDAO;
import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class FeriasPeriodoAquisitivoDAO extends
		AbstractCrudDAO<FeriasPeriodoAquisitivoEntity> implements IFeriasPeriodoAquisitivoDAO {

	@Override
	public Class<FeriasPeriodoAquisitivoEntity> getEntityClass() {
		return FeriasPeriodoAquisitivoEntity.class;
	}

	@Transactional
	public List<FeriasPeriodoAquisitivoEntity> listarTodos() {
		try {
			String sql = "FROM FeriasPeriodoAquisitivoEntity ent WHERE (1 = 1)";

			List<FeriasPeriodoAquisitivoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FeriasPeriodoAquisitivoEntity>();
		}
	}

	@Transactional
	public List<FeriasPeriodoAquisitivoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM FeriasPeriodoAquisitivoEntity ent WHERE (1 = 1)"
					+ " AND ent.colaborador.matricula LIKE :q";

			List<FeriasPeriodoAquisitivoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FeriasPeriodoAquisitivoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Data início", "Data término" };
	}

}