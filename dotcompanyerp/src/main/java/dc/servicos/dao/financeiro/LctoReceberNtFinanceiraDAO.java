package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.LctoReceberNtFinanceiraEntity;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LctoReceberNtFinanceiraDAO extends
		AbstractCrudDAO<LctoReceberNtFinanceiraEntity> implements ILctoReceberNtFinanceiraDAO {

	@Override
	public Class<LctoReceberNtFinanceiraEntity> getEntityClass() {
		return LctoReceberNtFinanceiraEntity.class;
	}

	@Transactional
	public List<LctoReceberNtFinanceiraDAO> listaTodos() {
		return getSession().createQuery("from LctoReceberNtFinanceiraEntity").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "valor", "dataInclusao" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILctoReceberNtFinanceiraDAO#findByNatureza(dc.entidade.financeiro.LancamentoReceber)
	 */
	@Override
	@Transactional
	public List<LctoReceberNtFinanceiraEntity> findByNatureza(
			LancamentoReceber currentBean) {

		List<LctoReceberNtFinanceiraEntity> lista = new ArrayList<>();

		try {
			if (currentBean != null) {
				lista = getSession()
						.createQuery(
								"from LctoReceberNtFinanceiraEntity i where i.lancamentoReceber = :lancamentoReceber")
						.setParameter("lancamentoReceber", currentBean).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILctoReceberNtFinanceiraDAO#findByNaturezaFin(dc.entidade.financeiro.LancamentoReceber)
	 */
	@Override
	@Transactional
	public List<NaturezaFinanceira> findByNaturezaFin(
			LancamentoReceber currentBean) {

		List<NaturezaFinanceira> lista = new ArrayList<>();

		try {
			if (currentBean != null) {
				lista = getSession()
						.createQuery(
								"from NaturezaFinanceira i where i.lancamentoReceber = :lancamentoReceber")
						.setParameter("lancamentoReceber", currentBean).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILctoReceberNtFinanceiraDAO#findByNaturezaReceber(dc.entidade.financeiro.LancamentoReceber)
	 */
	@Override
	@Transactional
	public List<LctoReceberNtFinanceiraEntity> findByNaturezaReceber(LancamentoReceber currentBean) {

			List<LctoReceberNtFinanceiraEntity> lista = new ArrayList<>();

			try{
				if(currentBean!=null){
					lista =  getSession()
							.createQuery("from LctoReceberNtFinanceira i where i.lancamentoReceber = :lancamentoReceber")
							.setParameter("lancamentoReceber", currentBean).list();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return lista;
	}	

}
