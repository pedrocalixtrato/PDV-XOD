package dc.visao.geral.pessoal;

import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.pessoal.ContadorFormController;
import dc.visao.framework.util.ComponentUtil;

public class ContadorFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout fields;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TextField txtEmail;
	
	@AutoGenerated
	private MaskedTextField txtCep, txtCnpj, txtCpf;
	
	@AutoGenerated
	private TextField txtTelefone, txtFax;

	@AutoGenerated
	private TextField txtMunicipioIbge;
	@AutoGenerated
	private TextField txtBairro;
	@AutoGenerated
	private TextField txtNumero;
	@AutoGenerated
	private TextField txtComplemento;
	@AutoGenerated
	private TextField txtLogradouro;
	@AutoGenerated
	private TextField txtUfCRC;
	@AutoGenerated
	private TextField txtNumeroCRC;
	
	@AutoGenerated
	private TextField txtNome;
	@AutoGenerated
	private TextField txtSite;
	
	ContadorFormController controller;
	
	//@AutoGenerated
	//private ManyToOneComboField<UfEntity> mocUf;
	
	@AutoGenerated
	private ComboBox mocUf;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ContadorFormView(ContadorFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(7);
		fields.setColumns(7);

		// txtNumeroCRC
		txtNumeroCRC = ComponentUtil.buildTextField("Número CRC");
		txtNumeroCRC.setImmediate(false);
		fields.addComponent(txtNumeroCRC, 0, 1);

		// txtUfCRC
		txtUfCRC = ComponentUtil.buildTextField("Uf CRC");
		txtUfCRC.setImmediate(false);
		fields.addComponent(txtUfCRC, 1, 1);
		
		// txtNome
		txtNome = ComponentUtil.buildTextField("Nome");
		txtNome.setImmediate(false);
		fields.addComponent(txtNome, 2, 1, 5,1);
		
		// txtCpf
		txtCpf = ComponentUtil.buildMaskedTextField("CPF", "###.###.###-##");
		txtCpf.setMaskClientOnly(true);
		txtCpf.setImmediate(false);
		fields.addComponent(txtCpf, 0, 2);
		
		// txtCnpj
		txtCnpj = ComponentUtil.buildMaskedTextField("CNPJ", "##.###.###/####-##");
		txtCnpj.setMaskClientOnly(true);
		txtCnpj.setImmediate(false);
		fields.addComponent(txtCnpj, 1, 2);

		// txtLogradouro
		txtLogradouro = ComponentUtil.buildTextField("Logradouro");
		txtLogradouro.setImmediate(false);
		fields.addComponent(txtLogradouro, 2, 2, 5,2);

		// txtCep
		txtCep = ComponentUtil.buildMaskedTextField("CEP", "#####-###");
		txtCep.setMaskClientOnly(true);
		txtCep.setImmediate(false);
		fields.addComponent(txtCep, 0, 3);

		// txtComplemento
		txtComplemento = ComponentUtil.buildTextField("Complemento");
		txtComplemento.setImmediate(false);
		fields.addComponent(txtComplemento, 2, 3, 5,3);

		// txtNumero
		txtNumero = ComponentUtil.buildTextField("Número");
		txtNumero.setImmediate(false);
		fields.addComponent(txtNumero, 1, 3);

		// txtBairro
		txtBairro = ComponentUtil.buildTextField("Bairro");
		txtBairro.setImmediate(false);
		fields.addComponent(txtBairro, 0, 4);

		// txtMunicipioIbge
		txtMunicipioIbge = ComponentUtil.buildTextField("Município IBGE");
		txtMunicipioIbge.setImmediate(false);
		fields.addComponent(txtMunicipioIbge, 1, 4);

		// cmbUf
		//mocUf = new ManyToOneComboField<>(UfEntity.class);
		//mocUf.setCaption("UF");
		//mocUf.setImmediate(false);
		mocUf = ComponentUtil.buildComboBox("UF");
		//cmbUf.setContainerDataSource(controller.carregarUFs());
		//cmbUf.setWidth("435px");
		//cmbUf.setHeight("-1px");
		fields.addComponent(mocUf, 2, 4);

		// txtTelefone
		txtTelefone = ComponentUtil.buildPhoneField("Telefone");
		txtTelefone.setImmediate(false);
		fields.addComponent(txtTelefone, 0, 5);

		// txtFax
		txtFax = ComponentUtil.buildPhoneField("Fax");
		txtFax.setImmediate(false);
		fields.addComponent(txtFax, 1, 5);

		// txtEmail
		txtEmail = ComponentUtil.buildTextField("Email");
		txtEmail.setImmediate(false);
		fields.addComponent(txtEmail, 2, 5, 3,5);
		
		// txtSite
		txtSite = ComponentUtil.buildTextField("Site");
		txtSite.setImmediate(false);
		fields.addComponent(txtSite, 4, 5, 5,5);

		return fields;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public VerticalLayout getVerticalLayout() {
		return mainLayout;
	}

	public void setVerticalLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public TextField getTxtFax() {
		return txtFax;
	}

	public void setTxtFax(TextField txtFax) {
		this.txtFax = txtFax;
	}

	public TextField getTxtTelefone() {
		return txtTelefone;
	}

	public void setTxtTelefone(TextField txtTelefone) {
		this.txtTelefone = txtTelefone;
	}

	

	public ComboBox getMocUf() {
		return mocUf;
	}

	public void setMocUf(ComboBox mocUf) {
		this.mocUf = mocUf;
	}

	public TextField getTxtMunicipioIbge() {
		return txtMunicipioIbge;
	}

	public void setTxtMunicipioIbge(TextField txtMunicipioIbge) {
		this.txtMunicipioIbge = txtMunicipioIbge;
	}

	public TextField getTxtBairro() {
		return txtBairro;
	}

	public void setTxtBairro(TextField txtBairro) {
		this.txtBairro = txtBairro;
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

	public MaskedTextField getTxtCep() {
		return txtCep;
	}

	public void setTxtCep(MaskedTextField txtCep) {
		this.txtCep = txtCep;
	}

	public TextField getTxtLogradouro() {
		return txtLogradouro;
	}

	public void setTxtLogradouro(TextField txtLogradouro) {
		this.txtLogradouro = txtLogradouro;
	}

	public TextField getTxtUfCRC() {
		return txtUfCRC;
	}

	public void setTxtUfCRC(TextField txtUfCRC) {
		this.txtUfCRC = txtUfCRC;
	}

	public TextField getTxtNumeroCRC() {
		return txtNumeroCRC;
	}

	public void setTxtNumeroCRC(TextField txtNumeroCRC) {
		this.txtNumeroCRC = txtNumeroCRC;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public MaskedTextField getTxtCpf() {
		return txtCpf;
	}

	public void setTxtCpf(MaskedTextField txtCpf) {
		this.txtCpf = txtCpf;
	}

	public MaskedTextField getTxtCnpj() {
		return txtCnpj;
	}

	public void setTxtCnpj(MaskedTextField txtCnpj) {
		this.txtCnpj = txtCnpj;
	}

	public TextField getTxtSite() {
		return txtSite;
	}

	public void setTxtSite(TextField txtSite) {
		this.txtSite = txtSite;
	}
	
}