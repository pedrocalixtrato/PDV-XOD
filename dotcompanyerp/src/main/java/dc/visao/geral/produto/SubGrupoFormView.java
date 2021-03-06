package dc.visao.geral.produto;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.produto.SubGrupoFormController;
import dc.entidade.geral.produto.GrupoEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class SubGrupoFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;

	@AutoGenerated
	private VerticalLayout verticalLayout_1;

	@AutoGenerated
	private TextArea tfDescricao;

	@AutoGenerated
	private TextField tfNome;

	@AutoGenerated
	private ManyToOneComboField<GrupoEntity> mocGrupoProduto;

	private SubGrupoFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SubGrupoFormView(SubGrupoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	public TextArea getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(TextArea tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public ManyToOneComboField<GrupoEntity> getMocGrupoProduto() {
		return mocGrupoProduto;
	}

	public void setMocGrupoProduto(ManyToOneComboField<GrupoEntity> mocGrupoProduto) {
		this.mocGrupoProduto = mocGrupoProduto;
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1, 0, 0);

		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("-1px");
		verticalLayout_1.setHeight("-1px");
		verticalLayout_1.setMargin(false);
		verticalLayout_1.setSpacing(true);

		// mocGrupoProduto
		mocGrupoProduto = new ManyToOneComboField<>(GrupoEntity.class);
		mocGrupoProduto.setCaption("Grupo de produto");
		mocGrupoProduto.setImmediate(false);
		verticalLayout_1.addComponent(mocGrupoProduto);

		// tfNome
		tfNome = ComponentUtil.buildTextField("Nome");
		tfNome.setImmediate(false);
		tfNome.setWidth("500px");
		tfNome.setHeight("-1px");
		verticalLayout_1.addComponent(tfNome);

		// tfDescricao
		tfDescricao = ComponentUtil.buildTextArea("Descrição");
		tfDescricao.setImmediate(false);
		tfDescricao.setWidth("500px");
		tfDescricao.setHeight("-1px");
		verticalLayout_1.addComponent(tfDescricao);

		return verticalLayout_1;
	}

}