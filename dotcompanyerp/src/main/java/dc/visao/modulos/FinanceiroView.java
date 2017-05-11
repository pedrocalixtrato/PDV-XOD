package dc.visao.modulos;

import com.sun.istack.logging.Logger;
import com.vaadin.ui.Label;

import dc.entidade.framework.FmModulo;
import dc.framework.ModuleView;
import dc.visao.framework.geral.MainController;


public class FinanceiroView extends ModuleView{
	
	
	public FinanceiroView(FmModulo module,MainController mainController) {
		super(module, mainController);
		
	}

	private static final long serialVersionUID = 8670914441833490592L;
	private static Logger logger = Logger.getLogger(FinanceiroView.class);
	
	@Override
	protected void moduleViewEnter() {
		// TODO Auto-generated method stub
		logger.info("Module Financeiro entered");
	}

	@Override
	protected void buildModuleViewLayout() {
		// TODO Auto-generated method stub
		addComponent(new Label("Coisas do financeiro"));
	}

}
