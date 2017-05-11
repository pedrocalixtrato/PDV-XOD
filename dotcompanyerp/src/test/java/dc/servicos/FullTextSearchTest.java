package dc.servicos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.financeiro.BancoDAO;

/**
*
* @author Wesley Jr
/*
 * Nessa classe é a classe principal.
 * Que tem a configuração do Button pesquisar, mas é s� uma classe de Teste
 *
*/

public class FullTextSearchTest {

	public static void main(String[] args) throws Exception {
		new FullTextSearchTest().run();
	}
	
	public void run() throws Exception {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/applicationContext.xml");
		BancoDAO dao = ctx.getBean(BancoDAO.class);
		
		Session session = dao.getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();
	
		List<BancoEntity> result = dao.fullTextSearch("republicas");
		
		for (BancoEntity b : result) {
			System.out.println(b.getNome());
		}
		
		Thread.sleep(3000);
		ctx.close();
	}
}
