package dc.visao.comercial;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.comercial.CondicaoPagamentoFormController;
import dc.entidade.comercial.ParcelaCondicaoPagamento;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class CondicaoPagamentoFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	CondicaoPagamentoFormController controller;

	@AutoGenerated
	private GridLayout fields;

	private TextField txtModelo;

	private TextField txtSerie;

	private TextField txtNome;
	private TextArea txtDescricao;

	TextField txtFaturamentoMinimo, txtFaturamentoMaximo, txtIndiceCorrecao,
			txtDiasTolerancia, txtValorTolerancia, txtPrazoMedio;

	private TextField txtTemplate, txtNumeroItens, txtUltimoImpresso;

	private SubFormComponent<ParcelaCondicaoPagamento, Integer> parcelasSubForm;

	@AutoGenerated
	private TabSheet subForms;

	public CondicaoPagamentoFormView(CondicaoPagamentoFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		setHeight("100.0%");
		setWidth("100.0%");

		fields = buildFields();
		mainLayout.addComponent(fields);

		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);

		buildParcelasSubForm();
		mainLayout.addComponent(subForms);

		return mainLayout;

	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 6);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		txtNome = ComponentUtil.buildTextField("Nome");
		fields.addComponent(txtNome, 0, 0, 5, 0);

		txtDescricao = ComponentUtil.buildTextArea("Descrição");
		fields.addComponent(txtDescricao, 0, 1, 5, 1);

		txtFaturamentoMinimo = ComponentUtil
				.buildCurrencysField("Faturamento Minimo");
		fields.addComponent(txtFaturamentoMinimo, 0, 2);

		txtFaturamentoMaximo = ComponentUtil
				.buildCurrencysField("Faturamento Máximo");
		fields.addComponent(txtFaturamentoMaximo, 1, 2);

		txtIndiceCorrecao = ComponentUtil
				.buildNumberField("Indice de Correção");
		fields.addComponent(txtIndiceCorrecao, 2, 2);

		txtDiasTolerancia = ComponentUtil
				.buildNumericField("Dias de Tolerância");
		fields.addComponent(txtDiasTolerancia, 3, 2);

		txtValorTolerancia = ComponentUtil
				.buildCurrencysField("Valor da Tolerância");
		fields.addComponent(txtValorTolerancia, 4, 2);

		txtPrazoMedio = ComponentUtil.buildNumericField("Prazo Médio");
		fields.addComponent(txtPrazoMedio, 5, 2);

		return fields;

	}

	public TextField getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(TextField txtModelo) {
		this.txtModelo = txtModelo;
	}

	public TextField getTxtSerie() {
		return txtSerie;
	}

	public void setTxtSerie(TextField txtSerie) {
		this.txtSerie = txtSerie;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextArea getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(TextArea txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public TextField getTxtTemplate() {
		return txtTemplate;
	}

	public void setTxtTemplate(TextField txtTemplate) {
		this.txtTemplate = txtTemplate;
	}

	public TextField getTxtNumeroItens() {
		return txtNumeroItens;
	}

	public void setTxtNumeroItens(TextField txtNumeroItens) {
		this.txtNumeroItens = txtNumeroItens;
	}

	public TextField getTxtUltimoImpresso() {
		return txtUltimoImpresso;
	}

	public void setTxtUltimoImpresso(TextField txtUltimoImpresso) {
		this.txtUltimoImpresso = txtUltimoImpresso;
	}

	public TextField getTxtFaturamentoMinimo() {
		return txtFaturamentoMinimo;
	}

	public void setTxtFaturamentoMinimo(TextField txtFaturamentoMinimo) {
		this.txtFaturamentoMinimo = txtFaturamentoMinimo;
	}

	public TextField getTxtFaturamentoMaximo() {
		return txtFaturamentoMaximo;
	}

	public void setTxtFaturamentoMaximo(TextField txtFaturamentoMaximo) {
		this.txtFaturamentoMaximo = txtFaturamentoMaximo;
	}

	public TextField getTxtIndiceCorrecao() {
		return txtIndiceCorrecao;
	}

	public void setTxtIndiceCorrecao(TextField txtIndiceCorrecao) {
		this.txtIndiceCorrecao = txtIndiceCorrecao;
	}

	public TextField getTxtDiasTolerancia() {
		return txtDiasTolerancia;
	}

	public void setTxtDiasTolerancia(TextField txtDiasTolerancia) {
		this.txtDiasTolerancia = txtDiasTolerancia;
	}

	public TextField getTxtValorTolerancia() {
		return txtValorTolerancia;
	}

	public void setTxtValorTolerancia(TextField txtValorTolerancia) {
		this.txtValorTolerancia = txtValorTolerancia;
	}

	public TextField getTxtPrazoMedio() {
		return txtPrazoMedio;
	}

	public void setTxtPrazoMedio(TextField txtPrazoMedio) {
		this.txtPrazoMedio = txtPrazoMedio;
	}

	private void buildParcelasSubForm() {
		// common part: create layout

		parcelasSubForm = new SubFormComponent<ParcelaCondicaoPagamento, Integer>(
				ParcelaCondicaoPagamento.class, new String[] { "parcela",
						"dias", "taxa" }, new String[] { "Parcela", "Dias",
						"Taxa" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						if ("parcela".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildNumericField("Parcela");
							return field;
						}

						if ("dias".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildNumericField("Dias");
							return field;
						}

						if ("taxa".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildNumberField("Taxa");
							return field;
						}

						return null;
					}
				};
			}

			protected ParcelaCondicaoPagamento getNovo() {
				ParcelaCondicaoPagamento parcela = controller
						.adicionarParcela();
				return parcela;
			}

			@Override
			public boolean validateItems(List<ParcelaCondicaoPagamento> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		layout.addComponent(parcelasSubForm);
		subForms.addTab(layout, "Parcela", null);
	}

	public void preencherSubForm(List<ParcelaCondicaoPagamento> detalhes) {
		parcelasSubForm.fillWith(detalhes);
	}

}