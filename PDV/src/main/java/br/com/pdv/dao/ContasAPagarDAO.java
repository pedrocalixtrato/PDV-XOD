//package br.com.pdv.dao;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//
//import br.com.pdv.dao.hibernate.HibernateGenericDAO;
//import br.com.pdv.domain.ContasAPagar;
//import br.com.pdv.domain.StatusConta;
//import br.com.pdv.filter.ContasAPagarFilter;
//
//@SuppressWarnings("serial")
//public class ContasAPagarDAO extends HibernateGenericDAO<ContasAPagar, Long> implements Serializable {
//
//	@Inject
//	private EntityManager em;
//
//	@SuppressWarnings({ "unchecked", "static-access" })
//	public List<ContasAPagar> bucarPorData(Date data, StatusConta tipo) {
//		Date date = new Date();
//		Session session = em.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(ContasAPagar.class);
//		criteria.add(Restrictions.le("data", date)).add(Restrictions.eq("tipo", tipo.ABERTO));
//
//		return criteria.setCacheable(true).list();
//
//		// }
//		//
//		// Query query = this.em.createNativeQuery("select * from contasapagar c
//		// where c.data < current_date",
//		// ContasAPagar.class);
//		//
//		// return query.getResultList();
//		//
//		// }
//
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<ContasAPagar> filtro(ContasAPagarFilter filtro) {
//
//		Session session = em.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(ContasAPagar.class);
//		
//		if(filtro.getDataInicial() != null && filtro.getDataFinal() != null){
//		
//		criteria.add(Restrictions.between("data", filtro.getDataInicial(), filtro.getDataFinal()));
//		
//		}
//		
//		return criteria.setCacheable(true).list();
//	}
//	
//	public BigDecimal somarTotal(ContasAPagarFilter cFilter){
//		
//		Session session = em.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(ContasAPagar.class);
//		
//		if(cFilter.getDataInicial() ==null && cFilter.getDataFinal() == null){
//			
//			criteria.setProjection(Projections.sum("valor"));
//			return (BigDecimal) criteria.uniqueResult();
//
//		}
//
//		criteria.setProjection(Projections.sum("valor"));
//		criteria.add(Restrictions.ge("data", cFilter.getDataInicial()))
//				.add(Restrictions.le("data", cFilter.getDataFinal()));
//
//		return (BigDecimal) criteria.uniqueResult();
//		
//	}
//
//}
