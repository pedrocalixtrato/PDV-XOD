package dc.visao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import dc.framework.DcConstants;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a configuração da Tela, todos os
 *         controllers. Essa é a classe principal. Temos a configuração dos
 *         botões SALVAR, CRIAR Tem o Método também do CARREGAR, que pega as
 *         informações contida na Tela, que está salvo no Banco de Dados
 * 
 */

public abstract class BlankFormController extends ControllerTask implements Controller, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6944317085399570143L;

	BlankFormView view;

	private Type beanType;

	@Autowired
	private MainController mainController;

	private Map<String, AbstractComponent> validatableComponents;

	private boolean novo;

	private boolean active;

	@PostConstruct
	public void init() {
		validatableComponents = new HashMap<String, AbstractComponent>();

		initSubView();
		// Configura Titulo
		view = new BlankFormView();
		// view.getLblNome().setContentMode(ContentMode.HTML);
		view.getLblNome().setValue(getNome());

		// Configura Conteudo

		Layout panel = view.getPnlContent();

		if (isFullSized()) {
			panel.setHeight("100%");
		}
		panel.addComponent(getSubView());

		/*
		 * Panel p = view.getPnlContent(); if(isFullSized()){
		 * view.setHeight("100%"); p.setHeight("100%"); }
		 * p.setContent(getSubView());
		 */

	}

	protected boolean isFullSized() {
		return false;
	}

	/**
	 * Utilizado para limpar validacoes nos componentes VAADIN
	 */
	protected void limpaValidacoes() {
		Iterator<String> it = validatableComponents.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			AbstractComponent c = validatableComponents.get(key);
			c.setComponentError(null);
		}

	}

	public Type getBeanType() {
		return this.beanType;
	}

	protected abstract void initSubView();

	public void mensagemSalvoOK() {
		new Notification("Gravado!", "Registro gravado com sucesso", Notification.TYPE_HUMANIZED_MESSAGE, true)
				.show(Page.getCurrent());
	}

	protected abstract Component getSubView();

	protected abstract String getNome();

	public Component getContent() {
		return view;
	}

	public void mensagemRemovidoOK() {
		new Notification(DcConstants.DELETE_TITLE_OK, DcConstants.DELETE_OK, Notification.TYPE_HUMANIZED_MESSAGE, true)
				.show(Page.getCurrent());
	}

	public void mensagemErro(String message) {
		new Notification(DcConstants.ERROR_TITLE, message, Notification.TYPE_ERROR_MESSAGE, true).show(Page
				.getCurrent());
	}

	public void mensagemAtencao(String message) {
		new Notification(DcConstants.CAUTION_PLEASE, message, Notification.TYPE_WARNING_MESSAGE, true).show(Page
				.getCurrent());
	}

	public View getView() {
		return view;
	}

	protected void adicionarErroDeValidacao(AbstractComponent c, String errorMessage) {
		validatableComponents.put(c.getId(), c);
		c.setComponentError(new UserError(errorMessage));
	}

	protected boolean estaVazio(TextField txtField) {
		if (txtField.getValue() == null || txtField.getValue().isEmpty()) {
			adicionarErroDeValidacao(txtField, "Não pode ficar em Branco!");
			return true;
		}

		return false;
	}

	public boolean isNovo() {
		return novo;
	}

	@Override
	public Controller getController() {
		return this;
	}

	public void setReadOnly(boolean readonly) {

	}

	@Override
	public String getControllerTitle() {
		return this.getNome();
	}
	
	@Override
	public void dispose(){
		
	}
	

	@Override
	public void setChildModuleID(String id){
		//nothinf for now
	}
}
