package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.validator.ObjectValidator;
import dc.controller.nfe.ProdutoServicoFormController;

/**
 * 
 * 
 */

public class NfeDetEspecificoCombustivelFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormController controller;

	/**
	 * 
	 */

	@AutoGenerated
	private String titulo = "Combustível";

	@AutoGenerated
	private VerticalLayout vlNdeCombustivel;

	@AutoGenerated
	private Panel plNdeCombustivel;

	@AutoGenerated
	private GridLayout glNdeCombustivel;

	@AutoGenerated
	private TextField tfCodigoAnpCombustivel;

	@AutoGenerated
	private TextField tfCodifCombustivel;

	@AutoGenerated
	private TextField tfQtdeTempAmbienteCombustivel;

	@AutoGenerated
	private TextField tfUfConsumoCombustivel;

	@AutoGenerated
	private TextField tfBcCideCombustivel;

	@AutoGenerated
	private TextField tfAliquotaCideCombustivel;

	@AutoGenerated
	private TextField tfValorCideCombustivel;

	public String getTitulo() {
		return titulo;
	}

	public TextField getTfCodigoAnpCombustivel() {
		return tfCodigoAnpCombustivel;
	}

	public void setTfCodigoAnpCombustivel(TextField tfCodigoAnpCombustivel) {
		this.tfCodigoAnpCombustivel = tfCodigoAnpCombustivel;
	}

	public TextField getTfCodifCombustivel() {
		return tfCodifCombustivel;
	}

	public void setTfCodifCombustivel(TextField tfCodifCombustivel) {
		this.tfCodifCombustivel = tfCodifCombustivel;
	}

	public TextField getTfQtdeTempAmbienteCombustivel() {
		return tfQtdeTempAmbienteCombustivel;
	}

	public void setTfQtdeTempAmbienteCombustivel(
			TextField tfQtdeTempAmbienteCombustivel) {
		this.tfQtdeTempAmbienteCombustivel = tfQtdeTempAmbienteCombustivel;
	}

	public TextField getTfUfConsumoCombustivel() {
		return tfUfConsumoCombustivel;
	}

	public void setTfUfConsumoCombustivel(TextField tfUfConsumoCombustivel) {
		this.tfUfConsumoCombustivel = tfUfConsumoCombustivel;
	}

	public TextField getTfBcCideCombustivel() {
		return tfBcCideCombustivel;
	}

	public void setTfBcCideCombustivel(TextField tfBcCideCombustivel) {
		this.tfBcCideCombustivel = tfBcCideCombustivel;
	}

	public TextField getTfAliquotaCideCombustivel() {
		return tfAliquotaCideCombustivel;
	}

	public void setTfAliquotaCideCombustivel(TextField tfAliquotaCideCombustivel) {
		this.tfAliquotaCideCombustivel = tfAliquotaCideCombustivel;
	}

	public TextField getTfValorCideCombustivel() {
		return tfValorCideCombustivel;
	}

	public void setTfValorCideCombustivel(TextField tfValorCideCombustivel) {
		this.tfValorCideCombustivel = tfValorCideCombustivel;
	}

	/**
	 * CONSTRUTOR
	 * 
	 * @param controller
	 */

	public NfeDetEspecificoCombustivelFormView(
			ProdutoServicoFormController controller) {
		this.controller = controller;
	}

	/**
	 * GET / SET
	 */

	@AutoGenerated
	public VerticalLayout bvlNdeCombustivel() {
		// common part: create layout
		vlNdeCombustivel = new VerticalLayout();
		vlNdeCombustivel.setImmediate(false);
		vlNdeCombustivel.setWidth("100.0%");
		vlNdeCombustivel.setHeight("100.0%");
		vlNdeCombustivel.setMargin(true);
		vlNdeCombustivel.setSpacing(true);

		// panel_2
		vlNdeCombustivel.addComponent(bplNdeCombustivel());

		return vlNdeCombustivel;
	}

	@AutoGenerated
	private Panel bplNdeCombustivel() {
		// common part: create layout
		plNdeCombustivel = new Panel();
		plNdeCombustivel.setImmediate(false);
		plNdeCombustivel.setSizeFull();

		plNdeCombustivel.setContent(bglNdeCombustivel());

		return plNdeCombustivel;
	}

	@AutoGenerated
	private GridLayout bglNdeCombustivel() {
		// common part: create layout
		glNdeCombustivel = new GridLayout();
		glNdeCombustivel.setImmediate(false);
		glNdeCombustivel.setSizeUndefined();
		glNdeCombustivel.setMargin(true);
		glNdeCombustivel.setSpacing(true);
		glNdeCombustivel.setRows(4);
		glNdeCombustivel.setColumns(4);
		glNdeCombustivel.setEnabled(false);

		// tfCodigoAnpCombustivel
		tfCodigoAnpCombustivel = new TextField("Código ANP:");
		tfCodigoAnpCombustivel.setWidth("-1px");
		tfCodigoAnpCombustivel.setHeight("-1px");
		tfCodigoAnpCombustivel.setSizeFull();
		tfCodigoAnpCombustivel.setNullRepresentation("");
		tfCodigoAnpCombustivel.setImmediate(true);
		tfCodigoAnpCombustivel.setId("tfCodigoAnpCombustivel");
		tfCodigoAnpCombustivel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateInteger(event)) {
								controller.ndeCombustivelSetarValor(
										tfCodigoAnpCombustivel.getId(), event
												.getProperty().getValue());
							}
						}
					}
				});
		glNdeCombustivel.addComponent(tfCodigoAnpCombustivel, 0, 0);

		// tfCodifCombustivel
		tfCodifCombustivel = new TextField("CODIF:");
		tfCodifCombustivel.setWidth("-1px");
		tfCodifCombustivel.setHeight("-1px");
		tfCodifCombustivel.setSizeFull();
		tfCodifCombustivel.setNullRepresentation("");
		tfCodifCombustivel.setImmediate(true);
		tfCodifCombustivel.setId("tfCodifCombustivel");
		tfCodifCombustivel.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeCombustivelSetarValor(tfCodifCombustivel
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeCombustivel.addComponent(tfCodifCombustivel, 1, 0);

		// tfQtdeTempAmbienteCombustivel
		tfQtdeTempAmbienteCombustivel = new TextField("Qtde temp ambiente:");
		tfQtdeTempAmbienteCombustivel.setWidth("-1px");
		tfQtdeTempAmbienteCombustivel.setHeight("-1px");
		tfQtdeTempAmbienteCombustivel.setSizeFull();
		tfQtdeTempAmbienteCombustivel.setNullRepresentation("");
		tfQtdeTempAmbienteCombustivel.setImmediate(true);
		tfQtdeTempAmbienteCombustivel.setId("tfQtdeTempAmbienteCombustivel");
		tfQtdeTempAmbienteCombustivel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.ndeCombustivelSetarValor(
										tfQtdeTempAmbienteCombustivel.getId(),
										event.getProperty().getValue());
							}
						}
					}
				});
		glNdeCombustivel.addComponent(tfQtdeTempAmbienteCombustivel, 2, 0);

		// tfUfConsumoCombustivel
		tfUfConsumoCombustivel = new TextField("UF consumo:");
		tfUfConsumoCombustivel.setWidth("-1px");
		tfUfConsumoCombustivel.setHeight("-1px");
		tfUfConsumoCombustivel.setSizeFull();
		tfUfConsumoCombustivel.setNullRepresentation("");
		tfUfConsumoCombustivel.setImmediate(true);
		tfUfConsumoCombustivel.setId("tfUfConsumoCombustivel");
		tfUfConsumoCombustivel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeCombustivelSetarValor(
									tfUfConsumoCombustivel.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeCombustivel.addComponent(tfUfConsumoCombustivel, 3, 0);

		// tfBcCideCombustivel
		tfBcCideCombustivel = new TextField("BC CIDE:");
		tfBcCideCombustivel.setWidth("-1px");
		tfBcCideCombustivel.setHeight("-1px");
		tfBcCideCombustivel.setSizeFull();
		tfBcCideCombustivel.setNullRepresentation("");
		tfBcCideCombustivel.setImmediate(true);
		tfBcCideCombustivel.setId("tfBcCideCombustivel");
		tfBcCideCombustivel.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndeCombustivelSetarValor(tfBcCideCombustivel
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdeCombustivel.addComponent(tfBcCideCombustivel, 0, 1);

		// tfAliquotaCideCombustivel
		tfAliquotaCideCombustivel = new TextField("Alíquota CIDE:");
		tfAliquotaCideCombustivel.setWidth("-1px");
		tfAliquotaCideCombustivel.setHeight("-1px");
		tfAliquotaCideCombustivel.setSizeFull();
		tfAliquotaCideCombustivel.setNullRepresentation("");
		tfAliquotaCideCombustivel.setImmediate(true);
		tfAliquotaCideCombustivel.setId("tfAliquotaCideCombustivel");
		tfAliquotaCideCombustivel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.ndeCombustivelSetarValor(
										tfAliquotaCideCombustivel.getId(),
										event.getProperty().getValue());
							}
						}
					}
				});
		glNdeCombustivel.addComponent(tfAliquotaCideCombustivel, 1, 1);

		// tfValorCideCombustivel
		tfValorCideCombustivel = new TextField("Valor CIDE:");
		tfValorCideCombustivel.setWidth("-1px");
		tfValorCideCombustivel.setHeight("-1px");
		tfValorCideCombustivel.setSizeFull();
		tfValorCideCombustivel.setNullRepresentation("");
		tfValorCideCombustivel.setImmediate(true);
		tfValorCideCombustivel.setId("tfValorCideCombustivel");
		tfValorCideCombustivel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.ndeCombustivelSetarValor(
										tfValorCideCombustivel.getId(), event
												.getProperty().getValue());
							}
						}
					}
				});
		glNdeCombustivel.addComponent(tfValorCideCombustivel, 2, 1);

		return glNdeCombustivel;
	}

}