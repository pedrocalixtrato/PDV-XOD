package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.financeiro.ParcelaRecebimento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParcelaRecebimentoDAO extends AbstractCrudDAO<ParcelaRecebimento> implements IParcelaRecebimentoDAO {

	@Override
	public Class<ParcelaRecebimento> getEntityClass() {
		return ParcelaRecebimento.class;
	}

	@Transactional
	public List<ParcelaRecebimento> listaTodos() {
		return getSession().createQuery("from ParcelaRecebimento").list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IParcelaRecebimentoDAO#buscaPorParcelaReceber(dc.entidade.financeiro.ParcelaReceber)
	 */
	@Override
	@Transactional
	public List<ParcelaRecebimento> buscaPorParcelaReceber(ParcelaReceber parcelaReceber) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ParcelaRecebimento.class);
		criteria.add(Restrictions.eq("parcelaReceber", parcelaReceber));

		List<ParcelaRecebimento> parcelaPagamento = criteria.list();

		return parcelaPagamento;
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

}
