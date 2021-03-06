package dc.visao.folhapagamento.movimento;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.folhapagamento.movimento.PppExameMedicoFormController;
import dc.entidade.folhapagamento.movimento.PppEntity;

public class PppExameMedicoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private PopupDateField pdfDataUltimo;

	@AutoGenerated
	private TextField tfTipo;

	@AutoGenerated
	private TextField tfNatureza;

	@AutoGenerated
	private TextField tfExame;

	@AutoGenerated
	private TextField tfIndicacaoResultados;

	@AutoGenerated
	private ComboBox cbPpp;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private PppExameMedicoFormController controller;

	public PppExameMedicoFormView(final PppExameMedicoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public PopupDateField getPdfDataUltimo() {
		return pdfDataUltimo;
	}

	public void setPdfDataUltimo(PopupDateField pdfDataUltimo) {
		this.pdfDataUltimo = pdfDataUltimo;
	}

	public TextField getTfTipo() {
		return tfTipo;
	}

	public void setTfTipo(TextField tfTipo) {
		this.tfTipo = tfTipo;
	}

	public TextField getTfNatureza() {
		return tfNatureza;
	}

	public void setTfNatureza(TextField tfNatureza) {
		this.tfNatureza = tfNatureza;
	}

	public TextField getTfExame() {
		return tfExame;
	}

	public void setTfExame(TextField tfExame) {
		this.tfExame = tfExame;
	}

	public TextField getTfIndicacaoResultados() {
		return tfIndicacaoResultados;
	}

	public void setTfIndicacaoResultados(TextField tfIndicacaoResultados) {
		this.tfIndicacaoResultados = tfIndicacaoResultados;
	}

	public ComboBox getCbPpp() {
		return cbPpp;
	}

	public void setCbPpp(ComboBox cbPpp) {
		this.cbPpp = cbPpp;
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

		// pdfDataUltimo
		pdfDataUltimo = new PopupDateField();
		pdfDataUltimo.setCaption("Data último:");
		pdfDataUltimo.setImmediate(false);
		pdfDataUltimo.setWidth("-1px");
		pdfDataUltimo.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataUltimo, 0, 1);

		// tfTipo
		tfTipo = new TextField();
		tfTipo.setCaption("Tipo:");
		tfTipo.setImmediate(false);
		tfTipo.setWidth("175px");
		tfTipo.setHeight("-1px");
		tfTipo.setSizeFull();
		tfTipo.setNullRepresentation("");
		gridLayout_1.addComponent(tfTipo, 1, 1);

		// tfNatureza
		tfNatureza = new TextField();
		tfNatureza.setCaption("Natureza:");
		tfNatureza.setImmediate(false);
		tfNatureza.setWidth("175px");
		tfNatureza.setHeight("-1px");
		tfNatureza.setSizeFull();
		tfNatureza.setNullRepresentation("");
		gridLayout_1.addComponent(tfNatureza, 0, 2);

		// tfTipo
		tfExame = new TextField();
		tfExame.setCaption("Exame:");
		tfExame.setImmediate(false);
		tfExame.setWidth("175px");
		tfExame.setHeight("-1px");
		tfExame.setSizeFull();
		tfExame.setNullRepresentation("");
		gridLayout_1.addComponent(tfExame, 1, 2);

		// tfIndicacaoResultados
		tfIndicacaoResultados = new TextField();
		tfIndicacaoResultados.setCaption("Indicação de resultados:");
		tfIndicacaoResultados.setImmediate(false);
		tfIndicacaoResultados.setWidth("175px");
		tfIndicacaoResultados.setHeight("-1px");
		tfIndicacaoResultados.setSizeFull();
		tfIndicacaoResultados.setNullRepresentation("");
		gridLayout_1.addComponent(tfIndicacaoResultados, 0, 3);

		// cbPpp
		cbPpp = new ComboBox();
		cbPpp.setCaption("PPP");
		cbPpp.setImmediate(false);
		cbPpp.setWidth("-1px");
		cbPpp.setHeight("-1px");
		cbPpp.setRequired(true);
		gridLayout_1.addComponent(cbPpp, 1, 3);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbPpp(List<PppEntity> lista) {
		BeanItemContainer<PppEntity> bic = new BeanItemContainer<PppEntity>(
				PppEntity.class, lista);
		this.cbPpp.setContainerDataSource(bic);
		this.cbPpp.setItemCaptionPropertyId("observacao");
	}

}