package dc.visao.framework.geral;

import org.vaadin.teemu.wizards.WizardStep;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseWizardStep implements WizardStep {

	
	private Label error = new Label("",ContentMode.HTML);
	private VerticalLayout mainPanel;

	@Override
	public abstract String getCaption();

	@Override
	public Component getContent() {
		
		
		mainPanel = new VerticalLayout();
		mainPanel.setMargin(true);
		
		error.addStyleName("error");
		error.setSizeUndefined();
		error.addStyleName("light");
		error.addStyleName("v-animate-reveal");
		error.setVisible(true);
        mainPanel.addComponent(error);
        
        fillMainPanel(mainPanel);
		
		return mainPanel;
	}

	protected abstract void fillMainPanel(VerticalLayout mainPanel);
		

	@Override
	public boolean onAdvance() {
		// TODO Auto-generated method stub
		if(!validate()){
			showErrorMessage("Selecione uma resposta para avançar");
			return false;
		}else{
			try {
				saveConfiguration();
				cleanErrors();
			} catch (Exception e) {
				showErrorMessage("Problemas ao salvar. Verifique os dados e tente novamente em instantes.");
				e.printStackTrace();
				return false;
			}
			
			
		}
		return true;
	}

	protected abstract void saveConfiguration();

	protected abstract boolean validate();

	private void cleanErrors() {
		error.setValue("");
		error.setVisible(false);
	}

	protected void showErrorMessage(String message) {
		mainPanel.removeComponent(error);
		error.setVisible(true);
		error.setValue(message);
		mainPanel.addComponentAsFirst(error);
	}

	@Override
	public boolean onBack() {
		// TODO Auto-generated method stub
		return false;
	}

}
