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
		return this.em.createQuery("from Cliente " +
				"where upper(CLI_RAZAO) like :CLI_RAZAO", Clientes.class)
				.setParameter("CLI_RAZAO", nome.toUpperCase() + "%")
				.getResultList();
	}


}
