package br.com.pdv.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.pdv.dao.GenericoDAO;

public class HibernateGenericDAO<T, ID extends Serializable> implements GenericoDAO<T, ID> {

	@PersistenceContext
	private EntityManager em;

	private Class<T> classeEntidade;

	@Resource
	private UserTransaction transaction;

	@SuppressWarnings("unchecked")
	public HibernateGenericDAO() {

		this.classeEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	@Override	
	public T buscarPeloCodigo(ID id) {
		
		return em.find(classeEntidade, id);
		
	}
	
			
		

	@Override
	public void salvar(T entidade) {
		try{
			transaction.begin();
			em.merge(entidade);
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void excluir(ID id) {
		try{			
		transaction.begin();
		T entidade = buscarPeloCodigo(id);
		em.remove(entidade);
		em.flush();
		transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filtrar(T entidade, String... propriedades) {

		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classeEntidade);

		if (propriedades != null) {
			for (String propriedade : propriedades) {
				try {
					Object valor = PropertyUtils.getProperty(entidade, propriedade);
					if (valor != null) {
						if (valor instanceof String) {
							criteria.add(Restrictions.ilike(propriedade, (String) valor, MatchMode.ANYWHERE));

						} else {

							criteria.add(Restrictions.eq(propriedade, valor));
						}
					}

				} catch (Exception e) {
					throw new RuntimeException("Propriedade nao encontrada", e);

				}
			}
		}

		return criteria.setCacheable(true).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(T entidade) {

		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classeEntidade);

		return criteria.setCacheable(true).list();

	}

}
