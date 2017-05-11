package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.CondicaoPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CondicaoPagamentoDAO extends AbstractCrudDAO<CondicaoPagamento> implements ICondicaoPagamentoDAO {

	@Override
	public Class<CondicaoPagamento> getEntityClass() {
		return CondicaoPagamento.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	@Transactional
	public List<CondicaoPagamento> listaTodos() {
		return getSession().createQuery("from CondicaoPagamento").list();
	}
	
}