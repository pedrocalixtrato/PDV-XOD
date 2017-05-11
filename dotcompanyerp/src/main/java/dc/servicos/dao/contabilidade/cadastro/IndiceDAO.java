package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class IndiceDAO extends AbstractCrudDAO<IndiceEntity> implements IIndiceDAO {

	@Override
	public Class<IndiceEntity> getEntityClass() {
		return IndiceEntity.class;
	}

	@Transactional
	public List<IndiceEntity> listarTodos() {
		try {
			String sql = "FROM IndiceEntity ent WHERE (1 = 1)";

			List<IndiceEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceEntity>();
		}
	}

	@Transactional
	public List<IndiceEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM IndiceEntity ent WHERE (1 = 1) AND ent.periodicidade LIKE :q";

			List<IndiceEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Periodicidade", "Diário a partir de",
				"Mensal mês ano" };
	}

}