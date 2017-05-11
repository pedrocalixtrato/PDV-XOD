package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.ParcelaCondicaoPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ParcelaCondicaoPagamentoDAO extends AbstractCrudDAO<ParcelaCondicaoPagamento> implements IParcelaCondicaoPagamentoDAO {

	@Override
	public Class<ParcelaCondicaoPagamento> getEntityClass() {
		return ParcelaCondicaoPagamento.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<ParcelaCondicaoPagamento> listarTodos() {
		List<ParcelaCondicaoPagamento> lista = null;
		try {
			String sql = "FROM ParcelaCondicaoPagamento";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
