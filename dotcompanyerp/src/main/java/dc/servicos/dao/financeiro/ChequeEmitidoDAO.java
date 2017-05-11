package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ChequeEmitido;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ChequeEmitidoDAO extends AbstractCrudDAO<ChequeEmitido> {
		
@Override
public Class<ChequeEmitido> getEntityClass() {
	return ChequeEmitido.class;
}
@Transactional
public List<ChequeEmitido> listaTodos() {
	return getSession().createQuery("from ChequeEmitido").list();
}
	
@Transactional
public List<ChequeEmitido> buscaPorParcelaPagar(ChequeEmitido chequeEmitido){
	 Session session = getSession();
        Criteria criteria = session.createCriteria(ChequeEmitido.class);
        criteria.add(Restrictions.eq("chequeEmitido", chequeEmitido));
         List<ChequeEmitido> chequesEmitido = criteria.list();
	         
        return chequesEmitido;
}
@Override
public String[] getDefaultSearchFields() {
		return new String[] { ""  };
 }


}