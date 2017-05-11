package dc.servicos.dao.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.cadastro.ParametroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class ParametroDAO extends AbstractCrudDAO<ParametroEntity> {

	@Override
	public Class<ParametroEntity> getEntityClass() {
		return ParametroEntity.class;
	}

	@Transactional
	public List<ParametroEntity> listarTodos() {
		try {
			String sql = "FROM ParametroEntity ent WHERE (1 = 1)";

			List<ParametroEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ParametroEntity>();
		}
	}

	@Transactional
	public List<ParametroEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ParametroEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<ParametroEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ParametroEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Competência", "Contribui PIS",
				"Percentual do adiantamento 13" };
	}

}