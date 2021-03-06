package dc.visao.geral.eventos;

import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.eventos.CerimonialEventosFormController;
import dc.visao.framework.util.ComponentUtil;

public class CerimonialEventosFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private VerticalLayout layout;

	@AutoGenerated
	private GridLayout glGeral;

	@AutoGenerated
	private TextField txtNome, tfEndereco, tfCidade, tfBairro, tfEmail, txtNumero,
			txtComplemento, txtCnpj, tfTelefone, tfCelular, txtContato;

	@AutoGenerated
	private MaskedTextField tfCep;

	private CerimonialEventosFormController controller;
	
	@AutoGenerated
	private ComboBox mocUf;
	
	//@AutoGenerated
	//private ManyToOneCombo<UfEntity> mocUf;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public CerimonialEventosFormView(CerimonialEventosFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here

		this.controller = controller;
	}

	@AutoGenerated
	private void buildMainLayout() {
		// the main layout and components will be created here
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		fields = bglGeral();
		mainLayout.addComponent(fields);

		/*layout = new VerticalLayout();
		layout.setImmediate(false);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setMargin(true);
		layout.setSpacing(true);

		// tsGeral.addTab(bvlInformacaoGeral(), 0);

		mainLayout.addComponent(layout);*/

	}

	/**
	 * GERAL
	 */

	@AutoGenerated
	private GridLayout bglGeral() {
		// common part: create layout
		fields = new GridLayout(5, 5);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		// Nome
		// txtNome= ComponentUtil.buildTextField("Nome");
		txtNome = ComponentUtil.buildTextField("Nome");
		fields.addComponent(txtNome, 0, 0, 1,0);

		// txtCnpj = ComponentUtil.buildTextField("Cnpj");
		txtCnpj = ComponentUtil.buildCnpjField("CNPJ");
		fields.addComponent(txtCnpj, 2, 0);

		// tfEndereco = ComponentUtil.buildTextField("Endereco");
		// fields.addComponent(tfEndereco,3,1,4,1);
		tfEndereco = ComponentUtil.buildTextField("Endereço");
		fields.addComponent(tfEndereco, 0, 1, 1,1);

		// txtComplemento= ComponentUtil.buildTextField("Complemento");
		// fields.addComponent(txtComplemento, 2, 2,3,2);
		txtComplemento = ComponentUtil.buildTextField("Complemento");
		fields.addComponent(txtComplemento, 2, 1, 3,1);

		// txtNumero = ComponentUtil.buildTextField("Numero");
		// fields.addComponent(txtNumero,4,2,4,2);
		txtNumero = ComponentUtil.buildTextField("Numero");
		fields.addComponent(txtNumero, 4, 1);

		// tfBairro = ComponentUtil.buildTextField("Bairro");
		// fields.addComponent(tfBairro, 0, 3,0,3);
		tfBairro = ComponentUtil.buildTextField("Bairro");
		fields.addComponent(tfBairro, 0, 2);

		// tfCidade = ComponentUtil.buildTextField("Cidade");
		// fields.addComponent(tfCidade,2,3,2,3);
		tfCidade = ComponentUtil.buildTextField("Cidade");
		fields.addComponent(tfCidade, 1, 2);

		// tfCep = ComponentUtil.buildTextField("Cep");
		// fields.addComponent(tfCep,3,3,3,3);
		tfCep = ComponentUtil.buildMaskedTextField("CEP", "#####-###");
		tfCep.setMaskClientOnly(true);
		fields.addComponent(tfCep, 2, 2);

		// cmbUf
		
		mocUf = ComponentUtil.buildComboBox("UF");
		mocUf.setImmediate(false);
//		cmbUf = ComponentUtil.buildComboBox("UF");
		// fields.addComponent(cmbUf,4,4,4,4);
		fields.addComponent(mocUf, 0, 3);

		// tfTelefone= ComponentUtil.buildTextField("Telefone");
		// fields.addComponent(tfTelefone,4,3,4,3);
		tfTelefone = ComponentUtil.buildPhoneField("Telefone comercial");
		fields.addComponent(tfTelefone, 1, 3);

		// tfCelular= ComponentUtil.buildTextField("Celular");
		// fields.addComponent(tfCelular,0,4,0,4);
		tfCelular = ComponentUtil.buildPhoneField("Celular");
		fields.addComponent(tfCelular, 2, 3);

		// txtContato = ComponentUtil.buildTextField("Contato");
		// fields.addComponent(txtContato,1,4,1,4);
		txtContato = ComponentUtil.buildPhoneField("Contato");
		fields.addComponent(txtContato, 3, 3);

		// tfEmail = ComponentUtil.buildTextField("Email");
		// fields.addComponent(tfEmail,0,5,0,5);
		tfEmail = ComponentUtil.buildTextField("Email");
		fields.addComponent(tfEmail, 0, 4, 1,4);

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

	public TextField getTxtCnpj() {
		return txtCnpj;
	}

	public void setTxtCnpj(TextField txtCnpj) {
		this.txtCnpj = txtCnpj;
	}

	public TextField getTxtContato() {
		return txtContato;
	}

	public void setTxtContato(TextField txtContato) {
		this.txtContato = txtContato;
	}

	public TextField getTfTelefone() {
		return tfTelefone;
	}

	public void setTfTelefone(MaskedTextField tfTelefone) {
		this.tfTelefone = tfTelefone;
	}

	public TextField getTfCelular() {
		return tfCelular;
	}

	public void setTfCelular(TextField tfCelular) {
		this.tfCelular = tfCelular;
	}

	public MaskedTextField getTfCep() {
		return tfCep;
	}

	public void setTfCep(MaskedTextField tfCep) {
		this.tfCep = tfCep;
	}

	public TextField getTfEndereco() {
		return tfEndereco;
	}

	public void setTfEndereco(TextField tfEndereco) {
		this.tfEndereco = tfEndereco;
	}

	public TextField getTfCidade() {
		return tfCidade;
	}

	public void setTfCidade(TextField tfCidade) {
		this.tfCidade = tfCidade;
	}

	public TextField getTfBairro() {
		return tfBairro;
	}

	public void setTfBairro(TextField tfBairro) {
		this.tfBairro = tfBairro;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public TextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public TextField getTxtComplemento() {
		return txtComplemento;
	}

	public void setTxtComplemento(TextField txtComplemento) {
		this.txtComplemento = txtComplemento;
	}
	

	public ComboBox getMocUf() {
		return mocUf;
	}

	public void setMocUf(ComboBox mocUf) {
		this.mocUf = mocUf;
	}

	public CerimonialEventosFormController getController() {
		return controller;
	}

	public void setController(CerimonialEventosFormController controller) {
		this.controller = controller;
	}

	public VerticalLayout getLayout() {
		return layout;
	}

	public void setLayout(VerticalLayout layout) {
		this.layout = layout;
	}

	public GridLayout getGlGeral() {
		return glGeral;
	}

	public void setGlGeral(GridLayout glGeral) {
		this.glGeral = glGeral;
	}
	
}
