package dc.framework;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;

import dc.framework.client.SerchTextFieldServerRPC;
import dc.visao.framework.geral.CRUDListController;

public class SearchTextFieldExtension extends AbstractExtension {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CRUDListController controller;
	private ConfigProperties config;

	public SearchTextFieldExtension(CRUDListController controller) {
		this.controller = controller;
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(VaadinServlet.getCurrent()
						.getServletContext());

		config = (ConfigProperties) ctx
				.getBean(ConfigProperties.class);

	}

	public void extend(TextField field) {
		System.out.println("extend..being called");
		field.setTextChangeTimeout(config.COMBO_DELAYVALUE);
		field.setTextChangeEventMode(TextChangeEventMode.LAZY);
		super.extend(field);
		SerchTextFieldServerRPC rpc = new SerchTextFieldServerRPC() {

			private static final long serialVersionUID = 2695079502474207881L;
			private int clickCount = 0;

			public void clicked(String buttonName) {
				Notification.show("Clicou" + buttonName);
			}

			public void search(String value) {
				String txt = value;
				if (value == null || value.trim().isEmpty()) {
					txt = " todos os registros";
				}
				controller.doSearch(value);
			}

			@Override
			public void schedule() {

			}
		};

		registerRpc(rpc);
	}

}
