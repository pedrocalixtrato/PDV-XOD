package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Produto;

@SuppressWarnings("serial")
public class ProdutoDAO extends HibernateGenericDAO<Produto, Long> implements Serializable {

	@Inject
	private EntityManager em;

	public List<Produto> porDesc(Long codUsuario) {
		return this.em.createQuery("from Produto " + "where upper(codUsuario) like :codUsuario", Produto.class)
				.setParameter("codUsuario", codUsuario).getResultList();
	}

	/* consulta com JPQL */

	public List<Produto> porNome(String descricao) {
		return this.em.createQuery("from Produto where upper(descricao) like :descricao", Produto.class)
				.setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
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
