package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagar;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class ParcelaPagarDAO extends AbstractCrudDAO<ParcelaPagar> implements IParcelaPagarDAO{

	@Override
	public Class<ParcelaPagar> getEntityClass() {
		return ParcelaPagar.class;
	}

	@Transactional
	public List<ParcelaPagar> listaTodos() {
		return getSession().createQuery("from ParcelaPagar").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] { "contaCaixa", "numeroParcela", "valor", "dataEmissao", "dataVencimento", "descontoAte", "sofreRetencao",
				"valorFaltante", "taxaJuro", "valorJuro", "taxaMulta", "valorMulta", "taxaDesconto", "valorDesconto"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IParcelaPagar#buscaPorParcelaPagar(dc.entidade.financeiro.ParcelaPagar)
	 */
	@Override
	@Transactional
	public List<ParcelaPagar> buscaPorParcelaPagar(ParcelaPagar parcelaPagar){
		 Session session = getSession();
         Criteria criteria = session.createCriteria(ParcelaPagar.class);
         criteria.add(Restrictions.eq("parcelaPagar", parcelaPagar));

         List<ParcelaPagar> parcelaPaga = criteria.list();
         
         return parcelaPaga;
	}
	

}
