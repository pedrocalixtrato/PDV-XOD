package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.CnaeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CnaeDAO extends AbstractCrudDAO<CnaeEntity> implements ICnaeDAO {

	@Override
	public Class<CnaeEntity> getEntityClass() {
		return CnaeEntity.class;
	}

	@Transactional
	public List<CnaeEntity> listarTodos() {
		List<CnaeEntity> lista = null;
		try {
			String sql = "FROM :entity";
			sql = sql.replace(":entity", getEntityClass().getName());

			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}