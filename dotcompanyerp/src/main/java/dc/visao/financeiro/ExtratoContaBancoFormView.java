package dc.visao.financeiro;

import java.util.List;

import org.vaadin.easyuploads.UploadField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.financeiro.ExtratoContaBancoFormController;
import dc.entidade.financeiro.ExtratoContaBancoEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class ExtratoContaBancoFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	
	@AutoGenerated
	private GridLayout fields;
	
	@AutoGenerated
	private TabSheet subForms;
	
	@AutoGenerated
	private TextField saldo, credito, debito;
	
	private UploadField btnImportar;
	
	private Button btnEfeutaConciliacaoCheque;
	
	private Button btnEfeutaConciliacaoLancamento;
	
	@AutoGenerated
	private SubFormComponent<ExtratoContaBancoEntity, Integer> extratoContaBancoSubForm;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	ExtratoContaBancoFormController controller;
	
	public ExtratoContaBancoFormView(ExtratoContaBancoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
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

		fields = buildFields();
		mainLayout.addComponent(fields);
		
		// common part: create layout
		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
				
		subForms = buildAbas();
				
		mainLayout.addComponent(subForms);
		mainLayout.setExpandRatio(subForms, 1);
		
		return mainLayout;


	}

	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(3);
		fields.setColumns(7);
		
		credito = ComponentUtil.buildCurrencyField("Crédito");
		fields.addComponent(credito, 0, 0, 1,0);
		
		debito = ComponentUtil.buildCurrencyField("Débito");
		fields.addComponent(debito, 2, 0, 3,0);
		
		saldo = ComponentUtil.buildCurrencyField("Saldo Total");
		fields.addComponent(saldo, 4, 0,5,0);
		
		btnImportar = new UploadField();
		btnImportar.setCaption("Importar OFX");
		btnImportar.setDisplayUpload(false);
		fields.addComponent(btnImportar, 0, 2);
		
		btnEfeutaConciliacaoCheque = new Button("Conciliar Cheque");
		fields.addComponent(btnEfeutaConciliacaoCheque, 1, 2);
		
		btnEfeutaConciliacaoLancamento = new Button("Conciliar Lançamentos");
		fields.addComponent(btnEfeutaConciliacaoLancamento, 2, 2);
		
		return fields;
		
	}
	
	private TabSheet buildAbas() {
		subForms = new TabSheet();
		subForms.setImmediate(true);
		subForms.setSizeFull();

		subForms.addTab(buildExtratoContaBancoSubForm(), "Extrato Conta Banco", null);

		return subForms;
	}

	
	@SuppressWarnings("serial")
	private SubFormComponent<ExtratoContaBancoEntity, Integer> buildExtratoContaBancoSubForm() {

		String[] atributos = new String[] { "mes", "ano", "dataMovimento",
				"dataBalancete", "historico", "documento",
				"valor", "conciliado" ,"observacoes"};
		String[] headers = new String[] { "Mês", "Ano", "Data Movimento",
				"Data Balancete", "Histórico", "Documento", "Valor",
				"Conciliado","Observações"};

		this.extratoContaBancoSubForm = new SubFormComponent<ExtratoContaBancoEntity, Integer>(ExtratoContaBancoEntity.class, atributos, headers) {

			@Override
			protected void adicionarBotoes(Table table) {

			}
			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {
					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						if ("mes".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField(null);
							return textField;
						} else if ("ano".equals(propertyId)) {
							TextField textField = ComponentUtil.buildTextField(null);
							return textField;
						} else if ("dataMovimento".equals(propertyId)) {
							PopupDateField date = ComponentUtil.buildPopupDateField(null);
							return date;
						} else if ("dataBalancete".equals(propertyId)) {
							PopupDateField date = ComponentUtil
									.buildPopupDateField(null);
							return date;
						} else if ("historico".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField(null);
							return textField;
						} else if ("documento".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField(null);
							return textField;
						} else if ("valor".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.setEnabled(false);
							return textField;
						}
						else if ("conciliado".equals(propertyId)) {
							ComboBox combobox = ComponentUtil.buildComboBox(null);
							combobox.removeAllItems();

							combobox.addItem("S");
							combobox.addItem("N");

							return combobox;
							
						}
						
						else if ("observacoes".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.setEnabled(false);
							return textField;
						}

						return null;
					}


				};
			}

			/*protected ExtratoContaBancoEntity getNovo() {
				ExtratoContaBancoEntity Item = controller
						.novoExtratoContaBancoItem();
				return Item;
			}

			protected void getRemoverSelecionados(
					List<ExtratoContaBancoEntity> values) {
				controller.removerExtratoContaBancoItem(values);
			}*/

			@Override
			public boolean validateItems(List<ExtratoContaBancoEntity> items) {
				return true;
			}
		};

		return this.extratoContaBancoSubForm;
	}
	
	public void preencheSubForm(List<ExtratoContaBancoEntity> detalhes) {
		extratoContaBancoSubForm.fillWith(detalhes);
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public TabSheet getSubForms() {
		return subForms;
	}

	public void setSubForms(TabSheet subForms) {
		this.subForms = subForms;
	}

	public SubFormComponent<ExtratoContaBancoEntity, Integer> getExtratoContaBancoSubForm() {
		return extratoContaBancoSubForm;
	}

	public void setExtratoContaBancoSubForm(
			SubFormComponent<ExtratoContaBancoEntity, Integer> extratoContaBancoSubForm) {
		this.extratoContaBancoSubForm = extratoContaBancoSubForm;
	}

	public TextField getSaldo() {
		return saldo;
	}

	public void setSaldo(TextField saldo) {
		this.saldo = saldo;
	}

	public TextField getCredito() {
		return credito;
	}

	public void setCredito(TextField credito) {
		this.credito = credito;
	}

	public TextField getDebito() {
		return debito;
	}

	public void setDebito(TextField debito) {
		this.debito = debito;
	}

	public UploadField getBtnImportar() {
		return btnImportar;
	}

	public void setBtnImportar(UploadField btnImportar) {
		this.btnImportar = btnImportar;
	}

	public Button getBtnEfeutaConciliacaoCheque() {
		return btnEfeutaConciliacaoCheque;
	}

	public void setBtnEfeutaConciliacaoCheque(Button btnEfeutaConciliacaoCheque) {
		this.btnEfeutaConciliacaoCheque = btnEfeutaConciliacaoCheque;
	}

	public Button getBtnEfeutaConciliacaoLancamento() {
		return btnEfeutaConciliacaoLancamento;
	}

	public void setBtnEfeutaConciliacaoLancamento(
			Button btnEfeutaConciliacaoLancamento) {
		this.btnEfeutaConciliacaoLancamento = btnEfeutaConciliacaoLancamento;
	}
	
}