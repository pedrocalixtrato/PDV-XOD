package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.LancamentoReceber;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author wesleyj
 *
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoReceberDAO extends AbstractCrudDAO<LancamentoReceber> implements ILancamentoReceberDAO{

	@Override
	public Class<LancamentoReceber> getEntityClass() {
		return LancamentoReceber.class;
	}

	@Transactional
	public List<LancamentoReceber> listaTodos() {
		return getSession().createQuery("from LancamentoReceber").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "documentoOrigem", "cliente", "valorTotal", "valorAReceber", "dataLancamento" };
	}
	
	@Transactional
	public List<LancamentoReceber> procuraNomeContendo(String query) {
		return getSession().createQuery("from LancamentoReceber where valorTotal like :q").setParameter("q", "%" + query + "%").list();
	}
	
	@Transactional
	public List<LancamentoReceber> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from LancamentoReceber where lower(valorTotal) like :q").setParameter("q", q).list();
	}

}
