package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.PppCatEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PppCatDAO extends AbstractCrudDAO<PppCatEntity> {

	@Override
	public Class<PppCatEntity> getEntityClass() {
		return PppCatEntity.class;
	}

	@Transactional
	public List<PppCatEntity> listarTodos() {
		try {
			String sql = "FROM PppCatEntity ent WHERE (1 = 1)";

			List<PppCatEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppCatEntity>();
		}
	}

	@Transactional
	public List<PppCatEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PppCatEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<PppCatEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PppCatEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "numeroCat", "dataAfastamento",
				"dataRegistro", "ppp" };
	}

}