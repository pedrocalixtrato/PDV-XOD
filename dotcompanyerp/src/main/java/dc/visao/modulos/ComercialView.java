package dc.visao.modulos;

import com.sun.istack.logging.Logger;
import com.vaadin.ui.Label;

import dc.entidade.framework.FmModulo;
import dc.framework.ModuleView;
import dc.visao.framework.geral.MainController;


public class ComercialView extends ModuleView{

	public ComercialView(FmModulo module,MainController controller) {
		super(module,controller);
	}

	private static final long serialVersionUID = -4423752584073532354L;
	private static Logger logger = Logger.getLogger(ComercialView.class);
	
	@Override
	protected void moduleViewEnter() {
		logger.info("ComercialView entered...");
		//addComponent(new Label("Comercial"));
	}

	@Override
	protected void buildModuleViewLayout() {
		addComponent(new Label("Coisas do comercial"));
		
	}

}
