package dc.control.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import dc.anotacoes.FullTextSearch;

@ApplicationScoped
public class FullTextEntityManagerProducer {

	@Inject
	private EntityManagerFactory factory;
	
	@PostConstruct
	public void init() {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(factory.createEntityManager());
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			throw new RuntimeException("Erro indexando o banco de dados.", e);
		}
		fullTextEntityManager.close();
	}
	
	@Produces
	@RequestScoped
	@FullTextSearch
	public FullTextEntityManager createFullTextEntityManager() {
		return Search.getFullTextEntityManager(factory.createEntityManager());
	}
	
	public void close(@FullTextSearch @Disposes FullTextEntityManager fullTextEntityManager) {
		fullTextEntityManager.close();
	}
	
}
