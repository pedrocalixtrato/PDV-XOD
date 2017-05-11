package dc.servicos.dao.folhapagamento.inss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.inss.InssEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class InssDAO extends AbstractCrudDAO<InssEntity> implements IInssDAO {

	@Override
	public Class<InssEntity> getEntityClass() {
		return InssEntity.class;
	}

	@Transactional
	public List<InssEntity> listarTodos() {
		try {
			String sql = "FROM InssEntity ent WHERE (1 = 1)";

			List<InssEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InssEntity>();
		}
	}

	@Transactional
	public List<InssEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InssEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<InssEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InssEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "competência" };
	}

}