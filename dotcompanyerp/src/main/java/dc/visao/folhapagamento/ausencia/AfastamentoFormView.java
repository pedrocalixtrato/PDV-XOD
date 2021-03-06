package dc.visao.folhapagamento.ausencia;

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

import dc.controller.folhapagamento.ausencia.AfastamentoFormController;
import dc.entidade.folhapagamento.ausencia.TipoAfastamentoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;

public class AfastamentoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private ComboBox cbTipoAfastamento;

	@AutoGenerated
	private ComboBox cbColaborador;

	@AutoGenerated
	private TextField tfDiasAfastado;

	@AutoGenerated
	private PopupDateField pdfDataFim;

	@AutoGenerated
	private PopupDateField pdfDataInicio;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private AfastamentoFormController controller;

	public AfastamentoFormView(final AfastamentoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public PopupDateField getPdfDataInicio() {
		return pdfDataInicio;
	}

	public void setPdfDataInicio(PopupDateField pdfDataInicio) {
		this.pdfDataInicio = pdfDataInicio;
	}

	public PopupDateField getPdfDataFim() {
		return pdfDataFim;
	}

	public void setPdfDataFim(PopupDateField pdfDataFim) {
		this.pdfDataFim = pdfDataFim;
	}

	public TextField getTfDiasAfastado() {
		return tfDiasAfastado;
	}

	public void setTfDiasAfastado(TextField tfDiasAfastado) {
		this.tfDiasAfastado = tfDiasAfastado;
	}

	public ComboBox getCbColaborador() {
		return cbColaborador;
	}

	public void setCbColaborador(ComboBox cbColaborador) {
		this.cbColaborador = cbColaborador;
	}

	public ComboBox getCbTipoAfastamento() {
		return cbTipoAfastamento;
	}

	public void setCbTipoAfastamento(ComboBox cbTipoAfastamento) {
		this.cbTipoAfastamento = cbTipoAfastamento;
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
		// panel_1.setHeight("100.0%");

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

		// pdfDataInicio
		pdfDataInicio = new PopupDateField();
		pdfDataInicio.setCaption("Data inicio:");
		pdfDataInicio.setImmediate(false);
		pdfDataInicio.setWidth("-1px");
		pdfDataInicio.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataInicio, 0, 1);

		// pdfDataFim
		pdfDataFim = new PopupDateField();
		pdfDataFim.setCaption("Data termino:");
		pdfDataFim.setImmediate(false);
		pdfDataFim.setWidth("-1px");
		pdfDataFim.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataFim, 1, 1);

		// tfDiasAfastado
		tfDiasAfastado = new TextField();
		tfDiasAfastado.setCaption("Dias de afastamento:");
		tfDiasAfastado.setImmediate(false);
		tfDiasAfastado.setWidth("175px");
		tfDiasAfastado.setHeight("-1px");
		tfDiasAfastado.setSizeFull();
		tfDiasAfastado.setNullRepresentation("");
		gridLayout_1.addComponent(tfDiasAfastado, 0, 2);

		// cbColaborador
		cbColaborador = new ComboBox();
		cbColaborador.setCaption("Colaborador:");
		cbColaborador.setImmediate(false);
		cbColaborador.setWidth("160px");
		cbColaborador.setHeight("-1px");
		cbColaborador.setRequired(true);
		gridLayout_1.addComponent(cbColaborador, 1, 2);

		// cbTipoAfastamento
		cbTipoAfastamento = new ComboBox();
		cbTipoAfastamento.setCaption("Tipo de afastamento:");
		cbTipoAfastamento.setImmediate(false);
		cbTipoAfastamento.setWidth("160px");
		cbTipoAfastamento.setHeight("-1px");
		cbTipoAfastamento.setRequired(true);
		gridLayout_1.addComponent(cbTipoAfastamento, 0, 3);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbColaborador(List<ColaboradorEntity> lista) {
		BeanItemContainer<ColaboradorEntity> bic = new BeanItemContainer<ColaboradorEntity>(
				ColaboradorEntity.class, lista);
		this.cbColaborador.setContainerDataSource(bic);
		this.cbColaborador.setItemCaptionPropertyId("nome");
	}

	public void carregarCmbTipoAfastamento(List<TipoAfastamentoEntity> lista) {
		BeanItemContainer<TipoAfastamentoEntity> bic = new BeanItemContainer<TipoAfastamentoEntity>(
				TipoAfastamentoEntity.class, lista);
		this.cbTipoAfastamento.setContainerDataSource(bic);
		this.cbTipoAfastamento.setItemCaptionPropertyId("nome");
	}

}