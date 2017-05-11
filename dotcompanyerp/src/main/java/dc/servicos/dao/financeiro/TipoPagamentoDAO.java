package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.TipoPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class TipoPagamentoDAO extends AbstractCrudDAO<TipoPagamento> implements ITipoPagamentoDAO{

	@Override
	public Class<TipoPagamento> getEntityClass() {
		return TipoPagamento.class;
	}

	@Transactional
	public List<TipoPagamento> listaTodos() {
		return getSession().createQuery("from TipoPagamento").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	

}
