package dc.visao.framework.geral;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.JavaScript;
//import com.porotype.iconfont.FontAwesome;
import com.vaadin.annotations.Theme;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import dc.visao.framework.geral.fake.DataProvider;

@Component
@Scope("request")
@SuppressWarnings("serial")
@Theme("dotcompany")
@JavaScript({"jquery-1.11.1.min.js", "jquery.inputmask.bundle.2.4.18.min.js"})
public class MainUI extends UI implements ErrorHandler {

	public DataProvider dataProvider = new DataProvider();
	public static Logger logger = LoggerFactory.getLogger(MainUI.class);

	@Autowired(required = true)
	public MainController mainController;

	@Autowired
	SessionFactory sessionFactory;

	private String contextPath = "";
	private String pathInfo = "";

	@Override
	protected void init(VaadinRequest request) {
		logger.info("vaadin init called");
		//TODO FontAwesome.load();
		contextPath = request.getContextPath();
		String p = request.getPathInfo();
		populatePathInfo(p);
		initApp();
	}

	private void populatePathInfo(String p) {
		String[] pa = p.split("/");
		if (pa.length >= 2) {
			pathInfo = pa[1];
		} else {
			pathInfo = "";
		}
	}

	private void initApp() {
		getPage().addUriFragmentChangedListener(new UriFragmentChangedListener() {
			public void uriFragmentChanged(UriFragmentChangedEvent source) {
				logger.info("URI FRAGMENT CHANGED: " + source.getUriFragment());
				logger.info("PAGE: " + source.getPage().getLocation());
			}
		});

		mainController.initNavigationMenu(this);
		mainController.navigateTo(pathInfo);
	}

	@Override
	public void error(com.vaadin.server.ErrorEvent event) {
		Label label = new Label();
		label.setWidth(-1, Unit.PERCENTAGE);

		// Find the final cause
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(label);

		layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.addComponent(layout);
		mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

		setContent(mainLayout);
		String cause = "<b>Erro:</b><br/>";
		for (Throwable t = event.getThrowable(); t != null; t = t.getCause())
			if (t.getCause() == null) // We're at final cause
				cause += t.getClass().getName() + "<br/>";

		// Display the error message in a custom fashion
		layout.addComponent(new Label(cause, ContentMode.HTML));

		setContent(layout);
		

		DefaultErrorHandler.doDefault(event);
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getPathInfo() {
		return pathInfo;
	}

}
