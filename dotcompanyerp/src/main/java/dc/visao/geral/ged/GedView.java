package dc.visao.geral.ged;

import com.vaadin.ui.Label;

import dc.entidade.framework.FmModulo;
import dc.framework.ModuleView;
import dc.visao.framework.geral.MainController;

public class GedView extends ModuleView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089266649554675582L;

	public GedView(FmModulo module, MainController controller) {
		super(module, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildModuleViewLayout() {
		addComponent(new Label("Gerenciamento eletrônico de documentos"));
		
	}

	@Override
	protected void moduleViewEnter() {
	
		
	}

}
