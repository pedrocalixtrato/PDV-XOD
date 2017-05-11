package dc.visao.patrimonio;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.converter.RunField;
import dc.controller.patrimonio.ApoliceSeguroFormController;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.patrimonio.SeguradoraEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

/**
 * 
 * 
 */

public class ApoliceSeguroFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	@RunField(mappedName = "imagem")
	private TextField tfImagem;

	@AutoGenerated
	@RunField(mappedName = "observacao")
	private TextField tfObservacao;

	@AutoGenerated
	@RunField(mappedName = "valorSegurado")
	private TextField tfValorSegurado;

	@AutoGenerated
	@RunField(mappedName = "valorPremio")
	private TextField tfValorPremio;

	@AutoGenerated
	@RunField(mappedName = "dataVencimento")
	private PopupDateField pdfDataVencimento;

	@AutoGenerated
	@RunField(mappedName = "dataContratacao")
	private PopupDateField pdfDataContratacao;

	@AutoGenerated
	@RunField(mappedName = "numero")
	private TextField tfNumero;

	@AutoGenerated
	@RunField(mappedName = "seguradora")
	private ManyToOneCombo<SeguradoraEntity> cbSeguradora;

	@AutoGenerated
	@RunField(mappedName = "bem")
	private ManyToOneCombo<BemEntity> cbBem;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private ApoliceSeguroFormController controller;

	public ApoliceSeguroFormView(final ApoliceSeguroFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public PopupDateField getPdfDataContratacao() {
		return pdfDataContratacao;
	}

	public void setPdfDataContratacao(PopupDateField pdfDataContratacao) {
		this.pdfDataContratacao = pdfDataContratacao;
	}

	public PopupDateField getPdfDataVencimento() {
		return pdfDataVencimento;
	}

	public void setPdfDataVencimento(PopupDateField pdfDataVencimento) {
		this.pdfDataVencimento = pdfDataVencimento;
	}

	public TextField getTfValorPremio() {
		return tfValorPremio;
	}

	public void setTfValorPremio(TextField tfValorPremio) {
		this.tfValorPremio = tfValorPremio;
	}

	public TextField getTfValorSegurado() {
		return tfValorSegurado;
	}

	public void setTfValorSegurado(TextField tfValorSegurado) {
		this.tfValorSegurado = tfValorSegurado;
	}

	public TextField getTfObservacao() {
		return tfObservacao;
	}

	public void setTfObservacao(TextField tfObservacao) {
		this.tfObservacao = tfObservacao;
	}

	public TextField getTfImagem() {
		return tfImagem;
	}

	public void setTfImagem(TextField tfImagem) {
		this.tfImagem = tfImagem;
	}

	public ManyToOneCombo<BemEntity> getCbBem() {
		return cbBem;
	}

	public void setCbBem(ManyToOneCombo<BemEntity> cbBem) {
		this.cbBem = cbBem;
	}

	public ManyToOneCombo<SeguradoraEntity> getCbSeguradora() {
		return cbSeguradora;
	}

	public void setCbSeguradora(ManyToOneCombo<SeguradoraEntity> cbSeguradora) {
		this.cbSeguradora = cbSeguradora;
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

		// tfNumero
		tfNumero = new TextField();
		tfNumero.setCaption("Número:");
		tfNumero.setImmediate(false);
		tfNumero.setWidth("175px");
		tfNumero.setHeight("-1px");
		// tfNumero.setRequired(true);
		tfNumero.setSizeFull();
		// tfNumero.setNullRepresentation("");
		tfNumero.setNullRepresentation("");
		gridLayout_1.addComponent(tfNumero, 0, 1);

		// pdfDataContratacao
		pdfDataContratacao = new PopupDateField();
		pdfDataContratacao.setCaption("Data de contratação:");
		pdfDataContratacao.setImmediate(false);
		pdfDataContratacao.setWidth("-1px");
		pdfDataContratacao.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataContratacao, 0, 2);

		// pdfDataVencimento
		pdfDataVencimento = new PopupDateField();
		pdfDataVencimento.setCaption("Data de vencimento:");
		pdfDataVencimento.setImmediate(false);
		pdfDataVencimento.setWidth("-1px");
		pdfDataVencimento.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataVencimento, 1, 2);

		// tfValorPremio
		tfValorPremio = ComponentUtil.buildCurrencyField("Valor do prêmio:");
		tfValorPremio.setImmediate(false);
		tfValorPremio.setWidth("175px");
		tfValorPremio.setHeight("-1px");
		tfValorPremio.setSizeFull();
		// tfValorPremio.setNullRepresentation("");
		tfValorPremio.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorPremio, 0, 3);

		// tfValorSegurado
		tfValorSegurado = ComponentUtil.buildCurrencyField("Valor segurado:");
		tfValorSegurado.setImmediate(false);
		tfValorSegurado.setWidth("175px");
		tfValorSegurado.setHeight("-1px");
		tfValorSegurado.setSizeFull();
		// tfValorSegurado.setNullRepresentation("");
		tfValorSegurado.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorSegurado, 1, 3);

		// tfObservacao
		tfObservacao = new TextField();
		tfObservacao.setCaption("Observação:");
		tfObservacao.setImmediate(false);
		tfObservacao.setWidth("175px");
		tfObservacao.setHeight("-1px");
		tfObservacao.setSizeFull();
		// tfObservacao.setNullRepresentation("");
		tfObservacao.setNullRepresentation("");
		gridLayout_1.addComponent(tfObservacao, 0, 4);

		// tfImagem
		tfImagem = new TextField();
		tfImagem.setCaption("Imagem:");
		tfImagem.setImmediate(false);
		tfImagem.setWidth("-1px");
		tfImagem.setHeight("-1px");
		tfImagem.setSizeFull();
		// tfImagem.setNullRepresentation("");
		tfImagem.setNullRepresentation("");
		gridLayout_1.addComponent(tfImagem, 1, 4);

		// cbBem
		cbBem = new ManyToOneCombo<>();
		cbBem.setCaption("Bem:");
		cbBem.setImmediate(false);
		cbBem.setWidth("175px");
		cbBem.setHeight("-1px");
		cbBem.setSizeFull();
		gridLayout_1.addComponent(cbBem, 0, 5);

		// cbSeguradora
		cbSeguradora = new ManyToOneCombo<>();
		cbSeguradora.setCaption("Seguradora:");
		cbSeguradora.setImmediate(false);
		cbSeguradora.setWidth("137px");
		cbSeguradora.setHeight("-1px");
		cbSeguradora.setSizeFull();
		gridLayout_1.addComponent(cbSeguradora, 1, 5);

		return gridLayout_1;
	}

}