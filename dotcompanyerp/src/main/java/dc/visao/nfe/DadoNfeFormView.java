package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.nfe.DadoNfeFormController;

/**
 * 
 * 
 */

public class DadoNfeFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private Panel panel_3;

	@AutoGenerated
	private VerticalLayout verticalLayout_4;

	@AutoGenerated
	private GridLayout gridLayout_2;

	/**
	 * 
	 */

	@AutoGenerated
	private TextField tfEmailDestinatario;

	@AutoGenerated
	private TextField tfSuframaDestinatario;

	@AutoGenerated
	private TextField tfTelefoneDestinatario;

	@AutoGenerated
	private TextField tfInscricaoEstadualDestinatario;

	@AutoGenerated
	private TextField tfUfDestinatario;

	@AutoGenerated
	private TextField tfCidadeDestinatario;

	@AutoGenerated
	private TextField tfCodigoIbgeDestinatario;

	@AutoGenerated
	private TextField tfBairroLogradouro;

	@AutoGenerated
	private TextField tfLogradouroComplementoDestinatario;

	@AutoGenerated
	private TextField tfLogradouroNumeroDestinatario;

	@AutoGenerated
	private TextField tfLogradouroDestinatario;

	@AutoGenerated
	private TextField tfCepDestinatario;

	@AutoGenerated
	private TextField tfRazaoSocialDestinatario;

	@AutoGenerated
	private TextField tfCpfCnpjDestinatario;

	@AutoGenerated
	private TextField tfIdDestinatario;

	/**
	 * 
	 */

	@AutoGenerated
	private Panel panel_2;

	@AutoGenerated
	private VerticalLayout verticalLayout_3;

	@AutoGenerated
	private GridLayout gridLayout_1;

	/**
	 * 
	 */

	@AutoGenerated
	private TextField tfFormaPagamentoNfeCabecalho;

	@AutoGenerated
	private TextField tfFormatoImpressaoDanfeNfeCabecalho;

	@AutoGenerated
	private TextField tfFinalidadeEmissaoNfeCabecalho;

	@AutoGenerated
	private TextField tfTipoEmissaoNfeCabecalho;

	@AutoGenerated
	private TextField tfTipoOperacaoNfeCabecalho;

	@AutoGenerated
	private TextField tfHoraEntradaSaidaNfeCabecalho;

	@AutoGenerated
	private TextField tfDataEntradaSaidaNfeCabecalho;

	@AutoGenerated
	private TextField tfDataEmissaoNfeCabecalho;

	@AutoGenerated
	private TextField tfNumeroNfeCabecalho;

	@AutoGenerated
	private TextField tfSerieNfeCabecalho;

	@AutoGenerated
	private TextField tfCodigoNumericoNfeCabecalho;

	@AutoGenerated
	private TextField tfDigitoChaveAcessoNfeCabecalho;

	@AutoGenerated
	private TextField tfChaveAcessoNfeCabecalho;

	@AutoGenerated
	private TextField tfNaturezaOperacaoNfeCabecalho;

	@AutoGenerated
	private TextField tfModeloNotaFiscalNfeCabecalho;

	@AutoGenerated
	private TextField tfVendaNfeCabecalho;

	@AutoGenerated
	private TextField tfOperacaoFiscalNfeCabecalho;

	@AutoGenerated
	private TextField tfCodigoOperacaoFiscalNfeCabecalho;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private DadoNfeFormController controller;

	public DadoNfeFormView(DadoNfeFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
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
		// verticalLayout_2.setWidth("100.0%");
		// verticalLayout_2.setHeight("100.0%");
		verticalLayout_2.setMargin(false);
		verticalLayout_2.setSizeFull();

		// panel_2
		panel_2 = buildPanel_2();
		verticalLayout_2.addComponent(panel_2);

		// panel_3
		panel_3 = buildPanel_3();
		verticalLayout_2.addComponent(panel_3);

		return verticalLayout_2;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel("Dados da NF-e");
		panel_2.setImmediate(false);
		panel_2.setWidth("100.0%");
		panel_2.setHeight("100.0%");

		// verticalLayout_3
		verticalLayout_3 = buildVerticalLayout_3();
		panel_2.setContent(verticalLayout_3);

		return panel_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_3() {
		// common part: create layout
		verticalLayout_3 = new VerticalLayout();
		verticalLayout_3.setImmediate(false);
		// verticalLayout_3.setWidth("100.0%");
		// verticalLayout_3.setHeight("100.0%");
		verticalLayout_3.setMargin(false);
		verticalLayout_3.setSizeFull();

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		verticalLayout_3.addComponent(gridLayout_1);

		return verticalLayout_3;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		// gridLayout_1.setWidth("100.0%");
		// gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setColumns(5);
		gridLayout_1.setRows(5);
		gridLayout_1.setSizeFull();

		// tfCodigoOperacaoFiscalNfeCabecalho
		tfCodigoOperacaoFiscalNfeCabecalho = new TextField();
		tfCodigoOperacaoFiscalNfeCabecalho.setCaption("Operação fiscal");
		tfCodigoOperacaoFiscalNfeCabecalho.setImmediate(false);
		tfCodigoOperacaoFiscalNfeCabecalho.setWidth("-1px");
		tfCodigoOperacaoFiscalNfeCabecalho.setHeight("-1px");
		tfCodigoOperacaoFiscalNfeCabecalho.setSizeFull();
		tfCodigoOperacaoFiscalNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfCodigoOperacaoFiscalNfeCabecalho, 0, 0);

		// tfOperacaoFiscalNfeCabecalho
		tfOperacaoFiscalNfeCabecalho = new TextField();
		tfOperacaoFiscalNfeCabecalho.setCaption("Operação fiscal");
		tfOperacaoFiscalNfeCabecalho.setImmediate(false);
		tfOperacaoFiscalNfeCabecalho.setWidth("-1px");
		tfOperacaoFiscalNfeCabecalho.setHeight("-1px");
		tfOperacaoFiscalNfeCabecalho.setSizeFull();
		tfOperacaoFiscalNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfOperacaoFiscalNfeCabecalho, 1, 0);

		// tfVendaNfeCabecalho
		tfVendaNfeCabecalho = new TextField();
		tfVendaNfeCabecalho.setCaption("Venda");
		tfVendaNfeCabecalho.setImmediate(false);
		tfVendaNfeCabecalho.setWidth("-1px");
		tfVendaNfeCabecalho.setHeight("-1px");
		tfVendaNfeCabecalho.setSizeFull();
		tfVendaNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfVendaNfeCabecalho, 2, 0);

		// tfModeloNotaFiscalNfeCabecalho
		tfModeloNotaFiscalNfeCabecalho = new TextField();
		tfModeloNotaFiscalNfeCabecalho.setCaption("Modelo de nota fiscal");
		tfModeloNotaFiscalNfeCabecalho.setImmediate(false);
		tfModeloNotaFiscalNfeCabecalho.setWidth("-1px");
		tfModeloNotaFiscalNfeCabecalho.setHeight("-1px");
		tfModeloNotaFiscalNfeCabecalho.setSizeFull();
		tfModeloNotaFiscalNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfModeloNotaFiscalNfeCabecalho, 3, 0);

		// tfNaturezaOperacaoNfeCabecalho
		tfNaturezaOperacaoNfeCabecalho = new TextField();
		tfNaturezaOperacaoNfeCabecalho.setCaption("Natureza da operação:");
		tfNaturezaOperacaoNfeCabecalho.setImmediate(false);
		tfNaturezaOperacaoNfeCabecalho.setWidth("-1px");
		tfNaturezaOperacaoNfeCabecalho.setHeight("-1px");
		tfNaturezaOperacaoNfeCabecalho.setSizeFull();
		tfNaturezaOperacaoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfNaturezaOperacaoNfeCabecalho, 0, 1);

		// tfChaveAcessoNfeCabecalho
		tfChaveAcessoNfeCabecalho = new TextField();
		tfChaveAcessoNfeCabecalho.setCaption("Chave de acesso:");
		tfChaveAcessoNfeCabecalho.setImmediate(false);
		tfChaveAcessoNfeCabecalho.setWidth("-1px");
		tfChaveAcessoNfeCabecalho.setHeight("-1px");
		tfChaveAcessoNfeCabecalho.setSizeFull();
		tfChaveAcessoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfChaveAcessoNfeCabecalho, 1, 1);

		// tfDigitoChaveAcessoNfeCabecalho
		tfDigitoChaveAcessoNfeCabecalho = new TextField();
		tfDigitoChaveAcessoNfeCabecalho.setCaption("DV:");
		tfDigitoChaveAcessoNfeCabecalho.setImmediate(false);
		tfDigitoChaveAcessoNfeCabecalho.setWidth("-1px");
		tfDigitoChaveAcessoNfeCabecalho.setHeight("-1px");
		tfDigitoChaveAcessoNfeCabecalho.setSizeFull();
		tfDigitoChaveAcessoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfDigitoChaveAcessoNfeCabecalho, 2, 1);

		// tfCodigoNumericoNfeCabecalho
		tfCodigoNumericoNfeCabecalho = new TextField();
		tfCodigoNumericoNfeCabecalho.setCaption("Código numérico:");
		tfCodigoNumericoNfeCabecalho.setImmediate(false);
		tfCodigoNumericoNfeCabecalho.setWidth("-1px");
		tfCodigoNumericoNfeCabecalho.setHeight("-1px");
		tfCodigoNumericoNfeCabecalho.setSizeFull();
		tfCodigoNumericoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfCodigoNumericoNfeCabecalho, 3, 1);

		// tfSerieNfeCabecalho
		tfSerieNfeCabecalho = new TextField();
		tfSerieNfeCabecalho.setCaption("Série:");
		tfSerieNfeCabecalho.setImmediate(false);
		tfSerieNfeCabecalho.setWidth("-1px");
		tfSerieNfeCabecalho.setHeight("-1px");
		tfSerieNfeCabecalho.setSizeFull();
		tfSerieNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfSerieNfeCabecalho, 4, 1);

		// tfNumeroNfeCabecalho
		tfNumeroNfeCabecalho = new TextField();
		tfNumeroNfeCabecalho.setCaption("Número:");
		tfNumeroNfeCabecalho.setImmediate(false);
		tfNumeroNfeCabecalho.setWidth("-1px");
		tfNumeroNfeCabecalho.setHeight("-1px");
		tfNumeroNfeCabecalho.setSizeFull();
		tfNumeroNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfNumeroNfeCabecalho, 0, 2);

		// tfDataEmissaoNfeCabecalho
		tfDataEmissaoNfeCabecalho = new TextField();
		tfDataEmissaoNfeCabecalho.setCaption("Data da emissão:");
		tfDataEmissaoNfeCabecalho.setImmediate(false);
		tfDataEmissaoNfeCabecalho.setWidth("-1px");
		tfDataEmissaoNfeCabecalho.setHeight("-1px");
		tfDataEmissaoNfeCabecalho.setSizeFull();
		tfDataEmissaoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfDataEmissaoNfeCabecalho, 1, 2);

		// tfDataEntradaSaidaNfeCabecalho
		tfDataEntradaSaidaNfeCabecalho = new TextField();
		tfDataEntradaSaidaNfeCabecalho.setCaption("Data (Entrada / saída):");
		tfDataEntradaSaidaNfeCabecalho.setImmediate(false);
		tfDataEntradaSaidaNfeCabecalho.setWidth("-1px");
		tfDataEntradaSaidaNfeCabecalho.setHeight("-1px");
		tfDataEntradaSaidaNfeCabecalho.setSizeFull();
		tfDataEntradaSaidaNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfDataEntradaSaidaNfeCabecalho, 2, 2);

		// tfHoraEntradaSaidaNfeCabecalho
		tfHoraEntradaSaidaNfeCabecalho = new TextField();
		tfHoraEntradaSaidaNfeCabecalho.setCaption("Hora (Entrada / saída):");
		tfHoraEntradaSaidaNfeCabecalho.setImmediate(false);
		tfHoraEntradaSaidaNfeCabecalho.setWidth("-1px");
		tfHoraEntradaSaidaNfeCabecalho.setHeight("-1px");
		tfHoraEntradaSaidaNfeCabecalho.setSizeFull();
		tfHoraEntradaSaidaNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfHoraEntradaSaidaNfeCabecalho, 3, 2);

		// tfTipoOperacaoNfeCabecalho
		tfTipoOperacaoNfeCabecalho = new TextField();
		tfTipoOperacaoNfeCabecalho.setCaption("Tipo de operação:");
		tfTipoOperacaoNfeCabecalho.setImmediate(false);
		tfTipoOperacaoNfeCabecalho.setWidth("-1px");
		tfTipoOperacaoNfeCabecalho.setHeight("-1px");
		tfTipoOperacaoNfeCabecalho.setSizeFull();
		tfTipoOperacaoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfTipoOperacaoNfeCabecalho, 4, 2);

		// tfTipoEmissaoNfeCabecalho
		tfTipoEmissaoNfeCabecalho = new TextField();
		tfTipoEmissaoNfeCabecalho.setCaption("Tipo de emissão:");
		tfTipoEmissaoNfeCabecalho.setImmediate(false);
		tfTipoEmissaoNfeCabecalho.setWidth("-1px");
		tfTipoEmissaoNfeCabecalho.setHeight("-1px");
		tfTipoEmissaoNfeCabecalho.setSizeFull();
		tfTipoEmissaoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfTipoEmissaoNfeCabecalho, 0, 3);

		// tfFinalidadeEmissaoNfeCabecalho
		tfFinalidadeEmissaoNfeCabecalho = new TextField();
		tfFinalidadeEmissaoNfeCabecalho.setCaption("Finalidade de emissão:");
		tfFinalidadeEmissaoNfeCabecalho.setImmediate(false);
		tfFinalidadeEmissaoNfeCabecalho.setWidth("-1px");
		tfFinalidadeEmissaoNfeCabecalho.setHeight("-1px");
		tfFinalidadeEmissaoNfeCabecalho.setSizeFull();
		tfFinalidadeEmissaoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfFinalidadeEmissaoNfeCabecalho, 1, 3);

		// tfFormatoImpressaoDanfeNfeCabecalho
		tfFormatoImpressaoDanfeNfeCabecalho = new TextField();
		tfFormatoImpressaoDanfeNfeCabecalho
				.setCaption("Formato da impressão DANFE:");
		tfFormatoImpressaoDanfeNfeCabecalho.setImmediate(false);
		tfFormatoImpressaoDanfeNfeCabecalho.setWidth("-1px");
		tfFormatoImpressaoDanfeNfeCabecalho.setHeight("-1px");
		tfFormatoImpressaoDanfeNfeCabecalho.setSizeFull();
		tfFormatoImpressaoDanfeNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfFormatoImpressaoDanfeNfeCabecalho, 2, 3);

		// tfFormaPagamentoNfeCabecalho
		tfFormaPagamentoNfeCabecalho = new TextField();
		tfFormaPagamentoNfeCabecalho.setCaption("Forma de pagamento:");
		tfFormaPagamentoNfeCabecalho.setImmediate(false);
		tfFormaPagamentoNfeCabecalho.setWidth("-1px");
		tfFormaPagamentoNfeCabecalho.setHeight("-1px");
		tfFormaPagamentoNfeCabecalho.setSizeFull();
		tfFormaPagamentoNfeCabecalho.setNullRepresentation("");
		gridLayout_1.addComponent(tfFormaPagamentoNfeCabecalho, 3, 3);

		return gridLayout_1;
	}

	@AutoGenerated
	private Panel buildPanel_3() {
		// common part: create layout
		panel_3 = new Panel("Destinatário");
		panel_3.setImmediate(false);
		panel_3.setWidth("100.0%");
		panel_3.setHeight("100.0%");

		// verticalLayout_4
		verticalLayout_4 = buildVerticalLayout_4();
		panel_3.setContent(verticalLayout_4);

		return panel_3;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_4() {
		// common part: create layout
		verticalLayout_4 = new VerticalLayout();
		verticalLayout_4.setImmediate(false);
		// verticalLayout_4.setWidth("100.0%");
		// verticalLayout_4.setHeight("100.0%");
		verticalLayout_4.setMargin(false);
		verticalLayout_4.setSizeFull();

		// gridLayout_2
		gridLayout_2 = buildGridLayout_2();
		verticalLayout_4.addComponent(gridLayout_2);

		return verticalLayout_4;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_2 = new GridLayout();
		gridLayout_2.setImmediate(false);
		gridLayout_2.setWidth("100.0%");
		gridLayout_2.setHeight("100.0%");
		gridLayout_2.setMargin(false);
		gridLayout_2.setSpacing(true);
		gridLayout_2.setColumns(4);
		gridLayout_2.setRows(5);

		// tfIdDestinatario
		tfIdDestinatario = new TextField();
		tfIdDestinatario.setCaption("ID");
		tfIdDestinatario.setImmediate(false);
		tfIdDestinatario.setWidth("-1px");
		tfIdDestinatario.setHeight("-1px");
		tfIdDestinatario.setSizeFull();
		tfIdDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfIdDestinatario, 0, 0);

		// tfCpfCnpjDestinatario
		tfCpfCnpjDestinatario = new TextField();
		tfCpfCnpjDestinatario.setCaption("CPF / CNPJ");
		tfCpfCnpjDestinatario.setImmediate(false);
		tfCpfCnpjDestinatario.setWidth("-1px");
		tfCpfCnpjDestinatario.setHeight("-1px");
		tfCpfCnpjDestinatario.setSizeFull();
		tfCpfCnpjDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfCpfCnpjDestinatario, 1, 0);

		// tfRazaoSocialDestinatario
		tfRazaoSocialDestinatario = new TextField();
		tfRazaoSocialDestinatario.setCaption("Razão social");
		tfRazaoSocialDestinatario.setImmediate(false);
		tfRazaoSocialDestinatario.setWidth("-1px");
		tfRazaoSocialDestinatario.setHeight("-1px");
		tfRazaoSocialDestinatario.setSizeFull();
		tfRazaoSocialDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfRazaoSocialDestinatario, 2, 0);

		// tfCepDestinatario
		tfCepDestinatario = new TextField();
		tfCepDestinatario.setCaption("CEP");
		tfCepDestinatario.setImmediate(false);
		tfCepDestinatario.setWidth("-1px");
		tfCepDestinatario.setHeight("-1px");
		tfCepDestinatario.setSizeFull();
		tfCepDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfCepDestinatario, 0, 1);

		// tfLogradouroDestinatario
		tfLogradouroDestinatario = new TextField();
		tfLogradouroDestinatario.setCaption("Logradouro");
		tfLogradouroDestinatario.setImmediate(false);
		tfLogradouroDestinatario.setWidth("-1px");
		tfLogradouroDestinatario.setHeight("-1px");
		tfLogradouroDestinatario.setSizeFull();
		tfLogradouroDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfLogradouroDestinatario, 1, 1);

		// tfLogradouroNumeroDestinatario
		tfLogradouroNumeroDestinatario = new TextField();
		tfLogradouroNumeroDestinatario.setCaption("Número");
		tfLogradouroNumeroDestinatario.setImmediate(false);
		tfLogradouroNumeroDestinatario.setWidth("-1px");
		tfLogradouroNumeroDestinatario.setHeight("-1px");
		tfLogradouroNumeroDestinatario.setSizeFull();
		tfLogradouroNumeroDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfLogradouroNumeroDestinatario, 2, 1);

		// tfLogradouroComplementoDestinatario
		tfLogradouroComplementoDestinatario = new TextField();
		tfLogradouroComplementoDestinatario.setCaption("Complemento");
		tfLogradouroComplementoDestinatario.setImmediate(false);
		tfLogradouroComplementoDestinatario.setWidth("-1px");
		tfLogradouroComplementoDestinatario.setHeight("-1px");
		tfLogradouroComplementoDestinatario.setSizeFull();
		tfLogradouroComplementoDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfLogradouroComplementoDestinatario, 3, 1);

		// tfBairroLogradouro
		tfBairroLogradouro = new TextField();
		tfBairroLogradouro.setCaption("Bairro");
		tfBairroLogradouro.setImmediate(false);
		tfBairroLogradouro.setWidth("-1px");
		tfBairroLogradouro.setHeight("-1px");
		tfBairroLogradouro.setSizeFull();
		tfBairroLogradouro.setNullRepresentation("");
		gridLayout_2.addComponent(tfBairroLogradouro, 0, 2);

		// tfCodigoIbgeDestinatario
		tfCodigoIbgeDestinatario = new TextField();
		tfCodigoIbgeDestinatario.setCaption("Código IBGE");
		tfCodigoIbgeDestinatario.setImmediate(false);
		tfCodigoIbgeDestinatario.setWidth("-1px");
		tfCodigoIbgeDestinatario.setHeight("-1px");
		tfCodigoIbgeDestinatario.setSizeFull();
		tfCodigoIbgeDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfCodigoIbgeDestinatario, 1, 2);

		// tfCidadeDestinatario
		tfCidadeDestinatario = new TextField();
		tfCidadeDestinatario.setCaption("Cidade");
		tfCidadeDestinatario.setImmediate(false);
		tfCidadeDestinatario.setWidth("-1px");
		tfCidadeDestinatario.setHeight("-1px");
		tfCidadeDestinatario.setSizeFull();
		tfCidadeDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfCidadeDestinatario, 2, 2);

		// tfUfDestinatario
		tfUfDestinatario = new TextField();
		tfUfDestinatario.setCaption("UF");
		tfUfDestinatario.setImmediate(false);
		tfUfDestinatario.setWidth("-1px");
		tfUfDestinatario.setHeight("-1px");
		tfUfDestinatario.setSizeFull();
		tfUfDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfUfDestinatario, 3, 2);

		// tfInscricaoEstadualDestinatario
		tfInscricaoEstadualDestinatario = new TextField();
		tfInscricaoEstadualDestinatario.setCaption("Inscrição estadual");
		tfInscricaoEstadualDestinatario.setImmediate(false);
		tfInscricaoEstadualDestinatario.setWidth("-1px");
		tfInscricaoEstadualDestinatario.setHeight("-1px");
		tfInscricaoEstadualDestinatario.setSizeFull();
		tfInscricaoEstadualDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfInscricaoEstadualDestinatario, 0, 3);

		// tfTelefoneDestinatario
		tfTelefoneDestinatario = new TextField();
		tfTelefoneDestinatario.setCaption("Telefone");
		tfTelefoneDestinatario.setImmediate(false);
		tfTelefoneDestinatario.setWidth("-1px");
		tfTelefoneDestinatario.setHeight("-1px");
		tfTelefoneDestinatario.setSizeFull();
		tfTelefoneDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfTelefoneDestinatario, 1, 3);

		// tfSuframaDestinatario
		tfSuframaDestinatario = new TextField();
		tfSuframaDestinatario.setCaption("SUFRAMA");
		tfSuframaDestinatario.setImmediate(false);
		tfSuframaDestinatario.setWidth("-1px");
		tfSuframaDestinatario.setHeight("-1px");
		tfSuframaDestinatario.setSizeFull();
		tfSuframaDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfSuframaDestinatario, 2, 3);

		// tfEmailDestinatario
		tfEmailDestinatario = new TextField();
		tfEmailDestinatario.setCaption("E-mail");
		tfEmailDestinatario.setImmediate(false);
		tfEmailDestinatario.setWidth("-1px");
		tfEmailDestinatario.setHeight("-1px");
		tfEmailDestinatario.setSizeFull();
		tfEmailDestinatario.setNullRepresentation("");
		gridLayout_2.addComponent(tfEmailDestinatario, 3, 3);

		return gridLayout_2;
	}

}