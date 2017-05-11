package dc.servicos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SessionFactoryTest {

	public static void main(String[] args) throws Exception {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/java/applicationContext.xml");
//		LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) ctx.getBean("&sessionFactory");
//		Configuration cfg = lsfb.getConfiguration();
//		SchemaUpdate su = new SchemaUpdate(cfg);
//		su.execute(true, false);
		
		SessionFactory sf = ctx.getBean(SessionFactory.class);
		Session s = sf.openSession();
		s.createQuery("from Usuario").list();
		
		
	}
}
