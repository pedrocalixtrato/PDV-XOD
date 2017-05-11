package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository(value = "contabilidadeLancamentoDetalheDAO")
@SuppressWarnings("unchecked")
public class LancamentoDetalheDAO extends
		AbstractCrudDAO<LancamentoDetalheEntity> implements ILancamentoDetalheDAO {

	@Override
	public Class<LancamentoDetalheEntity> getEntityClass() {
		return LancamentoDetalheEntity.class;
	}

	@Transactional
	public List<LancamentoDetalheEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoDetalheEntity ent WHERE (1 = 1)";

			List<LancamentoDetalheEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoDetalheEntity>();
		}
	}

	@Transactional
	public List<LancamentoDetalheEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoDetalheEntity ent WHERE (1 = 1) AND ent.historico LIKE :q";

			List<LancamentoDetalheEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoDetalheEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Histórico", "Valor", "Tipo" };
	}

}