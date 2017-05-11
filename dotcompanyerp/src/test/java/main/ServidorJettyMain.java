package main;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class ServidorJettyMain {

	public static void main(String[] args) throws Exception {
        Server server = new Server(9090);

		WebAppContext dotCompanyErp = new WebAppContext();
		dotCompanyErp.setContextPath("/dotcompanyerp");
        dotCompanyErp.setResourceAlias("WEB-INF/classes", "build/exploded/WEB-INF/classes");
		dotCompanyErp.setBaseResource(new ResourceCollection(new String[] { "src/main/webapp"}));

		dotCompanyErp.setAttribute("scanInterval", "3");

		server.setHandler(dotCompanyErp);
		server.setStopAtShutdown(true);
		server.start();
		server.join();
	}

}
