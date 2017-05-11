package dc.visao.framework.geral;

import java.net.URI;

import com.vaadin.server.Page;

public class VaadinURLUtils {

	public static String getInitialURL() {
		MainUI ui = (MainUI) MainUI.getCurrent();
		String location = ui.getContextPath();
		if(location == null || location.trim().isEmpty()){
			URI uri = Page.getCurrent().getLocation();
			location = uri.getScheme() +"://"+ uri.getAuthority();
		}
		return location;
	}

}
