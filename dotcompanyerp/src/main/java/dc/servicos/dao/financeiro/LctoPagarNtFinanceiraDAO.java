package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LctoPagarNtFinanceiraDAO extends
		AbstractCrudDAO<LctoPagarNtFinanceira> implements ILctoPagarNtFinanceiroDAO {

	@Override
	public Class<LctoPagarNtFinanceira> getEntityClass() {
		return LctoPagarNtFinanceira.class;
	}

	@Transactional
	public List<LctoPagarNtFinanceira> listaTodos() {
		return getSession().createQuery("from LctoPagarNtFinanceira").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "valor","dataInclusao" };
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILctoPagarNtFinanceiroDAO#findByNatureza(dc.entidade.financeiro.LancamentoPagarEntity)
	 */
	@Override
	@Transactional
	public List<LctoPagarNtFinanceira> findByNatureza(LancamentoPagarEntity currentBean) {

			List<LctoPagarNtFinanceira> lista = new ArrayList<>();

			try{
				if(currentBean!=null){
					lista =  getSession()
							.createQuery("from LctoPagarNtFinanceira i where i.lancamentoPagar = :lancamentoPagar")
							.setParameter("lancamentoPagar", currentBean).list();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return lista;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILctoPagarNtFinanceiroDAO#findByNaturezaFin(dc.entidade.financeiro.LancamentoPagarEntity)
	 */
	@Override
	@Transactional
	public List<NaturezaFinanceira> findByNaturezaFin(LancamentoPagarEntity currentBean) {

		List<NaturezaFinanceira> lista = new ArrayList<>();

		try{
			if(currentBean!=null){
				lista =  getSession()
						.createQuery("from NaturezaFinanceira i where i.lancamentoPagar = :lancamentoPagar")
						.setParameter("lancamentoPagar", currentBean).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}
