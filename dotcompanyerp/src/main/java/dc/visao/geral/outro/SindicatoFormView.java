package dc.visao.geral.outro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

import dc.controller.geral.outro.SindicatoFormController;
import dc.visao.framework.util.ComponentUtil;

public class SindicatoFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private TextField tfNome;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;

	@AutoGenerated
	private TextField tfCodigoBanco;

	@AutoGenerated
	private TextField tfCodigoAgencia;

	@AutoGenerated
	private TextField tfContaBanco;

	@AutoGenerated
	private TextField tfCodigoCedente;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_4;

	@AutoGenerated
	private TextField tfLogradouro;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_5;

	@AutoGenerated
	private TextField tfNumero;

	@AutoGenerated
	private TextField tfBairro;

	@AutoGenerated
	private TextField tfMunicipioIbge;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_6;

	@AutoGenerated
	private TextField tfEmail;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_7;

	@AutoGenerated
	private PopupDateField pdfDataBase;

	@AutoGenerated
	private ComboBox cbTipo;

	@AutoGenerated
	private TextField tfPisoSalarial;

	@AutoGenerated
	private TextField tfCnpj, tfTelefone1, tfTelefone2;

	//@AutoGenerated
	//private ManyToOneCombo<UfEntity> mocUf;
	
	@AutoGenerated
	private ComboBox mocUf;

	private SindicatoFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SindicatoFormView(SindicatoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfCodigoBanco() {
		return tfCodigoBanco;
	}

	public void setTfCodigoBanco(TextField tfCodigoBanco) {
		this.tfCodigoBanco = tfCodigoBanco;
	}

	public TextField getTfCodigoAgencia() {
		return tfCodigoAgencia;
	}

	public void setTfCodigoAgencia(TextField tfCodigoAgencia) {
		this.tfCodigoAgencia = tfCodigoAgencia;
	}

	public TextField getTfContaBanco() {
		return tfContaBanco;
	}

	public void setTfContaBanco(TextField tfContaBanco) {
		this.tfContaBanco = tfContaBanco;
	}

	public TextField getTfCodigoCedente() {
		return tfCodigoCedente;
	}

	public void setTfCodigoCedente(TextField tfCodigoCedente) {
		this.tfCodigoCedente = tfCodigoCedente;
	}

	public TextField getTfLogradouro() {
		return tfLogradouro;
	}

	public void setTfLogradouro(TextField tfLogradouro) {
		this.tfLogradouro = tfLogradouro;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public TextField getTfBairro() {
		return tfBairro;
	}

	public void setTfBairro(TextField tfBairro) {
		this.tfBairro = tfBairro;
	}

	public TextField getTfMunicipioIbge() {
		return tfMunicipioIbge;
	}

	public void setTfMunicipioIbge(TextField tfMunicipioIbge) {
		this.tfMunicipioIbge = tfMunicipioIbge;
	}

	public TextField getTfTelefone1() {
		return tfTelefone1;
	}

	public void setTfTelefone1(TextField tfTelefone1) {
		this.tfTelefone1 = tfTelefone1;
	}

	public TextField getTfTelefone2() {
		return tfTelefone2;
	}

	public void setTfTelefone2(TextField tfTelefone2) {
		this.tfTelefone2 = tfTelefone2;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public PopupDateField getPdfDataBase() {
		return pdfDataBase;
	}

	public void setPdfDataBase(PopupDateField pdfDataBase) {
		this.pdfDataBase = pdfDataBase;
	}

	public ComboBox getCbTipo() {
		return cbTipo;
	}

	public void setCbTipo(ComboBox cbTipo) {
		this.cbTipo = cbTipo;
	}

	public TextField getTfPisoSalarial() {
		return tfPisoSalarial;
	}

	public void setTfPisoSalarial(TextField tfPisoSalarial) {
		this.tfPisoSalarial = tfPisoSalarial;
	}

	public TextField getTfCnpj() {
		return tfCnpj;
	}

	public void setTfCnpj(TextField tfCnpj) {
		this.tfCnpj = tfCnpj;
	}

	public ComboBox getMocUf() {
		return mocUf;
	}

	public void setMocUf(ComboBox mocUf) {
		this.mocUf = mocUf;
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
		mainLayout.setRows(7);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 0);

		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		mainLayout.addComponent(horizontalLayout_3, 0, 1);

		// horizontalLayout_4
		horizontalLayout_4 = buildHorizontalLayout_4();
		mainLayout.addComponent(horizontalLayout_4, 0, 2);

		// horizontalLayout_5
		horizontalLayout_5 = buildHorizontalLayout_5();
		mainLayout.addComponent(horizontalLayout_5, 0, 3);

		// horizontalLayout_6
		horizontalLayout_6 = buildHorizontalLayout_6();
		mainLayout.addComponent(horizontalLayout_6, 0, 4);

		// horizontalLayout_7
		horizontalLayout_7 = buildHorizontalLayout_7();
		mainLayout.addComponent(horizontalLayout_7, 0, 5);

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);

		// tfNome
		tfNome = new TextField();
		tfNome.setCaption("Nome");
		tfNome.setImmediate(false);
		tfNome.setNullRepresentation("");
		tfNome.setWidth("600px");
		tfNome.setHeight("-1px");
		horizontalLayout_1.addComponent(tfNome);

		return horizontalLayout_1;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("-1px");
		horizontalLayout_3.setHeight("-1px");
		horizontalLayout_3.setMargin(false);
		horizontalLayout_3.setSpacing(true);

		// tfCodigoBanco
		tfCodigoBanco = new TextField();
		tfCodigoBanco.setCaption("Código do banco");
		tfCodigoBanco.setImmediate(false);
		tfCodigoBanco.setNullRepresentation("");
		tfCodigoBanco.setWidth("-1px");
		tfCodigoBanco.setHeight("-1px");
		horizontalLayout_3.addComponent(tfCodigoBanco);

		// tfCodigoAgencia
		tfCodigoAgencia = new TextField();
		tfCodigoAgencia.setCaption("Código da agência");
		tfCodigoAgencia.setImmediate(false);
		tfCodigoAgencia.setNullRepresentation("");
		tfCodigoAgencia.setWidth("123px");
		tfCodigoAgencia.setHeight("-1px");
		horizontalLayout_3.addComponent(tfCodigoAgencia);

		// tfContaBanco
		tfContaBanco = new TextField();
		tfContaBanco.setCaption("Conta do banco");
		tfContaBanco.setImmediate(false);
		tfContaBanco.setNullRepresentation("");
		tfContaBanco.setWidth("134px");
		tfContaBanco.setHeight("-1px");
		horizontalLayout_3.addComponent(tfContaBanco);

		// tfCodigoCedente
		tfCodigoCedente = new TextField();
		tfCodigoCedente.setCaption("Código do cedente");
		tfCodigoCedente.setImmediate(false);
		tfCodigoCedente.setNullRepresentation("");
		tfCodigoCedente.setWidth("134px");
		tfCodigoCedente.setHeight("-1px");
		horizontalLayout_3.addComponent(tfCodigoCedente);

		return horizontalLayout_3;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_4() {
		// common part: create layout
		horizontalLayout_4 = new HorizontalLayout();
		horizontalLayout_4.setImmediate(false);
		horizontalLayout_4.setWidth("-1px");
		horizontalLayout_4.setHeight("-1px");
		horizontalLayout_4.setMargin(false);
		horizontalLayout_4.setSpacing(true);

		// tfLogradouro
		tfLogradouro = new TextField();
		tfLogradouro.setCaption("Logradouro");
		tfLogradouro.setImmediate(false);
		tfLogradouro.setNullRepresentation("");
		tfLogradouro.setRequired(true);
		tfLogradouro.setWidth("600px");
		tfLogradouro.setHeight("-1px");
		horizontalLayout_4.addComponent(tfLogradouro);

		// mocUf
		mocUf = ComponentUtil.buildComboBox("UF");
		horizontalLayout_4.addComponent(mocUf);

		return horizontalLayout_4;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_5() {
		// common part: create layout
		horizontalLayout_5 = new HorizontalLayout();
		horizontalLayout_5.setImmediate(false);
		horizontalLayout_5.setWidth("-1px");
		horizontalLayout_5.setHeight("-1px");
		horizontalLayout_5.setMargin(false);
		horizontalLayout_5.setSpacing(true);

		// tfNumero
		tfNumero = new TextField();
		tfNumero.setCaption("Número");
		tfNumero.setImmediate(false);
		tfNumero.setNullRepresentation("");
		tfNumero.setWidth("180px");
		tfNumero.setHeight("-1px");
		horizontalLayout_5.addComponent(tfNumero);

		// tfBairro
		tfBairro = new TextField();
		tfBairro.setCaption("Bairro");
		tfBairro.setImmediate(false);
		tfBairro.setNullRepresentation("");
		tfBairro.setWidth("280px");
		tfBairro.setHeight("-1px");
		horizontalLayout_5.addComponent(tfBairro);

		// tfMunicipioIbge
		tfMunicipioIbge = new TextField();
		tfMunicipioIbge.setCaption("Município IBGE");
		tfMunicipioIbge.setImmediate(false);
		tfMunicipioIbge.setNullRepresentation("");
		tfMunicipioIbge.setWidth("180px");
		tfMunicipioIbge.setHeight("-1px");
		horizontalLayout_5.addComponent(tfMunicipioIbge);

		// tfTelefone1
		tfTelefone1 = ComponentUtil.buildPhoneField("Telefone 1");
		tfTelefone1.setImmediate(false);
		tfTelefone1.setNullRepresentation("");
		tfTelefone1.setWidth("180px");
		tfTelefone1.setHeight("-1px");
		horizontalLayout_5.addComponent(tfTelefone1);

		// tfTelefone2
		tfTelefone2 = ComponentUtil.buildPhoneField("Telefone 2");
		tfTelefone2.setImmediate(false);
		tfTelefone2.setNullRepresentation("");
		tfTelefone2.setWidth("180px");
		tfTelefone2.setHeight("-1px");
		horizontalLayout_5.addComponent(tfTelefone2);

		return horizontalLayout_5;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_6() {
		// common part: create layout
		horizontalLayout_6 = new HorizontalLayout();
		horizontalLayout_6.setImmediate(false);
		horizontalLayout_6.setWidth("-1px");
		horizontalLayout_6.setHeight("-1px");
		horizontalLayout_6.setMargin(false);
		horizontalLayout_6.setSpacing(true);

		// tfEmail
		tfEmail = new TextField();
		tfEmail.setCaption("Email");
		tfEmail.setImmediate(false);
		tfEmail.setNullRepresentation("");
		tfEmail.setWidth("680px");
		tfEmail.setHeight("-1px");
		horizontalLayout_6.addComponent(tfEmail);

		return horizontalLayout_6;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_7() {
		// common part: create layout
		horizontalLayout_7 = new HorizontalLayout();
		horizontalLayout_7.setImmediate(false);
		horizontalLayout_7.setWidth("-1px");
		horizontalLayout_7.setHeight("-1px");
		horizontalLayout_7.setMargin(false);
		horizontalLayout_7.setSpacing(true);

		// cbTipo
		cbTipo = new ComboBox();
		cbTipo.setCaption("Tipo");
		cbTipo.setWidth("180px");
		cbTipo.setHeight("-1px");
		horizontalLayout_7.addComponent(cbTipo);

		// pdfDataBase
		pdfDataBase = new PopupDateField();
		pdfDataBase.setCaption("Data base");
		pdfDataBase.setImmediate(false);
		pdfDataBase.setWidth("-1px");
		pdfDataBase.setHeight("-1px");
		horizontalLayout_7.addComponent(pdfDataBase);

		// tfPisoSalarial
		tfPisoSalarial = new TextField();
		tfPisoSalarial.setCaption("Piso salarial");
		tfPisoSalarial.setImmediate(false);
		tfPisoSalarial.setNullRepresentation("");
		tfPisoSalarial.setWidth("180px");
		tfPisoSalarial.setHeight("-1px");
		horizontalLayout_7.addComponent(tfPisoSalarial);

		// tfCnpj
		tfCnpj = ComponentUtil.buildCnpjField("CNPJ");
		tfCnpj.setImmediate(false);
		tfCnpj.setNullRepresentation("");
		tfCnpj.setWidth("180px");
		tfCnpj.setHeight("-1px");
		horizontalLayout_7.addComponent(tfCnpj);

		return horizontalLayout_7;
	}

}