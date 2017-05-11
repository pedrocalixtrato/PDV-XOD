package dc.visao.contabilidade.demonstrativo;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.contabilidade.demonstrativo.EncerramentoExeDetFormController;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeCabEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class EncerramentoExeDetFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField tfSaldoAnterior;

	@AutoGenerated
	private TextField tfValorDebito;

	@AutoGenerated
	private TextField tfValorCredito;

	@AutoGenerated
	private TextField tfSaldo;

	@AutoGenerated
	private ManyToOneCombo<EncerramentoExeCabEntity> cbEncerramentoExeCab;

	@AutoGenerated
	private ManyToOneCombo<ContaEntity> cbConta;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private EncerramentoExeDetFormController controller;

	public EncerramentoExeDetFormView(
			final EncerramentoExeDetFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfSaldoAnterior() {
		return tfSaldoAnterior;
	}

	public void setTfSaldoAnterior(TextField tfSaldoAnterior) {
		this.tfSaldoAnterior = tfSaldoAnterior;
	}

	public TextField getTfValorDebito() {
		return tfValorDebito;
	}

	public void setTfValorDebito(TextField tfValorDebito) {
		this.tfValorDebito = tfValorDebito;
	}

	public TextField getTfValorCredito() {
		return tfValorCredito;
	}

	public void setTfValorCredito(TextField tfValorCredito) {
		this.tfValorCredito = tfValorCredito;
	}

	public TextField getTfSaldo() {
		return tfSaldo;
	}

	public void setTfSaldo(TextField tfSaldo) {
		this.tfSaldo = tfSaldo;
	}

	public ManyToOneCombo<EncerramentoExeCabEntity> getCbEncerramentoExeCab() {
		return cbEncerramentoExeCab;
	}

	public void setCbEncerramentoExeCab(
			ManyToOneCombo<EncerramentoExeCabEntity> cbEncerramentoExeCab) {
		this.cbEncerramentoExeCab = cbEncerramentoExeCab;
	}

	public ManyToOneCombo<ContaEntity> getCbConta() {
		return cbConta;
	}

	public void setCbConta(ManyToOneCombo<ContaEntity> cbConta) {
		this.cbConta = cbConta;
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

		// tfSaldoAnterior;
		tfSaldoAnterior = ComponentUtil.buildCurrencyField("Saldo anterior:");
		tfSaldoAnterior.setImmediate(false);
		tfSaldoAnterior.setWidth("175px");
		tfSaldoAnterior.setHeight("-1px");
		tfSaldoAnterior.setSizeFull();
		tfSaldoAnterior.setNullRepresentation("");
		gridLayout_1.addComponent(tfSaldoAnterior, 0, 1);

		// tfValorDebito;
		tfValorDebito = ComponentUtil.buildCurrencyField("Valor débito:");
		tfValorDebito.setImmediate(false);
		tfValorDebito.setWidth("175px");
		tfValorDebito.setHeight("-1px");
		tfValorDebito.setSizeFull();
		tfValorDebito.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorDebito, 1, 1);

		// tfValorCredito;
		tfValorCredito = ComponentUtil.buildCurrencyField("Valor crédito:");
		tfValorCredito.setImmediate(false);
		tfValorCredito.setWidth("175px");
		tfValorCredito.setHeight("-1px");
		tfValorCredito.setSizeFull();
		tfValorCredito.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorCredito, 0, 2);

		// tfSaldo;
		tfSaldo = ComponentUtil.buildCurrencyField("Saldo:");
		tfSaldo.setImmediate(false);
		tfSaldo.setWidth("175px");
		tfSaldo.setHeight("-1px");
		tfSaldo.setSizeFull();
		tfSaldo.setNullRepresentation("");
		gridLayout_1.addComponent(tfSaldo, 1, 2);

		// cbEncerramentoExeCab
		cbEncerramentoExeCab = new ManyToOneCombo<>();
		cbEncerramentoExeCab.setCaption("Encerramento exe cab:");
		cbEncerramentoExeCab.setImmediate(false);
		cbEncerramentoExeCab.setWidth("175px");
		cbEncerramentoExeCab.setHeight("-1px");
		cbEncerramentoExeCab.setSizeFull();
		gridLayout_1.addComponent(cbEncerramentoExeCab, 0, 3);

		// cbConta
		cbConta = new ManyToOneCombo<>();
		cbConta.setCaption("Conta:");
		cbConta.setImmediate(false);
		cbConta.setWidth("175px");
		cbConta.setHeight("-1px");
		cbConta.setSizeFull();
		gridLayout_1.addComponent(cbConta, 1, 3);

		return gridLayout_1;
	}

}