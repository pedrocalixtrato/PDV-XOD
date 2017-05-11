package dc.visao.framework.component.manytoonecombo.connector;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

import dc.visao.framework.component.manytoonecombo.extensions.ButtonHintExtension;

@Connect(ButtonHintExtension.class)
public class ButtonHintConnector extends AbstractExtensionConnector {
	
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 2506533249272148432L;

		@Override
	    protected void extend(ServerConnector target) {
	        final Widget button = ((ComponentConnector) target).getWidget();
	        //EventHandler handler = ;
	        MouseOverHandler handler = new MouseOverHandler() {

	            public void onMouseOver(MouseOverEvent event) {
	                System.out.println("mouse over");
	                button.getElement().setAttribute("title", "exemploHint");
	            }
	        };
	        
			button.addDomHandler(handler,MouseOverEvent.getType() );
	    }

}
