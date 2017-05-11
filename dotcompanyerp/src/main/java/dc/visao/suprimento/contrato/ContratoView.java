package dc.visao.suprimento.contrato;

import com.vaadin.ui.Label;

import dc.entidade.framework.FmModulo;
import dc.framework.ModuleView;
import dc.visao.framework.geral.MainController;

public class ContratoView extends ModuleView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContratoView(FmModulo module, MainController controller) {
		super(module, controller);
	}

	@Override
	protected void buildModuleViewLayout() {
		addComponent(new Label("Gestão de Contratos"));
	}

	@Override
	protected void moduleViewEnter() {

	}

}