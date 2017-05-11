package dc.visao.ponto;

import com.vaadin.ui.Label;

import dc.entidade.framework.FmModulo;
import dc.framework.ModuleView;
import dc.visao.framework.geral.MainController;

public class PontoView extends ModuleView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8373201547999439L;

	public PontoView(FmModulo module, MainController controller) {
		super(module, controller);
	}

	@Override
	protected void buildModuleViewLayout() {
		addComponent(new Label("Ponto Eletrônico"));
		
	}

	@Override
	protected void moduleViewEnter() {
	}

}
