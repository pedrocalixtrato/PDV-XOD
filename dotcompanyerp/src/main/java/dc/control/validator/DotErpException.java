package dc.control.validator;

import com.vaadin.ui.AbstractComponent;

public class DotErpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractComponent component;

	public DotErpException(AbstractComponent component, String msg) {
		// TODO Auto-generated constructor stub
		super(msg);

		this.component = component;
	}

	public AbstractComponent getComponent() {
		return component;
	}

}