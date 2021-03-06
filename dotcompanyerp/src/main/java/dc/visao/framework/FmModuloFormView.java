package dc.visao.framework;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Tela (DESIGN), onde criamos o campos que a Tela contém
 * tudo que ela precisa ter
 *
*/

public class FmModuloFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TextField txtViewName;


	@AutoGenerated
	private TextField txtURL;
	@AutoGenerated
	private TextField txtCaption;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FmModuloFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	public TextField getTxtURL() {
		return txtURL;
	}

	public void setTxtURL(TextField txtURL) {
		this.txtURL = txtURL;
	}

	public TextField getTxtCaption() {
		return txtCaption;
	}

	public TextField getTxtViewName() {
		return txtViewName;
	}
	

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// txtCaption
		txtCaption = new TextField();
		txtCaption.setCaption("Caption:");
		txtCaption.setImmediate(false);
		txtCaption.setWidth("-1px");
		txtCaption.setHeight("-1px");
		mainLayout.addComponent(txtCaption);
		
		// txtURL
		txtURL = new TextField();
		txtURL.setCaption("Identificador URL:");
		txtURL.setImmediate(false);
		txtURL.setWidth("-1px");
		txtURL.setHeight("24px");
		mainLayout.addComponent(txtURL);
		
		// txtViewName
		txtViewName = new TextField();
		txtViewName.setCaption("Classe da View:");
		txtViewName.setImmediate(false);
		txtViewName.setWidth("-1px");
		txtViewName.setHeight("-1px");
		mainLayout.addComponent(txtViewName);
		
		return mainLayout;
	}

}
