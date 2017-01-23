package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Clientes;

@SuppressWarnings("serial")
public class ClienteDAO extends HibernateGenericDAO<Clientes, Long> implements Serializable{
 	
	@Inject
	private EntityManager em;

	
	public List<Clientes> porNome(String nome) {
		return this.em.createQuery("from Clientes " +
				"where upper(nome) like :nome", Clientes.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}


}
