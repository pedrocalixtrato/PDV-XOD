package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.AidfAimdfEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class AidfAimdfDAO extends AbstractCrudDAO<AidfAimdfEntity> implements IAidfAimdfDAO {

	@Override
	public Class<AidfAimdfEntity> getEntityClass() {
		return AidfAimdfEntity.class;
	}

	@Transactional
	public List<AidfAimdfEntity> listarTodos() {
		try {
			String sql = "FROM AidfAimdfEntity ent WHERE (1 = 1)";

			List<AidfAimdfEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<AidfAimdfEntity>();
		}
	}

	@Override
	@Transactional
	public List<AidfAimdfEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM AidfAimdfEntity ent WHERE (1 = 1) AND ent.numeroAutorizacao LIKE :q";

			List<AidfAimdfEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<AidfAimdfEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Número da autorização", "Formulário disponível" };
	}

}