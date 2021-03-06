package dc.visao.financeiro;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.addons.maskedtextfield.NumericField;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.TipoVencimentoEn;
import dc.controller.financeiro.LancamentoReceberFormController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.LctoReceberNtFinanceiraEntity;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;


public class LancamentoReceberFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GridLayout fields;

	private VerticalLayout mainLayout;

	private ManyToOneComboField<DocumentoOrigem> cbDocumentoOrigem;
	private ManyToOneComboField<ContaCaixa> cbContaCaixa;
	private ManyToOneComboField<ClienteEntity> cbCliente;

	private PopupDateField dtLancamento;
	private PopupDateField dtPrimeiroVencimento;

	private TextField txNumeroDocumento;
	private TextField txValorTotal;
	private TextField txValorReceber;
	private TextField txTaxaComissao;
	private TextField txValorComissao;
	private NumericField txQuantidadeParcela;
	private NumericField txIntervaloParcela;

	private TabSheet tabSheet;

	private ComboBox cbTipoVencimento;

	private SubFormComponent<ParcelaReceber, Integer> parcelasSubForm;
	private SubFormComponent<LctoReceberNtFinanceiraEntity, Integer> naturezaFinanceiraSubForm;
	//private SubFormComponent<CentroResultado, Integer> centroResultadoSubForm;
	private final LancamentoReceberFormController controller;
	private Button btnGerarParcelas;
	private Button btnGerarBoleto;
	private VerticalLayout parcelasLayout;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public LancamentoReceberFormView(LancamentoReceberFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = controller;
	}

	private VerticalLayout buildMainLayout() {

		// common part: create layout
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		// setHeight("100.0%");

		fields = buildFields();

		mainLayout.addComponent(fields);

		tabSheet = BuildTabs();
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1);

		return mainLayout;
	}
	
	public LancamentoReceberFormController getController() {
		return controller;
	}

	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(8);
		fields.setColumns(8);

		cbCliente = new ManyToOneComboField<>(ClienteEntity.class);
		fields.addComponent(cbCliente, 0, 0, 1, 0);
		cbCliente.setCaption("Cliente");

		cbDocumentoOrigem = new ManyToOneComboField<>(DocumentoOrigem.class);
		cbDocumentoOrigem.setCaption("Documento Origem");
		fields.addComponent(cbDocumentoOrigem, 0, 1, 1, 1);

		txNumeroDocumento = ComponentUtil.buildTextField("Número Documento");
		fields.addComponent(txNumeroDocumento, 2, 1);

		dtLancamento = ComponentUtil.buildPopupDateField("Data Lançamento");
		fields.addComponent(dtLancamento, 0, 2);

		txValorTotal = ComponentUtil.buildCurrencysField("Valor Total");
		fields.addComponent(txValorTotal, 1, 2);

		txValorReceber = ComponentUtil.buildCurrencysField("Valor à Receber");
		fields.addComponent(txValorReceber, 2, 2);

		txTaxaComissao = ComponentUtil.buildPercentageField("Taxa Comissão");
		fields.addComponent(txTaxaComissao, 3, 2);

		txValorComissao = ComponentUtil.buildCurrencysField("Valor Comissão");
		fields.addComponent(txValorComissao, 4, 2);
		
		/*txTaxaJuro = ComponentUtil.buildPercentageField("Taxa Juro");
		fields.addComponent(txTaxaJuro, 5, 2);
		
		txValorJuro = ComponentUtil.buildCurrencyField("Valor Juro");
		fields.addComponent(txValorJuro, 6, 2);*/

		dtPrimeiroVencimento = ComponentUtil
				.buildPopupDateField("Primeiro Vencimento");
		fields.addComponent(dtPrimeiroVencimento, 0, 3);

		txQuantidadeParcela = ComponentUtil
				.buildNumericField("Quantidade Parcelas");
		fields.addComponent(txQuantidadeParcela, 1, 3);
		txQuantidadeParcela.setConverter(new IntegerConverter());

		cbTipoVencimento = ComponentUtil.buildComboBox("Tipo Vencimento");
		fields.addComponent(cbTipoVencimento, 2, 3);

		txIntervaloParcela = ComponentUtil
				.buildNumericField("Intervalos Parcelas");
		fields.addComponent(txIntervaloParcela, 3, 3);
		txIntervaloParcela.setConverter(new IntegerConverter());
		
		/*txTaxaMulta = ComponentUtil.buildPercentageField("Taxa Multa");
		fields.addComponent(txTaxaMulta, 4, 3);
		
		txValorMulta = ComponentUtil.buildCurrencyField("Valor Multa");
		fields.addComponent(txValorMulta, 5, 3);
		
		txTaxaDesconto = ComponentUtil.buildPercentageField("Taxa Desconto");
		fields.addComponent(txTaxaDesconto, 6, 3);
		
		txValorDesconto = ComponentUtil.buildCurrencyField("Valor Desconto");
		fields.addComponent(txValorDesconto, 7, 3);*/

		cbContaCaixa = new ManyToOneComboField<>(ContaCaixa.class);
		cbContaCaixa.setCaption("Conta Caixa");
		fields.addComponent(cbContaCaixa, 0, 4, 1, 4);

		btnGerarParcelas = new Button("Gerar Parcelas");
		fields.addComponent(btnGerarParcelas, 0, 5);

		btnGerarBoleto = new Button("Gerar Boletos");
		fields.addComponent(btnGerarBoleto, 1, 5);

		for (TipoVencimentoEn en : TipoVencimentoEn.values()) {
			cbTipoVencimento.addItem(en);
		}

		return fields;
	}

	private TabSheet BuildTabs() {
		tabSheet = new TabSheet();
		tabSheet.setImmediate(true);
		tabSheet.setSizeFull();

		tabSheet.addTab(buildSubFormParcelas(), "Parcelas", null);
		tabSheet.addTab(buildSubFormNaturezaFinanceira(),"Naturezas Financeiras Vinculadas", null);
		//tabSheet.addTab(buildSubFormCentroResultado(), "Centros de Resultado", null);

		return tabSheet;
	}

	private Component buildSubFormNaturezaFinanceira() {
		String[] atributos = new String[] { "naturezaFinanceira",
				"dataInclusao", "valor" };
		String[] headers = new String[] { "Natureza Financeira",
				"Data Inclusão", "Valor" };

		this.naturezaFinanceiraSubForm = new SubFormComponent<LctoReceberNtFinanceiraEntity, Integer>(
				LctoReceberNtFinanceiraEntity.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("serial")
					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("dataInclusao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("valor".equals(propertyId)) {
							return ComponentUtil.buildCurrencysField(null);
						} else if ("naturezaFinanceira".equals(propertyId)) {
							//ComboBox cmb = ComponentUtil.buildComboBox(null);
							//cmb.removeAllItems();
							//List<NaturezaFinanceira> naturezaFinanceiras = controller.getNaturezasFinanceiras();
							//for (NaturezaFinanceira naturezaFinanceira : naturezaFinanceiras) {
							//	cmb.addItem(naturezaFinanceira);
							//}
							//return cmb;							
							//ComboBox cmb = ComponentUtil.buildComboBox(null);
							//cmb.removeAllItems();
							//List<LctoReceberNtFinanceiraEntity> naturezaFinanceiras = controller.getNaturezasFinan();
							//for (LctoReceberNtFinanceiraEntity naturezaFinanceira : naturezaFinanceiras) {
							//	cmb.addItem(naturezaFinanceira);
						//	}
							//return cmb;
							
							ComboBox comboBox = ComponentUtil.buildComboBox(null);
							BeanItemContainer<NaturezaFinanceira> naturezaContainer = new BeanItemContainer<>(NaturezaFinanceira.class,
									controller.buscarNaturezas());
							naturezaContainer.addNestedContainerProperty("descricao");
							comboBox.setContainerDataSource(naturezaContainer);
							comboBox.setItemCaptionPropertyId("descricao");
							return comboBox;
							
						}

						else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<LctoReceberNtFinanceiraEntity> items) {

				return true;
			}

			protected LctoReceberNtFinanceiraEntity getNovo() {
				LctoReceberNtFinanceiraEntity LlctoReceberNtFinanceira = controller
						.novoLctoReceberNtFinanceira();
				return LlctoReceberNtFinanceira;
			}

			@Override
			protected void getRemoverSelecionados(
					List<LctoReceberNtFinanceiraEntity> values) {
				controller.removerLctoReceberNtFinanceira(values);
			}
		};

		return this.naturezaFinanceiraSubForm;
	}
	
	/*private Component buildSubFormCentroResultado() {
		String[] atributos = new String[] { "classificacao",
				"descricao", "tipo","percentualRateio","plano" };
		String[] headers = new String[] { "Classificação",
				"Descrição", "Tipo", "Percentual Rateio","Plano" };

		this.centroResultadoSubForm = new SubFormComponent<CentroResultado, Integer>(
				CentroResultado.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					/*private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("classificacao".equals(propertyId)) {
							TextField field = new TextField();
							field.setSizeFull();
							return field;
						} else if ("descricao".equals(propertyId)) {
							TextField field = new TextField();
							field.setSizeFull();
							return field;
						} else if ("tipo".equals(propertyId)) {
							TextField field = new TextField();
							field.setSizeFull();
							return field;
							
						}else if ("percentualRateio".equals(propertyId)) {
							
							return ComponentUtil.buildCurrencyField(null);
						}else if ("plano".equals(propertyId)) {
							TextField field = new TextField();
							field.setSizeFull();
							return field;
						}

						else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<CentroResultado> items) {

				return true;
			}

			protected CentroResultado getNovo() {
				CentroResultado centroResultado = controller
						.novoLctoReceberNtFinanceira();
				return centroResultado;
			}

			@Override
			protected void getRemoverSelecionados(List<CentroResultado> values) {
				controller.removerCentroResultado(values);
			}
		};

		return this.centroResultadoSubForm;
	}*/

	private Component buildSubFormParcelas() {

		parcelasLayout = new VerticalLayout();
		parcelasLayout.setImmediate(false);
		parcelasLayout.setSizeFull();
		parcelasLayout.setMargin(false);
		parcelasLayout.setSpacing(true);

		String[] atributos = new String[] { "numeroParcela", "dataEmissao",
				"dataVencimento", "descontoAte", "valor", "taxaJuro",
				"valorJuro", "taxaMulta", "valorMulta", "taxaDesconto",
				"valorDesconto", "emitiuBoleto", "boletoNossoNumero" };

		String[] headers = new String[] { "Número Parcela", "Data Emissão",
				"Data Vencimento", "Desconto Até", "Valor", "Taxa Juro",
				"Valor Juro", "Taxa Multa", "Valor Multa", "Taxa Desconto",
				"Valor Desconto", "Emitir Boleto", "Boleto Nosso Número" };

		this.parcelasSubForm = new SubFormComponent<ParcelaReceber, Integer>(
				ParcelaReceber.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected Map<String, ColumnGenerator> generateCustomColumns() {

				Map<String, ColumnGenerator> colunas = new HashMap<>();

				colunas.put("Boleto", new ColumnGenerator() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					@SuppressWarnings("unchecked")
					public Component generateCell(final Table source,
							final Object itemId, final Object columnId) {

						final BeanItem<ParcelaReceber> selectedBeanItem = (BeanItem<ParcelaReceber>) source
								.getContainerDataSource().getItem(itemId);

						Button boletoButton = new Button("");
						boletoButton.addStyleName("btnBoleto");

						StreamResource myResource = createBoletoResource(selectedBeanItem);
						if (myResource != null) {
							FileDownloader fileDownloader = new FileDownloader(
									myResource);
							fileDownloader.extend(boletoButton);
						}

						return boletoButton;
					}

					private StreamResource createBoletoResource(
							final BeanItem<ParcelaReceber> selectedBeanItem) {

						return new StreamResource(new StreamSource() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public InputStream getStream() {
								ByteArrayInputStream resource = null;
								try {

									List<ParcelaReceber> listaParcelasReceber = new ArrayList<ParcelaReceber>();

									listaParcelasReceber.add(selectedBeanItem
											.getBean());
									byte[] boleto = controller
											.gerarBoleto(listaParcelasReceber);
									resource = new ByteArrayInputStream(boleto);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									mensagemErro(e.getMessage());
								}

								return resource;
							}
						}, "boleto.pdf");
					}
				});

				return colunas;
			}

			@Override
			protected void adicionarBotoes(Table table) {

			}

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
						 * 
						 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("dataEmissao".equals(propertyId)
								|| "dataVencimento".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("valor".equals(propertyId)) {
							return ComponentUtil.buildCurrencyField(null);

						} else if ("taxaJuro".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildPercentageField(null);
							field.setReadOnly(true);
							return field;
						} else if ("taxaMulta".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildPercentageField(null);
							field.setReadOnly(true);
							return field;
						} else if ("taxaDesconto".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildPercentageField(null);
							field.setReadOnly(true);
							return field;
						} else if ("valorJuro".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildCurrencyField(null);
							field.setReadOnly(true);

							return field;
						} else if ("valorMulta".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildCurrencyField(null);
							field.setReadOnly(true);
							return field;
						} else if ("valorDesconto".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildCurrencyField(null);
							field.setReadOnly(true);
							return field;
						} else if ("valorPago".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildCurrencyField(null);
							field.setReadOnly(true);
							return field;
						} else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<ParcelaReceber> items) {

				return true;
			}

			protected ParcelaReceber getNovo() {
				ParcelaReceber parcelaReceber = controller.novoParcelaReceber();
				return parcelaReceber;
			}

			protected void getRemoverSelecionados(List<ParcelaReceber> values) {
				controller.removerParcelaReceber(values);
			}
		};
		parcelasLayout.addComponent(this.parcelasSubForm);
		parcelasLayout.setExpandRatio(parcelasSubForm, 1);

		return parcelasLayout;
	}

	public void preencheBean(LancamentoReceber currentBean) {
		currentBean.setDataLancamento(dtLancamento.getValue());
		currentBean.setPrimeiroVencimento(dtPrimeiroVencimento.getValue());
		currentBean.setDocumentoOrigem((DocumentoOrigem) cbDocumentoOrigem
				.getValue());
		Object txIntervaloParcelaConvertedValue = txIntervaloParcela.getConvertedValue();
		currentBean.setIntervaloEntreParcelas(txIntervaloParcelaConvertedValue != null 
				&& !"".equals(txIntervaloParcelaConvertedValue)? (Integer) txIntervaloParcelaConvertedValue : 0);
		currentBean.setNumeroDocumento(txNumeroDocumento.getValue());
		currentBean.setQuantidadeParcela((Integer) txQuantidadeParcela
				.getConvertedValue());

		currentBean.setCliente(cbCliente.getValue());
		currentBean.setTaxaComissao((BigDecimal) txTaxaComissao
				.getConvertedValue());
		currentBean.setValorAReceber((BigDecimal) txValorReceber
				.getConvertedValue());
		currentBean.setValorComissao((BigDecimal) txValorComissao
				.getConvertedValue());
		currentBean
				.setValorTotal((BigDecimal) txValorTotal.getConvertedValue());
	}

	public void preencheForm(LancamentoReceber currentBean) {

		dtLancamento.setValue(currentBean.getDataLancamento());
		dtPrimeiroVencimento.setValue(currentBean.getPrimeiroVencimento());
		cbDocumentoOrigem.setValue(currentBean.getDocumentoOrigem());
		txIntervaloParcela.setConvertedValue(currentBean
				.getIntervaloEntreParcelas());
		txNumeroDocumento.setValue(currentBean.getNumeroDocumento());
		txQuantidadeParcela.setConvertedValue(currentBean
				.getQuantidadeParcela());
		parcelasSubForm.fillWith(currentBean.getParcelasReceber());
		naturezaFinanceiraSubForm.fillWith(currentBean
				.getLctoReceberNtFinanceira());
		cbCliente.setValue(currentBean.getCliente());
		txTaxaComissao.setConvertedValue(currentBean.getTaxaComissao());
		txValorReceber.setConvertedValue(currentBean.getValorAReceber());
		txValorComissao.setConvertedValue(currentBean.getTaxaComissao());
		txValorTotal.setConvertedValue(currentBean.getValorTotal());
	}
	
	public void preencheSubForm(List<LctoReceberNtFinanceiraEntity> detalhes) {
		naturezaFinanceiraSubForm.fillWith(detalhes);
	}

	public ManyToOneComboField<DocumentoOrigem> getCbDocumentoOrigem() {
		return cbDocumentoOrigem;
	}

	public void setCbDocumentoOrigem(
			ManyToOneComboField<DocumentoOrigem> cbDocumentoOrigem) {
		this.cbDocumentoOrigem = cbDocumentoOrigem;
	}

	public TextField getTxNumeroDocumento() {
		return txNumeroDocumento;
	}

	public void setTxNumeroDocumento(TextField txNumeroDocumento) {
		this.txNumeroDocumento = txNumeroDocumento;
	}

	public PopupDateField getDtLancamento() {
		return dtLancamento;
	}

	public void setDtLancamento(PopupDateField dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public NumericField getTxQuantidadeParcela() {
		return txQuantidadeParcela;
	}

	public void setTxQuantidadeParcela(NumericField txQuantidadeParcela) {
		this.txQuantidadeParcela = txQuantidadeParcela;
	}

	public NumericField getTxIntervaloParcela() {
		return txIntervaloParcela;
	}

	public void setTxIntervaloParcela(NumericField txIntervaloParcela) {
		this.txIntervaloParcela = txIntervaloParcela;
	}

	public PopupDateField getDtPrimeiroVencimento() {
		return dtPrimeiroVencimento;
	}

	public void setDtPrimeiroVencimento(PopupDateField dtPrimeiroVencimento) {
		this.dtPrimeiroVencimento = dtPrimeiroVencimento;
	}

	public TabSheet getTabSheet() {
		return tabSheet;
	}

	public void setTabSheet(TabSheet tabSheet) {
		this.tabSheet = tabSheet;
	}

	public SubFormComponent<ParcelaReceber, Integer> getParcelasSubForm() {
		return parcelasSubForm;
	}

	public void setParcelasSubForm(
			SubFormComponent<ParcelaReceber, Integer> parcelasSubForm) {
		this.parcelasSubForm = parcelasSubForm;
	}

	public SubFormComponent<LctoReceberNtFinanceiraEntity, Integer> getNaturezaFinanceiraSubForm() {
		return naturezaFinanceiraSubForm;
	}

	public void setNaturezaFinanceiraSubForm(
			SubFormComponent<LctoReceberNtFinanceiraEntity, Integer> naturezaFinanceiraSubForm) {
		this.naturezaFinanceiraSubForm = naturezaFinanceiraSubForm;
	}

	public Button getBtnGerarParcelas() {
		return btnGerarParcelas;
	}

	public void setBtnGerarParcelas(Button btnGerarParcelas) {
		this.btnGerarParcelas = btnGerarParcelas;
	}

	public ManyToOneComboField<ContaCaixa> getCbContaCaixa() {
		return cbContaCaixa;
	}

	public void setCbContaCaixa(ManyToOneComboField<ContaCaixa> cbContaCaixa) {
		this.cbContaCaixa = cbContaCaixa;
	}

	public ManyToOneComboField<ClienteEntity> getCbCliente() {
		return cbCliente;
	}

	public void setCbCliente(ManyToOneComboField<ClienteEntity> cbCliente) {
		this.cbCliente = cbCliente;
	}

	public TextField getTxValorTotal() {
		return txValorTotal;
	}

	public void setTxValorTotal(TextField txValorTotal) {
		this.txValorTotal = txValorTotal;
	}

	public TextField getTxValorReceber() {
		return txValorReceber;
	}

	public void setTxValorReceber(TextField txValorReceber) {
		this.txValorReceber = txValorReceber;
	}

	public TextField getTxTaxaComissao() {
		return txTaxaComissao;
	}

	public void setTxTaxaComissao(TextField txTaxaComissao) {
		this.txTaxaComissao = txTaxaComissao;
	}

	public TextField getTxValorComissao() {
		return txValorComissao;
	}

	public void setTxValorComissao(TextField txValorComissao) {
		this.txValorComissao = txValorComissao;
	}

	public ComboBox getCbTipoVencimento() {
		return cbTipoVencimento;
	}

	public void setCbTipoVencimento(ComboBox cbTipoVencimento) {
		this.cbTipoVencimento = cbTipoVencimento;
	}

	public Button getBtnGerarBoleto() {
		return btnGerarBoleto;
	}

	public void setBtnGerarBoleto(Button btnGerarBoleto) {
		this.btnGerarBoleto = btnGerarBoleto;
	}

}