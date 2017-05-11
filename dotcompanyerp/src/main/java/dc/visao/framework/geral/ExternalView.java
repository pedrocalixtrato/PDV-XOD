package dc.visao.framework.geral;

import com.sun.istack.logging.Logger;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public abstract class ExternalView extends VerticalLayout implements View{

	private static final long serialVersionUID = -8326681375074030295L;
	
	
	protected boolean showed;
	public static Logger logger = Logger.getLogger(ExternalView.class);
	

	public boolean recreateUIOnEnter(){
		return true;
	}
	
	private void viewEnter() {
		System.out.println("view enter");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		logger.info("Entered view...");
		if(!showed){
			initUI();
		}else{
			if(recreateUIOnEnter()){
				removeAllComponents();
				initUI();
			}
			getController().init();
			
		}
		showed = true;
		viewEnter();
	        
	}
	
	protected abstract void initUI();
	protected abstract ViewController getController();
	
	
}
