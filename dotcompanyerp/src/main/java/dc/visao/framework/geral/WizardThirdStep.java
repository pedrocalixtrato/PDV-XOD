package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.core.java.util.Collections;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmModulo;
import dc.visao.spring.SecuritySessionProvider;

public class WizardThirdStep extends BaseWizardStep implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3313923964964263506L;


	private TwinColSelect sample;


	private ConfiguraNovaContaController controller;

	public WizardThirdStep(ConfiguraNovaContaController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return "Escolha os Módulos";
	}
       
	@Override
	public boolean onAdvance() {
		// TODO Auto-generated method stub
		if(validate()){
			try{
			saveConfiguration();
			return true;
			}catch (Exception e) {
				e.printStackTrace();
				showErrorMessage("Problemas ao salvar.");
				return false;
			}
		}else{
			showErrorMessage("Selecione ao menos um Módulo para prosseguir");
			return false;
		}
	}

	
	@Override
	public boolean onBack() {
		new Notification("Pressione o botão finalizar para continuar").show(Page.getCurrent());
		return false;
	}
	
	@Override
	protected void fillMainPanel(VerticalLayout mainPanel) {
		Label question = new Label("Quais os Módulos Você gostaria de utilizar inicialmente?");
        question.setSizeUndefined();
        question.addStyleName("h4");
        mainPanel.addComponent(question);
        
        sample = new TwinColSelect();
        sample.setWidth("90%");
        sample.setHeight("70%");
        List<FmModulo> modulos = controller.buscaModulos();
        BeanItemContainer<FmModulo> objects = new BeanItemContainer(FmModulo.class, modulos);
        sample.setContainerDataSource(objects);
        sample.setItemCaptionPropertyId("caption");
        sample.setRows(8);
        sample.setNullSelectionAllowed(true);
        sample.setMultiSelect(true);
        sample.setImmediate(true);
        sample.setLeftColumnCaption("Módulos disponíveis");
        sample.setRightColumnCaption("Módulos escolhidos");
        mainPanel.addComponent(sample);

	}

	@Override
	protected boolean validate() {
		System.out.println(sample.getValue());
		return sample.getValue() != null && !((Collection)sample.getValue()).isEmpty();
	}

	@Override
	protected void saveConfiguration() {
		UsuarioEntity u = SecuritySessionProvider.getUsuario();
		Integer contaId = u.getConta().getId();
		List list = getModulosSelecionadosAsList();
		controller.associaModulos(list,contaId);
		
	}

	private List getModulosSelecionadosAsList() {
		Collection modulosSelecitonados = (Collection) sample.getValue();
		List list;
		if (modulosSelecitonados instanceof List){
		  list = (List)modulosSelecitonados;
		}else{
		  list = new ArrayList(modulosSelecitonados);
		}
		return list;
	}

}
