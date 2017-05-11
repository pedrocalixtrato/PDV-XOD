package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.InformativoGuiaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class InformativoGuiaDAO extends AbstractCrudDAO<InformativoGuiaEntity> {

	@Override
	public Class<InformativoGuiaEntity> getEntityClass() {
		return InformativoGuiaEntity.class;
	}

	@Transactional
	public List<InformativoGuiaEntity> listarTodos() {
		try {
			String sql = "FROM InformativoGuiaEntity ent WHERE (1 = 1)";

			List<InformativoGuiaEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformativoGuiaEntity>();
		}
	}

	@Transactional
	public List<InformativoGuiaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InformativoGuiaEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<InformativoGuiaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformativoGuiaEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Número", "Bem", "Seguradora" };
	}

}