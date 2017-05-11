package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.FluxoCaixaDetalheEntity;
import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class NaturezaFinanceiraDAO extends AbstractCrudDAO<NaturezaFinanceira> implements INaturezaFinanceiraDAO{

	@Override
	public Class<NaturezaFinanceira> getEntityClass() {
		return NaturezaFinanceira.class;
	}
		
	@Transactional
	public List<NaturezaFinanceira> listaTodos() {
		return getSession().createQuery("from NaturezaFinanceira").list();
	}

	@Transactional
	public List<NaturezaFinanceira> procuraNomeContendo(String query) {
		return getSession().createQuery("from NaturezaFinanceira where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"tipo", "descricao","contas_receber","contas_pagar"};
	}

	public List<NaturezaFinanceira> findAll() {
		try {
			List<NaturezaFinanceira> auxLista = this.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.INaturezaFinanceiraDAO#findByNatureza(dc.entidade.financeiro.LancamentoPagarEntity)
	 */
	@Override
	@Transactional
	public List<NaturezaFinanceira> findByNatureza(LancamentoPagarEntity currentBean) {

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
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.INaturezaFinanceiraDAO#findByNaturezaReceber(dc.entidade.financeiro.LancamentoReceber)
	 */
	@Override
	@Transactional
	public List<NaturezaFinanceira> findByNaturezaReceber(LancamentoReceber currentBean) {

			List<NaturezaFinanceira> lista = new ArrayList<>();

			try{
				if(currentBean!=null){
					lista =  getSession()
							.createQuery("from NaturezaFinanceira i where i.lancamentoReceber = :lancamentoReceber")
							.setParameter("lancamentoReceber", currentBean).list();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return lista;
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.INaturezaFinanceiraDAO#findByNatureza(dc.entidade.financeiro.FluxoCaixaEntity)
	 */
	@Override
	@Transactional
	public List<FluxoCaixaDetalheEntity> findByNatureza(FluxoCaixaEntity currentBean) {

			List<FluxoCaixaDetalheEntity> lista = new ArrayList<>();

			try{
				if(currentBean!=null){
					lista =  getSession()
							.createQuery("from FluxoCaixaDetalhe i where i.fluxoCaixa = :fluxoCaixa")
							.setParameter("fluxoCaixa", currentBean).list();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return lista;
	}
}
