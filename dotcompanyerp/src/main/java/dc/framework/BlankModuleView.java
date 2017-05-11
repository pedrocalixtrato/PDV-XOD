package dc.framework;

import dc.entidade.framework.FmModulo;
import dc.visao.framework.geral.MainController;


public class BlankModuleView extends ModuleView{
	
	public BlankModuleView(FmModulo module,MainController controller) {
		super(module,controller);
	}

	private static final long serialVersionUID = 2662505237969993373L;
	
	@Override
	protected void moduleViewEnter() {
	}

	@Override
	protected void buildModuleViewLayout() {
		//blank do nothing.
	}



}
