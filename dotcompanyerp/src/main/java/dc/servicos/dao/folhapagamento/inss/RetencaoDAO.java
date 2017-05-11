package dc.servicos.dao.folhapagamento.inss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.inss.RetencaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class RetencaoDAO extends AbstractCrudDAO<RetencaoEntity> implements IRetencaoDAO {

	@Override
	public Class<RetencaoEntity> getEntityClass() {
		return RetencaoEntity.class;
	}

	@Transactional
	public List<RetencaoEntity> listarTodos() {
		try {
			String sql = "FROM RetencaoEntity ent WHERE (1 = 1)";

			List<RetencaoEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<RetencaoEntity>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.folhapagamento.inss.IRetencaoDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<RetencaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM RetencaoEntity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";

			List<RetencaoEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<RetencaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "inss", "servico", "valorMensal", "valor13" };
	}

}