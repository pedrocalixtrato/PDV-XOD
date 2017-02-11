package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Cliente;

@SuppressWarnings("serial")
public class ClienteDAO extends HibernateGenericDAO<Cliente, Long> implements Serializable{
 	
	@PersistenceContext
	private EntityManager em;

	
	public List<Cliente> porNome(String nome) {
		return this.em.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}


}
