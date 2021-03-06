package dc.visao.folhapagamento.inss;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.folhapagamento.inss.RetencaoFormController;
import dc.entidade.folhapagamento.inss.InssEntity;
import dc.entidade.folhapagamento.inss.ServicoEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class RetencaoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField tfValor13;

	@AutoGenerated
	private TextField tfValorMensal;

	@AutoGenerated
	private ManyToOneComboField<InssEntity> cbInss;

	@AutoGenerated
	private ManyToOneComboField<ServicoEntity> cbServico;

	/*
	 * @AutoGenerated private ManyToOneCombo<InssEntity> cbInss;
	 * 
	 * @AutoGenerated private ManyToOneCombo<ServicoEntity> cbServico;
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private RetencaoFormController controller;

	public RetencaoFormView(final RetencaoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfValorMensal() {
		return tfValorMensal;
	}

	public void setTfValorMensal(TextField tfValorMensal) {
		this.tfValorMensal = tfValorMensal;
	}

	public TextField getTfValor13() {
		return tfValor13;
	}

	public void setTfValor13(TextField tfValor13) {
		this.tfValor13 = tfValor13;
	}

	public ManyToOneComboField<InssEntity> getCbInss() {
		return cbInss;
	}

	public void setCbInss(ManyToOneComboField<InssEntity> cbInss) {
		this.cbInss = cbInss;
	}

	public ManyToOneComboField<ServicoEntity> getCbServico() {
		return cbServico;
	}

	public void setCbServico(ManyToOneComboField<ServicoEntity> cbServico) {
		this.cbServico = cbServico;
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
		gridLayout_1.setRows(6);
		gridLayout_1.setColumns(6);

		// tfValorMensal
		tfValorMensal = ComponentUtil.buildCurrencyField("Valor mensal:");
		gridLayout_1.addComponent(tfValorMensal, 0, 1, 1,1);

		// tfValor13
		tfValor13 = ComponentUtil.buildCurrencyField("Valor do 13:");
		gridLayout_1.addComponent(tfValor13, 2, 1, 3,1);

		// cbInss
		cbInss = new ManyToOneComboField<>(InssEntity.class);
		cbInss.setCaption("INSS");
		gridLayout_1.addComponent(cbInss, 0, 2, 1,2);

		// cbServico
		cbServico = new ManyToOneComboField<>(ServicoEntity.class);
		cbServico.setCaption("Serviço");
		gridLayout_1.addComponent(cbServico, 3, 2, 4,2);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	/*public void carregarCmbInss(List<InssEntity> lista) {
		BeanItemContainer<InssEntity> bic = new BeanItemContainer<InssEntity>(
				InssEntity.class, lista);
		this.cbInss.setContainerDataSource(bic);
		this.cbInss.setItemCaptionPropertyId("competencia");
	}

	public void carregarCmbServico(List<ServicoEntity> lista) {
		BeanItemContainer<ServicoEntity> bic = new BeanItemContainer<ServicoEntity>(
				ServicoEntity.class, lista);
		this.cbServico.setContainerDataSource(bic);
		this.cbServico.setItemCaptionPropertyId("nome");
	}*/

}