package dc.visao.framework.geral;

/*
*
*@author Wesley Jr
*
*Classe do Menu
*/

public class Menu {
	
	String caption;
	String viewName;
	
	public Menu(String caption, String controller) {
		super();
		this.caption = caption;
		this.viewName = controller;
	}

}
