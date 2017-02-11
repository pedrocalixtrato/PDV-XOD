package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Produto;

@SuppressWarnings("serial")
public class ProdutoDAO extends HibernateGenericDAO<Produto, Long> implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<Produto> porDesc(Long codUsuario) {
		return this.em.createQuery("from Produto " + "where upper(codUsuario) like :codUsuario", Produto.class)
				.setParameter("codUsuario", codUsuario).getResultList();
	}

	/* consulta com JPQL */

	public List<Produto> porNome(String nome) {
		return this.em.createQuery("from Produto where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public Produto porSku(String sku) {
		try {
			return em.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}
