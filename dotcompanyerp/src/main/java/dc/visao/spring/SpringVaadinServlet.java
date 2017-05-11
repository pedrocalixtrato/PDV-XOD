package dc.visao.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.vaadin.server.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.ui.UI;

/**
 * 
 * @author Wesley Jr /* configuração do Servlet o Vaadin Servlet.
 * 
 */

public class SpringVaadinServlet extends VaadinServlet {

	private WebApplicationContext applicationContext;

	public void init(ServletConfig servletConfig) throws ServletException {
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		super.init(servletConfig);
	}

	@Override
	protected VaadinServletService createServletService(
			DeploymentConfiguration deploymentConfiguration) {

		VaadinServletService service = null;
		try {
			service = super.createServletService(deploymentConfiguration);
			service.addSessionInitListener(new SessionInitListener() {
				@Override
				public void sessionInit(SessionInitEvent event)
						throws ServiceException {
					System.out.println("session init");
					event.getSession().addUIProvider(new DefaultUIProvider() {
						@Override
						public UI createInstance(UICreateEvent event) {
							return applicationContext.getBean(event
									.getUIClass());
						}
					});
				}
			});
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SystemMessagesProvider systemMessagesProvider = new SystemMessagesProvider() {

			CustomizedSystemMessages customizedSystemMessages;

			@Override
			public SystemMessages getSystemMessages(
					SystemMessagesInfo systemMessagesInfo) {

				if (customizedSystemMessages == null) {
					customizedSystemMessages = new CustomizedSystemMessages();
					String loginPage = systemMessagesInfo.getService()
							.getCurrentRequest().getContextPath()
							+ "/login/";
					customizedSystemMessages.setSessionExpiredURL(loginPage);
					customizedSystemMessages
							.setSessionExpiredNotificationEnabled(false);
				}

				return customizedSystemMessages;
			}
		};

		service.setSystemMessagesProvider(systemMessagesProvider);

        service.addSessionInitListener(event -> {
            event.getSession().setErrorHandler(new DefaultErrorHandler(){
                @Override
                public void error(ErrorEvent errorEvent) {
                    try {
                        super.error(errorEvent);
                    } finally {
                        log("GOODBYE WORLD");
                        event.getSession().close();
                        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
                    }
                }
            });
        });
		return service;
	}

}
