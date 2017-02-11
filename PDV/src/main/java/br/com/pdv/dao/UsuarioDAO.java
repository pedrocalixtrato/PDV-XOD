package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Usuario;

@SuppressWarnings("serial")
public class UsuarioDAO extends HibernateGenericDAO<Usuario, Long> implements Serializable{
	
	@PersistenceContext
	private EntityManager em;
	
	public Usuario porEmail(String email) {
		Usuario usuario = null;
		
		try {
			usuario = this.em.createQuery("from Usuario where lower(email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return usuario;
	}
	
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.em.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}
	

}
