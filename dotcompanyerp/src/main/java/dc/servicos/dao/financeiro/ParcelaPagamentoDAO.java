package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.ParcelaPagar;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParcelaPagamentoDAO extends AbstractCrudDAO<ParcelaPagamento> implements IParcelaPagamentoDAO {

	@Override
	public Class<ParcelaPagamento> getEntityClass() {
		return ParcelaPagamento.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from ParcelaPagamento").list();
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IParcelaPagamentoDAO#buscaPorParcelaPagar(dc.entidade.financeiro.ParcelaPagar)
	 */
	@Override
	@Transactional
	public List<ParcelaPagamento> buscaPorParcelaPagar(ParcelaPagar parcelaPagar){
		 Session session = getSession();
         Criteria criteria = session.createCriteria(ParcelaPagamento.class);
         criteria.add(Restrictions.eq("parcelaPagar", parcelaPagar));

         List<ParcelaPagamento> parcelaPagamentos = criteria.list();
         
         return parcelaPagamentos;
	}
	
	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "contaCaixa","parcelaPagar","tipoPagamento"  };
	}

}
