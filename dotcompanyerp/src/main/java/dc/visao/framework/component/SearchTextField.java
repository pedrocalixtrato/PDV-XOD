package dc.visao.framework.component;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TextField;

import dc.framework.ConfigProperties;
import dc.visao.framework.geral.CRUDListController;

public class SearchTextField extends TextField {

	private String filterString;
	private CRUDListController controller;

	public SearchTextField(CRUDListController controller) {

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
		ConfigProperties config = (ConfigProperties) ctx.getBean(ConfigProperties.class);
		this.setTextChangeTimeout(config.COMBO_DELAYVALUE);
		this.setTextChangeEventMode(TextChangeEventMode.LAZY);
		this.setImmediate(true);
		this.controller = controller;

		this.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				String newFilter;
				if ((newFilter = (String) event.getText()) != null) {
					filterString = newFilter;
				}

				String txt = filterString;
				if (filterString == null || filterString.trim().isEmpty()) {
					txt = "";
				}
				controller.doSearch(txt);

			}

		});
	}
}
