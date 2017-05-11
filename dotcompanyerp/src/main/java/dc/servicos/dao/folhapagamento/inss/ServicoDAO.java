package dc.servicos.dao.folhapagamento.inss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.inss.ServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class ServicoDAO extends AbstractCrudDAO<ServicoEntity> implements IServicoDAO {

	@Override
	public Class<ServicoEntity> getEntityClass() {
		return ServicoEntity.class;
	}

	@Transactional
	public List<ServicoEntity> listarTodos() {
		try {
			String sql = "FROM ServicoEntity ent WHERE (1 = 1)";

			List<ServicoEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ServicoEntity>();
		}
	}

	@Transactional
	public List<ServicoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ServicoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<ServicoEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ServicoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Código", "Nome" };
	}

}