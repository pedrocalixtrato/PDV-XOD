package dc.visao.framework.component.manytoonecombo.extensions;

import com.sun.istack.logging.Logger;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Button;

public class ButtonHintExtension extends AbstractExtension{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -3198919297304650043L;
	public static Logger logger = Logger.getLogger(ButtonHintExtension.class);
	
	protected ButtonHintExtension(Button field) {
			logger.info("extending button hint:" + field);
	        extend(field);
	   }

	public static ButtonHintExtension addHint(String string, Button btn) {
		// TODO Auto-generated method stub
		return new ButtonHintExtension(btn);
		
	}

	    

}
