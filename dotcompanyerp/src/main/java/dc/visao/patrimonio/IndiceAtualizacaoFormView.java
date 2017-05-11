package dc.visao.patrimonio;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.converter.RunField;
import dc.controller.patrimonio.IndiceAtualizacaoFormController;
import dc.visao.framework.util.ComponentUtil;

/**
 * 
 * 
 */

public class IndiceAtualizacaoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	@RunField(mappedName = "dataIndice")
	private PopupDateField pdfDataIndice;

	@AutoGenerated
	@RunField(mappedName = "valorAlternativo")
	private TextField tfValorAlternativo;

	@AutoGenerated
	@RunField(mappedName = "valor")
	private TextField tfValor;

	@AutoGenerated
	@RunField(mappedName = "nome")
	private TextField tfNome;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private IndiceAtualizacaoFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public IndiceAtualizacaoFormView(
			final IndiceAtualizacaoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public PopupDateField getPdfDataIndice() {
		return pdfDataIndice;
	}

	public void setPdfDataIndice(PopupDateField pdfDataIndice) {
		this.pdfDataIndice = pdfDataIndice;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tfValor) {
		this.tfValor = tfValor;
	}

	public TextField getTfValorAlternativo() {
		return tfValorAlternativo;
	}

	public void setTfValorAlternativo(TextField tfValorAlternativo) {
		this.tfValorAlternativo = tfValorAlternativo;
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

		// panel_1
		panel_1 = buildPanel_1();
		mainLayout.addComponent(panel_1);

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
		panel_1.setHeight("100.0%");

		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		panel_1.setContent(verticalLayout_2);

		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("100.0%");
		verticalLayout_2.setMargin(false);

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		verticalLayout_2.addComponent(gridLayout_1);

		return verticalLayout_2;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		// gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setRows(20);
		gridLayout_1.setColumns(2);

		// tfNome
		tfNome = new TextField();
		tfNome.setCaption("Nome:");
		tfNome.setImmediate(false);
		// tfNome.setWidth("160px");
		tfNome.setHeight("-1px");
		// tfNome.setRequired(true);
		tfNome.setSizeFull();
		tfNome.setNullRepresentation("");
		gridLayout_1.addComponent(tfNome, 0, 1);

		// tfValor
		tfValor = ComponentUtil.buildCurrencyField("Valor:");
		tfValor.setImmediate(false);
		// tfValor.setWidth("160px");
		tfValor.setHeight("-1px");
		// tfValor.setRequired(true);
		tfValor.setSizeFull();
		tfValor.setNullRepresentation("");
		gridLayout_1.addComponent(tfValor, 1, 1);

		// tfValorAlternativo
		tfValorAlternativo = ComponentUtil.buildCurrencyField("Valor alternativo:");
		tfValorAlternativo.setImmediate(false);
		// tfValorAlternativo.setWidth("160px");
		tfValorAlternativo.setHeight("-1px");
		tfValorAlternativo.setSizeFull();
		tfValorAlternativo.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorAlternativo, 0, 2);

		// pdfDataIndice
		pdfDataIndice = new PopupDateField();
		pdfDataIndice.setCaption("Data do índice:");
		pdfDataIndice.setImmediate(false);
		pdfDataIndice.setWidth("160px");
		pdfDataIndice.setHeight("-1px");
		// pdfDataIndice.setRequired(true);
		gridLayout_1.addComponent(pdfDataIndice, 1, 2);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

}