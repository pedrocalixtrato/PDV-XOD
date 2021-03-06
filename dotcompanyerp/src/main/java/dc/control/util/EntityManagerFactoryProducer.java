package dc.control.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {

	private EntityManagerFactory factory;
	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory createFactory() {
		this.factory = Persistence.createEntityManagerFactory("hibernateSearch");
		return this.factory;
	}
	
	public void close(@Disposes EntityManagerFactory factory) {
		factory.close();
	}
	
}