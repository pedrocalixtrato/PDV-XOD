package dc.visao.ordemservico;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.ordemservico.SubGrupoFormController;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class SubGrupoFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private VerticalLayout mainLayout;
	
	@AutoGenerated
	private TextField txtNome;
	
	@AutoGenerated
	private ManyToOneComboField<GrupoOsEntity> cbGrupo;
	
	SubGrupoFormController controller;
	
	public SubGrupoFormView(SubGrupoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
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

		return mainLayout;
	}

	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
//		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(3);
		fields.setColumns(4);

		txtNome = ComponentUtil.buildTextField("Nome");
		fields.addComponent(txtNome,0,1,1,1);

		cbGrupo = new ManyToOneComboField<>(GrupoOsEntity.class);
		cbGrupo.setCaption("Grupo");
		fields.addComponent(cbGrupo,0,2,0,2);
		
		return fields;

	}


	public GridLayout getFields() {
		return fields;
	}
	public void setFields(GridLayout fields) {
		this.fields = fields;
	}
	public VerticalLayout getMainLayout() {
		return mainLayout;
	}
	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}
	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public ManyToOneComboField<GrupoOsEntity> getCbGrupo() {
		return cbGrupo;
	}

	public void setCbGrupo(ManyToOneComboField<GrupoOsEntity> cbGrupo) {
		this.cbGrupo = cbGrupo;
	}
}

