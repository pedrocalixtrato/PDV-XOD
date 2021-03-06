package dc.visao.ponto;

import java.util.List;
import java.util.Locale;

import org.vaadin.easyuploads.UploadField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.ponto.AFDTipo3;
import dc.entidade.ponto.PontoFechamentoJornada;
import dc.entidade.ponto.PontoMarcacao;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;
import dc.visao.ponto.PontoConsultaFormView.TipoMarcacaoPonto;
import dc.visao.ponto.PontoConsultaFormView.TipoRegistroPonto;
import dc.visao.ponto.converters.ColaboradorConverter;
import dc.visao.ponto.converters.PontoClassificacaoJornadaConverter;

public class PontoImportacaoFormView extends CustomComponent {
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TabSheet tabSheet;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private Button btnProcessarFechamento;

	@AutoGenerated
	private SubFormComponent<AFDTipo3, Integer> AFDSubForm;

	@AutoGenerated
	private SubFormComponent<PontoMarcacao, Integer> PontoMarcacaoSubForm;

	@AutoGenerated
	private SubFormComponent<PontoFechamentoJornada, Integer> pontoFechamentoSubForm;

	@AutoGenerated
	private UploadField upArquivo;

	@AutoGenerated
	private Button btnGravarFechamento;

	public PontoImportacaoFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
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

		// gridLayout_1
		fields = buildButtons();
		mainLayout.addComponent(fields);

		tabSheet = buildAbas();

		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1);

		return mainLayout;
	}

	private TabSheet buildAbas() {
		tabSheet = new TabSheet();
		tabSheet.setImmediate(true);
		tabSheet.setSizeFull();

		tabSheet.addTab(buildSubFormAFD(), "Marcações no arquivo", null);
		tabSheet.addTab(buildSubFormPontoMarcacao(), "Marcações incluídas", null);
		tabSheet.addTab(buildSubFormPontoFechamentoJornada(), "Fechamento Jornada", null);

		return tabSheet;
	}

	@SuppressWarnings("serial")
	private SubFormComponent<PontoMarcacao, Integer> buildSubFormPontoMarcacao() {

		this.PontoMarcacaoSubForm = new SubFormComponent<PontoMarcacao, Integer>(PontoMarcacao.class, new String[] { "colaborador", "dataMarcacao",
				"horaMarcacao", "tipoMarcacao", "tipoRegistro", "justificativa" }, new String[] { "Colaborador", "Data marcação", "Hora marcação",
				"Tipo marcação", "Tipo Registro", "Justificativa" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("colaborador".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new ColaboradorConverter());

							return textField;
						} else if ("dataMarcacao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("horaMarcacao".equals(propertyId)) {
							return ComponentUtil.buildMaskedTextField(null, "##:##:##");
						} else if ("tipoMarcacao".equals(propertyId)) {
							ComboBox cmb = ComponentUtil.buildComboBox(null);
							cmb.removeAllItems();

							cmb.setConverter(new Converter<Object, String>() {

								@Override
								public String convertToModel(Object value, Class<? extends String> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {

									if (value instanceof String) {
										return TipoMarcacaoPonto.getEnumLabel((String) value).getCodigo();
									} else {
										return ((TipoMarcacaoPonto) value).getCodigo();
									}
								}

								@Override
								public Class<String> getModelType() {

									return String.class;
								}

								@Override
								public Class<Object> getPresentationType() {

									return Object.class;
								}

								@Override
								public Object convertToPresentation(String value, Class<? extends Object> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return TipoMarcacaoPonto.getEnum(value);
								}

							});

							for (TipoMarcacaoPonto e : TipoMarcacaoPonto.values()) {
								cmb.addItem(e);
							}

							return cmb;
						} else if ("tipoRegistro".equals(propertyId)) {
							ComboBox cmb = ComponentUtil.buildComboBox(null);
							cmb.removeAllItems();

							cmb.setConverter(new Converter<Object, String>() {

								@Override
								public String convertToModel(Object value, Class<? extends String> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {

									if (value instanceof String) {
										return TipoRegistroPonto.getEnumLabel((String) value).getCodigo();
									} else {
										return ((TipoRegistroPonto) value).getCodigo();
									}
								}

								@Override
								public Class<String> getModelType() {

									return String.class;
								}

								@Override
								public Class<Object> getPresentationType() {

									return Object.class;
								}

								@Override
								public Object convertToPresentation(String value, Class<? extends Object> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return TipoRegistroPonto.getEnum(value);
								}

							});

							for (TipoRegistroPonto e : TipoRegistroPonto.values()) {
								cmb.addItem(e);
							}

							return cmb;
						} else if ("justificativa".equals(propertyId)) {
							return ComponentUtil.buildTextField(null);
						}

						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<PontoMarcacao> items) {
			
				return true;
			}
		};

		return this.PontoMarcacaoSubForm;
	}

	private Component buildSubFormPontoFechamentoJornada() {

		String[] atributos = new String[] { "dataFechamento", "diaSemana", "codigoHorario", "cargaHorariaEsperada", "cargaHorariaDiurna",
				"cargaHorariaNoturna", "cargaHorariaTotal", "entrada01", "saida01", "entrada02", "saida02", "entrada03", "saida03", "entrada04",
				"saida04", "entrada05", "saida05", "horaInicioJornada", "horaFimJornada", "horaExtra01", "percentualHoraExtra01",
				"modalidadeHoraExtra01", "horaExtra02", "percentualHoraExtra02", "modalidadeHoraExtra02", "horaExtra03", "percentualHoraExtra03",
				"modalidadeHoraExtra03", "horaExtra04", "percentualHoraExtra04", "modalidadeHoraExtra04", "faltaAtraso", "compensar", "bancoHoras",
				"observacao", "colaborador", "pontoClassificacaoJornada" };
		String[] headers = new String[] { "Data fechamento", "Dia Semana", "Código Horário", "Carga Horária Esperada", "Carga Horaria Diurna",
				"Carga Horária Noturna", "Carga Horária Total", "Entrada01", "Saída01", "Entrada02", "Saída02", "Entrada03", "Saída03", "Entrada04",
				"Saída04", "Entrada05", "Saída05", "Hora Início Jornada", "Hora Fim Jornada", "Hora Extra01", "Percentual Hora Extra01",
				"Modalidade Hora Extra01", "Hora Extra02", "Percentual Hora Extra02", "Modalidade Hora Extra02", "Hora Extra03",
				"Percentual Hora Extra03", "Modalidade Hora Extra03", "Hora Extra04", "Percentual Hora Extra04", "Modalidade Hora Extra04",
				"Falta/Atraso", "Compensar", "Banco de Horas", "Observação", "Colaborador", "Ponto Classificação Jornada" };

		this.pontoFechamentoSubForm = new SubFormComponent<PontoFechamentoJornada, Integer>(PontoFechamentoJornada.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

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
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("dataMarcacao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("colaborador".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new ColaboradorConverter());

							return textField;
						} else if ("pontoClassificacaoJornada".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new PontoClassificacaoJornadaConverter());

							return textField;
						}

						else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<PontoFechamentoJornada> items) {
				
				return true;
			}
		};

		return this.pontoFechamentoSubForm;
	}

	@AutoGenerated
	private GridLayout buildButtons() {
		// common part: create layout
		fields = new GridLayout(3, 1);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		// upArquivo
		upArquivo = new UploadField();
		upArquivo.setCaption("Importar Arquivo AFD");
		upArquivo.setImmediate(false);
		upArquivo.setHeight("50px");
		fields.addComponent(upArquivo, 0, 0);

		btnProcessarFechamento = new Button("Processar Fechamento");
		btnProcessarFechamento.setImmediate(true);
		btnProcessarFechamento.setWidth("-1px");
		btnProcessarFechamento.setHeight("-1px");
		fields.addComponent(btnProcessarFechamento, 1, 0);

		btnGravarFechamento = new Button("Gravar Fechamento");
		btnGravarFechamento.setImmediate(true);
		btnGravarFechamento.setWidth("-1px");
		btnGravarFechamento.setHeight("-1px");
		fields.addComponent(btnGravarFechamento, 2, 0);

		return fields;
	}

	@SuppressWarnings("serial")
	private SubFormComponent<AFDTipo3, Integer> buildSubFormAFD() {

		String[] atributos = new String[] { "sequencial", "dataMarcacao", "horaMarcacao", "pisEmpregado", "colaborador", "numeroSerieRelogioPonto",
				"desconsiderar", "situacaoRegistro" };
		String[] headers = new String[] { "Sequencial", "Data Marcação", "Hora Marcação", "Pis Empregado", "Colaborador",
				"N° de Sério Relógio Ponto", "Desconsiderar", "Situação Registro" };

		this.AFDSubForm = new SubFormComponent<AFDTipo3, Integer>(AFDTipo3.class, atributos, headers) {

			@Override
			protected void adicionarBotoes(Table table) {

			}

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("dataMarcacao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("colaborador".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new ColaboradorConverter());

							return textField;
						} else {
							return ComponentUtil.buildTextField(null);
						}

						// return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<AFDTipo3> items) {

				return true;
			}
		};

		return this.AFDSubForm;
	}

	public void fillAFDSubForm(List<AFDTipo3> listaTipo3) {
		this.AFDSubForm.fillWith(listaTipo3);
	}

	public SubFormComponent<AFDTipo3, Integer> getAFDSubForm() {
		return AFDSubForm;
	}

	public void setAFDSubForm(SubFormComponent<AFDTipo3, Integer> subForm) {
		this.AFDSubForm = subForm;
	}

	public Button getBtnProcessarFechamento() {
		return btnProcessarFechamento;
	}

	public void setBtnProcessarFechamento(Button btnProcessarFechamento) {
		this.btnProcessarFechamento = btnProcessarFechamento;
	}

	public SubFormComponent<PontoMarcacao, Integer> getPontoMarcacaoSubForm() {
		return PontoMarcacaoSubForm;
	}

	public void setPontoMarcacaoSubForm(SubFormComponent<PontoMarcacao, Integer> pontoMarcacaoSubForm) {
		PontoMarcacaoSubForm = pontoMarcacaoSubForm;
	}

	public SubFormComponent<PontoFechamentoJornada, Integer> getPontoFechamentoSubForm() {
		return pontoFechamentoSubForm;
	}

	public void setPontoFechamentoSubForm(SubFormComponent<PontoFechamentoJornada, Integer> pontoFechamentoSubForm) {
		this.pontoFechamentoSubForm = pontoFechamentoSubForm;
	}

	public UploadField getUpArquivo() {
		return upArquivo;
	}

	public void setUpArquivo(UploadField upArquivo) {
		this.upArquivo = upArquivo;
	}

	public TabSheet getTabSheet() {
		return tabSheet;
	}

	public Button getBtnGravarFechamento() {
		return btnGravarFechamento;
	}

	public void setBtnGravarFechamento(Button btnGravarFechamento) {
		this.btnGravarFechamento = btnGravarFechamento;
	}

}
