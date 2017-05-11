package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.RescisaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class RescisaoDAO extends AbstractCrudDAO<RescisaoEntity> {

	@Override
	public Class<RescisaoEntity> getEntityClass() {
		return RescisaoEntity.class;
	}

	@Transactional
	public List<RescisaoEntity> listarTodos() {
		try {
			String sql = "FROM RescisaoEntity ent WHERE (1 = 1)";

			List<RescisaoEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<RescisaoEntity>();
		}
	}

	@Transactional
	public List<RescisaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM RescisaoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<RescisaoEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<RescisaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "colaborador", "dataDemissao",
				"dataPagamento", "motivo" };
	}

}