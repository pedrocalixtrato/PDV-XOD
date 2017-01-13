package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Produto;

@SuppressWarnings("serial")
public class ProdutoDAO extends HibernateGenericDAO<Produto, Integer> implements Serializable {
	
	@Inject
	private EntityManager em;
	public List<Produto> porDesc(Long codUsuario) {
		return this.em.createQuery("from Produto " +
				"where upper(codUsuario) like :codUsuario", Produto.class)
				.setParameter("codUsuario", codUsuario)
				.getResultList();
	}
	
	

	
	
  
    
    

}
