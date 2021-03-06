package dc.visao.contabilidade.lancamento;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.contabilidade.lancamento.LancamentoProgramadoDetFormController;
import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class LancamentoProgramadoDetFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField tfDescricaoHistorico;

	@AutoGenerated
	private TextField tfValor;

	@AutoGenerated
	private TextField tfTipo;

	@AutoGenerated
	private ManyToOneCombo<LancamentoProgramadoCabEntity> cbLancamentoProgramadoCab;

	@AutoGenerated
	private ManyToOneCombo<ContaEntity> cbConta;

	@AutoGenerated
	private ManyToOneCombo<HistoricoEntity> cbHistorico;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private LancamentoProgramadoDetFormController controller;

	public LancamentoProgramadoDetFormView(
			final LancamentoProgramadoDetFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	/**
	 * GETS E SETS
	 */

	public TextField getTfDescricaoHistorico() {
		return tfDescricaoHistorico;
	}

	public void setTfDescricaoHistorico(TextField tfDescricaoHistorico) {
		this.tfDescricaoHistorico = tfDescricaoHistorico;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tfValor) {
		this.tfValor = tfValor;
	}

	public TextField getTfTipo() {
		return tfTipo;
	}

	public void setTfTipo(TextField tfTipo) {
		this.tfTipo = tfTipo;
	}

	public ManyToOneCombo<LancamentoProgramadoCabEntity> getCbLancamentoProgramadoCab() {
		return cbLancamentoProgramadoCab;
	}

	public void setCbLancamentoProgramadoCab(
			ManyToOneCombo<LancamentoProgramadoCabEntity> cbLancamentoProgramadoCab) {
		this.cbLancamentoProgramadoCab = cbLancamentoProgramadoCab;
	}

	public ManyToOneCombo<ContaEntity> getCbConta() {
		return cbConta;
	}

	public void setCbConta(ManyToOneCombo<ContaEntity> cbConta) {
		this.cbConta = cbConta;
	}

	public ManyToOneCombo<HistoricoEntity> getCbHistorico() {
		return cbHistorico;
	}

	public void setCbHistorico(ManyToOneCombo<HistoricoEntity> cbHistorico) {
		this.cbHistorico = cbHistorico;
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

		// tfDescricaoHistorico
		tfDescricaoHistorico = new TextField();
		tfDescricaoHistorico.setCaption("Histórico descrição:");
		tfDescricaoHistorico.setImmediate(false);
		tfDescricaoHistorico.setWidth("175px");
		tfDescricaoHistorico.setHeight("-1px");
		tfDescricaoHistorico.setSizeFull();
		tfDescricaoHistorico.setNullRepresentation("");
		gridLayout_1.addComponent(tfDescricaoHistorico, 0, 1);

		// tfValor
		tfValor = new TextField();
		tfValor.setCaption("Valor:");
		tfValor.setImmediate(false);
		tfValor.setWidth("175px");
		tfValor.setHeight("-1px");
		tfValor.setSizeFull();
		tfValor.setNullRepresentation("");
		gridLayout_1.addComponent(tfValor, 1, 1);

		// tfTipo
		tfTipo = new TextField();
		tfTipo.setCaption("Tipo:");
		tfTipo.setImmediate(false);
		tfTipo.setWidth("175px");
		tfTipo.setHeight("-1px");
		tfTipo.setSizeFull();
		tfTipo.setNullRepresentation("");
		gridLayout_1.addComponent(tfTipo, 0, 2);

		// cbLancamentoProgramadoCab
		cbLancamentoProgramadoCab = new ManyToOneCombo<>();
		cbLancamentoProgramadoCab.setCaption("Lançamento programado cab:");
		cbLancamentoProgramadoCab.setImmediate(false);
		cbLancamentoProgramadoCab.setWidth("175px");
		cbLancamentoProgramadoCab.setHeight("-1px");
		cbLancamentoProgramadoCab.setSizeFull();
		gridLayout_1.addComponent(cbLancamentoProgramadoCab, 1, 2);

		// cbConta
		cbConta = new ManyToOneCombo<>();
		cbConta.setCaption("Conta:");
		cbConta.setImmediate(false);
		cbConta.setWidth("175px");
		cbConta.setHeight("-1px");
		cbConta.setSizeFull();
		gridLayout_1.addComponent(cbConta, 0, 3);

		// cbHistorico
		cbHistorico = new ManyToOneCombo<>();
		cbHistorico.setCaption("Histórico:");
		cbHistorico.setImmediate(false);
		cbHistorico.setWidth("175px");
		cbHistorico.setHeight("-1px");
		cbHistorico.setSizeFull();
		gridLayout_1.addComponent(cbHistorico, 1, 3);

		return gridLayout_1;
	}

}