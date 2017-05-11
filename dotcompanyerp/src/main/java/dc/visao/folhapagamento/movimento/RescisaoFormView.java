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

import dc.controller.folhapagamento.movimento.RescisaoFormController;
import dc.entidade.geral.pessoal.ColaboradorEntity;

public class RescisaoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private PopupDateField pdfDataDemissao;

	@AutoGenerated
	private PopupDateField pdfDataPagamento;

	@AutoGenerated
	private TextField tfMotivo;

	@AutoGenerated
	private PopupDateField pdfDataAvisoPrevio;

	@AutoGenerated
	private TextField tfDiasAvisoPrevio;

	@AutoGenerated
	private TextField tfComprovouNovoEmprego;

	@AutoGenerated
	private TextField tfDispensouEmpregado;

	@AutoGenerated
	private TextField tfPensaoAlimenticia;

	@AutoGenerated
	private TextField tfPensaoAlimenticiaFgts;

	@AutoGenerated
	private TextField tfFgtsValorRescisao;

	@AutoGenerated
	private TextField tfFgtsSaldoBanco;

	@AutoGenerated
	private TextField tfFgtsComplementoSaldo;

	@AutoGenerated
	private TextField tfFgtsCodigoAfastamento;

	@AutoGenerated
	private TextField tfFgtsCodigoSaque;

	@AutoGenerated
	private ComboBox cbColaborador;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private RescisaoFormController controller;

	public RescisaoFormView(final RescisaoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public PopupDateField getPdfDataDemissao() {
		return pdfDataDemissao;
	}

	public void setPdfDataDemissao(PopupDateField pdfDataDemissao) {
		this.pdfDataDemissao = pdfDataDemissao;
	}

	public PopupDateField getPdfDataPagamento() {
		return pdfDataPagamento;
	}

	public void setPdfDataPagamento(PopupDateField pdfDataPagamento) {
		this.pdfDataPagamento = pdfDataPagamento;
	}

	public TextField getTfMotivo() {
		return tfMotivo;
	}

	public void setTfMotivo(TextField tfMotivo) {
		this.tfMotivo = tfMotivo;
	}

	public PopupDateField getPdfDataAvisoPrevio() {
		return pdfDataAvisoPrevio;
	}

	public void setPdfDataAvisoPrevio(PopupDateField pdfDataAvisoPrevio) {
		this.pdfDataAvisoPrevio = pdfDataAvisoPrevio;
	}

	public TextField getTfDiasAvisoPrevio() {
		return tfDiasAvisoPrevio;
	}

	public void setTfDiasAvisoPrevio(TextField tfDiasAvisoPrevio) {
		this.tfDiasAvisoPrevio = tfDiasAvisoPrevio;
	}

	public TextField getTfComprovouNovoEmprego() {
		return tfComprovouNovoEmprego;
	}

	public void setTfComprovouNovoEmprego(TextField tfComprovouNovoEmprego) {
		this.tfComprovouNovoEmprego = tfComprovouNovoEmprego;
	}

	public TextField getTfDispensouEmpregado() {
		return tfDispensouEmpregado;
	}

	public void setTfDispensouEmpregado(TextField tfDispensouEmpregado) {
		this.tfDispensouEmpregado = tfDispensouEmpregado;
	}

	public TextField getTfPensaoAlimenticia() {
		return tfPensaoAlimenticia;
	}

	public void setTfPensaoAlimenticia(TextField tfPensaoAlimenticia) {
		this.tfPensaoAlimenticia = tfPensaoAlimenticia;
	}

	public TextField getTfPensaoAlimenticiaFgts() {
		return tfPensaoAlimenticiaFgts;
	}

	public void setTfPensaoAlimenticiaFgts(TextField tfPensaoAlimenticiaFgts) {
		this.tfPensaoAlimenticiaFgts = tfPensaoAlimenticiaFgts;
	}

	public TextField getTfFgtsValorRescisao() {
		return tfFgtsValorRescisao;
	}

	public void setTfFgtsValorRescisao(TextField tfFgtsValorRescisao) {
		this.tfFgtsValorRescisao = tfFgtsValorRescisao;
	}

	public TextField getTfFgtsSaldoBanco() {
		return tfFgtsSaldoBanco;
	}

	public void setTfFgtsSaldoBanco(TextField tfFgtsSaldoBanco) {
		this.tfFgtsSaldoBanco = tfFgtsSaldoBanco;
	}

	public TextField getTfFgtsComplementoSaldo() {
		return tfFgtsComplementoSaldo;
	}

	public void setTfFgtsComplementoSaldo(TextField tfFgtsComplementoSaldo) {
		this.tfFgtsComplementoSaldo = tfFgtsComplementoSaldo;
	}

	public TextField getTfFgtsCodigoAfastamento() {
		return tfFgtsCodigoAfastamento;
	}

	public void setTfFgtsCodigoAfastamento(TextField tfFgtsCodigoAfastamento) {
		this.tfFgtsCodigoAfastamento = tfFgtsCodigoAfastamento;
	}

	public TextField getTfFgtsCodigoSaque() {
		return tfFgtsCodigoSaque;
	}

	public void setTfFgtsCodigoSaque(TextField tfFgtsCodigoSaque) {
		this.tfFgtsCodigoSaque = tfFgtsCodigoSaque;
	}

	public ComboBox getCbColaborador() {
		return cbColaborador;
	}

	public void setCbColaborador(ComboBox cbColaborador) {
		this.cbColaborador = cbColaborador;
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

		// pdfDataDemissao
		pdfDataDemissao = new PopupDateField();
		pdfDataDemissao.setCaption("Data da demissão:");
		pdfDataDemissao.setImmediate(false);
		pdfDataDemissao.setWidth("-1px");
		pdfDataDemissao.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataDemissao, 0, 1);

		// pdfDataPagamento
		pdfDataPagamento = new PopupDateField();
		pdfDataPagamento.setCaption("Data do pagamento:");
		pdfDataPagamento.setImmediate(false);
		pdfDataPagamento.setWidth("-1px");
		pdfDataPagamento.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataPagamento, 1, 1);

		// tfMotivo
		tfMotivo = new TextField();
		tfMotivo.setCaption("Motivo:");
		tfMotivo.setImmediate(false);
		tfMotivo.setWidth("175px");
		tfMotivo.setHeight("-1px");
		// tfMotivo.setRequired(true);
		tfMotivo.setSizeFull();
		tfMotivo.setNullRepresentation("");
		gridLayout_1.addComponent(tfMotivo, 0, 2);

		// pdfDataAvisoPrevio
		pdfDataAvisoPrevio = new PopupDateField();
		pdfDataAvisoPrevio.setCaption("Data do aviso prévio:");
		pdfDataAvisoPrevio.setImmediate(false);
		pdfDataAvisoPrevio.setWidth("-1px");
		pdfDataAvisoPrevio.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataAvisoPrevio, 1, 2);

		// tfDiasAvisoPrevio
		tfDiasAvisoPrevio = new TextField();
		tfDiasAvisoPrevio.setCaption("Dias do aviso prévio:");
		tfDiasAvisoPrevio.setImmediate(false);
		tfDiasAvisoPrevio.setWidth("175px");
		tfDiasAvisoPrevio.setHeight("-1px");
		tfDiasAvisoPrevio.setSizeFull();
		tfDiasAvisoPrevio.setNullRepresentation("");
		gridLayout_1.addComponent(tfDiasAvisoPrevio, 0, 3);

		// tfComprovouNovoEmprego
		tfComprovouNovoEmprego = new TextField();
		tfComprovouNovoEmprego.setCaption("Conprovou novo emprego:");
		tfComprovouNovoEmprego.setImmediate(false);
		tfComprovouNovoEmprego.setWidth("175px");
		tfComprovouNovoEmprego.setHeight("-1px");
		tfComprovouNovoEmprego.setSizeFull();
		tfComprovouNovoEmprego.setNullRepresentation("");
		gridLayout_1.addComponent(tfComprovouNovoEmprego, 1, 3);

		// tfDispensouEmpregado
		tfDispensouEmpregado = new TextField();
		tfDispensouEmpregado.setCaption("Dispensou o empregado:");
		tfDispensouEmpregado.setImmediate(false);
		tfDispensouEmpregado.setWidth("175px");
		tfDispensouEmpregado.setHeight("-1px");
		tfDispensouEmpregado.setSizeFull();
		tfDispensouEmpregado.setNullRepresentation("");
		gridLayout_1.addComponent(tfDispensouEmpregado, 0, 4);

		// tfPensaoAlimenticia
		tfPensaoAlimenticia = new TextField();
		tfPensaoAlimenticia.setCaption("Pensão alimentícia:");
		tfPensaoAlimenticia.setImmediate(false);
		tfPensaoAlimenticia.setWidth("-1px");
		tfPensaoAlimenticia.setHeight("-1px");
		tfPensaoAlimenticia.setSizeFull();
		tfPensaoAlimenticia.setNullRepresentation("");
		gridLayout_1.addComponent(tfPensaoAlimenticia, 1, 4);

		// tfPensaoAlimenticiaFgts
		tfPensaoAlimenticiaFgts = new TextField();
		tfPensaoAlimenticiaFgts.setCaption("Pensão alimentícia FGTS:");
		tfPensaoAlimenticiaFgts.setImmediate(false);
		tfPensaoAlimenticiaFgts.setWidth("-1px");
		tfPensaoAlimenticiaFgts.setHeight("-1px");
		tfPensaoAlimenticiaFgts.setSizeFull();
		tfPensaoAlimenticiaFgts.setNullRepresentation("");
		gridLayout_1.addComponent(tfPensaoAlimenticiaFgts, 0, 5);

		// tfFgtsValorRescisao
		tfFgtsValorRescisao = new TextField();
		tfFgtsValorRescisao.setCaption("FGTS valor da rescisão:");
		tfFgtsValorRescisao.setImmediate(false);
		tfFgtsValorRescisao.setWidth("-1px");
		tfFgtsValorRescisao.setHeight("-1px");
		tfFgtsValorRescisao.setSizeFull();
		tfFgtsValorRescisao.setNullRepresentation("");
		gridLayout_1.addComponent(tfFgtsValorRescisao, 1, 5);

		// tfFgtsSaldoBanco
		tfFgtsSaldoBanco = new TextField();
		tfFgtsSaldoBanco.setCaption("FGTS saldo do banco:");
		tfFgtsSaldoBanco.setImmediate(false);
		tfFgtsSaldoBanco.setWidth("-1px");
		tfFgtsSaldoBanco.setHeight("-1px");
		tfFgtsSaldoBanco.setSizeFull();
		tfFgtsSaldoBanco.setNullRepresentation("");
		gridLayout_1.addComponent(tfFgtsSaldoBanco, 0, 6);

		// tfFgtsComplementoSaldo
		tfFgtsComplementoSaldo = new TextField();
		tfFgtsComplementoSaldo.setCaption("FGTS complemento do saldo:");
		tfFgtsComplementoSaldo.setImmediate(false);
		tfFgtsComplementoSaldo.setWidth("-1px");
		tfFgtsComplementoSaldo.setHeight("-1px");
		tfFgtsComplementoSaldo.setSizeFull();
		tfFgtsComplementoSaldo.setNullRepresentation("");
		gridLayout_1.addComponent(tfFgtsComplementoSaldo, 1, 6);

		// tfFgtsCodigoAfastamento
		tfFgtsCodigoAfastamento = new TextField();
		tfFgtsCodigoAfastamento.setCaption("FGTS código do afastamento:");
		tfFgtsCodigoAfastamento.setImmediate(false);
		tfFgtsCodigoAfastamento.setWidth("-1px");
		tfFgtsCodigoAfastamento.setHeight("-1px");
		tfFgtsCodigoAfastamento.setSizeFull();
		tfFgtsCodigoAfastamento.setNullRepresentation("");
		gridLayout_1.addComponent(tfFgtsCodigoAfastamento, 0, 7);

		// tfFgtsCodigoSaque
		tfFgtsCodigoSaque = new TextField();
		tfFgtsCodigoSaque.setCaption("FGTS código do saque:");
		tfFgtsCodigoSaque.setImmediate(false);
		tfFgtsCodigoSaque.setWidth("-1px");
		tfFgtsCodigoSaque.setHeight("-1px");
		tfFgtsCodigoSaque.setSizeFull();
		tfFgtsCodigoSaque.setNullRepresentation("");
		gridLayout_1.addComponent(tfFgtsCodigoSaque, 1, 7);

		// cbColaborador
		cbColaborador = new ComboBox();
		cbColaborador.setCaption("Colaborador:");
		cbColaborador.setImmediate(false);
		cbColaborador.setWidth("137px");
		cbColaborador.setHeight("-1px");
		cbColaborador.setRequired(true);
		gridLayout_1.addComponent(cbColaborador, 0, 8);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbColaborador(List<ColaboradorEntity> lista) {
		BeanItemContainer<ColaboradorEntity> bic = new BeanItemContainer<ColaboradorEntity>(
				ColaboradorEntity.class, lista);
		this.cbColaborador.setContainerDataSource(bic);
		this.cbColaborador.setItemCaptionPropertyId("matricula");
	}

}