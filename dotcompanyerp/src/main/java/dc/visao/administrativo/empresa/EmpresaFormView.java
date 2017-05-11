package dc.visao.administrativo.empresa;

import java.util.List;

import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.administrativo.empresa.EmpresaFormController;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.framework.SeguimentoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.framework.DcConstants;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class EmpresaFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String NAME_PROPERTY = "seguimento.nome";

	private static final String DISCRIPTION_PROPERTY = "seguimento.descricao";

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_1;

	@AutoGenerated
	private TabSheet tabForm;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_6;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_4;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_5;

	@AutoGenerated
	private TextField tfEmail;

	@AutoGenerated
	private TextField tfCnaePrincipal;

	@AutoGenerated
	private ComboBox cbCnaePrincipal;

	@AutoGenerated
	private TextField tfCodigoGps;

	@AutoGenerated
	private TextField tfUf;

	@AutoGenerated
	private TextField tfMunicipio;

	@AutoGenerated
	private TextField tfAliquotaSat;

	@AutoGenerated
	private TextField tfAliquotaCofins;

	@AutoGenerated
	private TextField tfAliquotaPis;

	@AutoGenerated
	private TextField tfCei;

	@AutoGenerated
	private TextField tfCodigoTerceiros;

	@AutoGenerated
	private TextField tfContato;

	@AutoGenerated
	private ComboBox cbCrt;

	@AutoGenerated
	private TextField tfSuframa;

	@AutoGenerated
	private ComboBox cbTipoRegime;

	@AutoGenerated
	private ComboBox cbTipoEmpresa;

	@AutoGenerated
	private PopupDateField pdfDataInscricaoJuntaComercial;

	@AutoGenerated
	private TextField tfInscricaoJuntaComercial;

	@AutoGenerated
	private TextField tfInscricaoMunicipal;

	@AutoGenerated
	private TextField tfInscricaoEstadualSt;

	@AutoGenerated
	private TextField tfInscricaoEstadual;

	@AutoGenerated
	private TextField mtfCnpj;

	@AutoGenerated
	private PopupDateField pdfDataInicioAtividades;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;

	@AutoGenerated
	private ComboBox cbFpas;

	@AutoGenerated
	private ComboBox cbSindicato;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	@AutoGenerated
	private ComboBox cbContador;

	@AutoGenerated
	private ComboBox cbMatriz;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private TextField tfNomeFantasia;

	@AutoGenerated
	private TextField tfRazaoSocial;

	private TabSheet tabSubForm;

	private VerticalLayout vltEmpresaSeguimento;

	private ManyToOneCombo<SeguimentoEntity> mocSeguimento;

	// @AutoGenerated
	private SubFormComponent<PessoaEnderecoEntity, Integer> sfPessoaEndereco;

	// @AutoGenerated
	private SubFormComponent<EmpresaSeguimento, Integer> sfEmpresaSeguimento;

	private EmpresaFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization. The constructor will not be
	 * automatically regenerated by the visual editor.
	 */
	public EmpresaFormView(EmpresaFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public TextField getTfCnaePrincipal() {
		return tfCnaePrincipal;
	}

	public void setTfCnaePrincipal(TextField tfCnaePrincipal) {
		this.tfCnaePrincipal = tfCnaePrincipal;
	}

	public ComboBox getCbCnaePrincipal() {
		return cbCnaePrincipal;
	}

	public void setCbCnaePrincipal(ComboBox cbCnaePrincipal) {
		this.cbCnaePrincipal = cbCnaePrincipal;
	}

	public TextField getTfCodigoGps() {
		return tfCodigoGps;
	}

	public void setTfCodigoGps(TextField tfCodigoGps) {
		this.tfCodigoGps = tfCodigoGps;
	}

	public TextField getTfUf() {
		return tfUf;
	}

	public void setTfUf(TextField tfUf) {
		this.tfUf = tfUf;
	}

	public TextField getTfMunicipio() {
		return tfMunicipio;
	}

	public void setTfMunicipio(TextField tfMunicipio) {
		this.tfMunicipio = tfMunicipio;
	}

	public TextField getTfAliquotaSat() {
		return tfAliquotaSat;
	}

	public void setTfAliquotaSat(TextField tfAliquotaSat) {
		this.tfAliquotaSat = tfAliquotaSat;
	}

	public TextField getTfAliquotaCofins() {
		return tfAliquotaCofins;
	}

	public void setTfAliquotaCofins(TextField tfAliquotaCofins) {
		this.tfAliquotaCofins = tfAliquotaCofins;
	}

	public TextField getTfAliquotaPis() {
		return tfAliquotaPis;
	}

	public void setTfAliquotaPis(TextField tfAliquotaPis) {
		this.tfAliquotaPis = tfAliquotaPis;
	}

	public TextField getTfCei() {
		return tfCei;
	}

	public void setTfCei(TextField tfCei) {
		this.tfCei = tfCei;
	}

	public TextField getTfCodigoTerceiros() {
		return tfCodigoTerceiros;
	}

	public void setTfCodigoTerceiros(TextField tfCodigoTerceiros) {
		this.tfCodigoTerceiros = tfCodigoTerceiros;
	}

	public TextField getTfContato() {
		return tfContato;
	}

	public void setTfContato(TextField tfContato) {
		this.tfContato = tfContato;
	}

	public ComboBox getCbCrt() {
		return cbCrt;
	}

	public void setCbCrt(ComboBox cbCrt) {
		this.cbCrt = cbCrt;
	}

	public TextField getTfSuframa() {
		return tfSuframa;
	}

	public void setTfSuframa(TextField tfSuframa) {
		this.tfSuframa = tfSuframa;
	}

	public ComboBox getCbTipoRegime() {
		return cbTipoRegime;
	}

	public void setCbTipoRegime(ComboBox cbTipoRegime) {
		this.cbTipoRegime = cbTipoRegime;
	}

	public ComboBox getCbTipoEmpresa() {
		return cbTipoEmpresa;
	}

	public void setCbTipoEmpresa(ComboBox cbTipoEmpresa) {
		this.cbTipoEmpresa = cbTipoEmpresa;
	}

	public PopupDateField getPdfDataInscricaoJuntaComercial() {
		return pdfDataInscricaoJuntaComercial;
	}

	public void setPdfDataInscricaoJuntaComercial(
			PopupDateField pdfDataInscricaoJuntaComercial) {
		this.pdfDataInscricaoJuntaComercial = pdfDataInscricaoJuntaComercial;
	}

	public TextField getTfInscricaoJuntaComercial() {
		return tfInscricaoJuntaComercial;
	}

	public void setTfInscricaoJuntaComercial(TextField tfInscricaoJuntaComercial) {
		this.tfInscricaoJuntaComercial = tfInscricaoJuntaComercial;
	}

	public TextField getTfInscricaoMunicipal() {
		return tfInscricaoMunicipal;
	}

	public void setTfInscricaoMunicipal(TextField tfInscricaoMunicipal) {
		this.tfInscricaoMunicipal = tfInscricaoMunicipal;
	}

	public TextField getTfInscricaoEstadualSt() {
		return tfInscricaoEstadualSt;
	}

	public void setTfInscricaoEstadualSt(TextField tfInscricaoEstadualSt) {
		this.tfInscricaoEstadualSt = tfInscricaoEstadualSt;
	}

	public TextField getTfInscricaoEstadual() {
		return tfInscricaoEstadual;
	}

	public void setTfInscricaoEstadual(TextField tfInscricaoEstadual) {
		this.tfInscricaoEstadual = tfInscricaoEstadual;
	}

	public TextField getMtfCnpj() {
		return mtfCnpj;
	}

	public void setMtfCnpj(TextField mtfCnpj) {
		this.mtfCnpj = mtfCnpj;
	}

	public PopupDateField getPdfDataInicioAtividades() {
		return pdfDataInicioAtividades;
	}

	public void setPdfDataInicioAtividades(
			PopupDateField pdfDataInicioAtividades) {
		this.pdfDataInicioAtividades = pdfDataInicioAtividades;
	}

	public ComboBox getCbFpas() {
		return cbFpas;
	}

	public void setCbFpas(ComboBox cbFpas) {
		this.cbFpas = cbFpas;
	}

	public ComboBox getCbSindicato() {
		return cbSindicato;
	}

	public void setCbSindicato(ComboBox cbSindicato) {
		this.cbSindicato = cbSindicato;
	}

	public ComboBox getCbContador() {
		return cbContador;
	}

	public void setCbContador(ComboBox cbContador) {
		this.cbContador = cbContador;
	}

	public ComboBox getCbMatriz() {
		return cbMatriz;
	}

	public void setCbMatriz(ComboBox cbMatriz) {
		this.cbMatriz = cbMatriz;
	}

	public TextField getTfNomeFantasia() {
		return tfNomeFantasia;
	}

	public void setTfNomeFantasia(TextField tfNomeFantasia) {
		this.tfNomeFantasia = tfNomeFantasia;
	}

	public TextField getTfRazaoSocial() {
		return tfRazaoSocial;
	}

	public void setTfRazaoSocial(TextField tfRazaoSocial) {
		this.tfRazaoSocial = tfRazaoSocial;
	}

	public ManyToOneCombo<SeguimentoEntity> getMocSeguimento() {
		return mocSeguimento;
	}

	public void setMocSeguimento(ManyToOneCombo<SeguimentoEntity> mocSeguimento) {
		this.mocSeguimento = mocSeguimento;
	}

	public SubFormComponent<EmpresaSeguimento, Integer> getSfEmpresaSeguimento() {
		return sfEmpresaSeguimento;
	}

	public void setSfEmpresaSeguimento(
			SubFormComponent<EmpresaSeguimento, Integer> sfEmpresaSeguimento) {
		this.sfEmpresaSeguimento = sfEmpresaSeguimento;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		setHeight("100%");
		// top-level component properties

		mainLayout.setSizeFull();
		tabForm = buildTabForm();
		tabForm.setSizeFull();

		mainLayout.addComponent(tabForm);

		return mainLayout;
	}

	public VerticalLayout montaAbaDadosGerais() {
		tfRazaoSocial = ComponentUtil.buildTextField("Razão social");
		tfRazaoSocial.setRequired(true);

		tfNomeFantasia = ComponentUtil.buildTextField("Nome fantasia");
		tfNomeFantasia.setRequired(true);

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.addComponent(buildHorizontalLayout_1());
		layout.addComponent(buildHorizontalLayout_2());
		layout.addComponent(buildHorizontalLayout_3());
		layout.addComponent(montaLayout());

		return layout;
	}

	public VerticalLayout vlAbaEmpresaSeguimento() {
		vltEmpresaSeguimento = new VerticalLayout();
		vltEmpresaSeguimento.setImmediate(false);
		vltEmpresaSeguimento.setSizeFull();
		vltEmpresaSeguimento.setMargin(true);
		vltEmpresaSeguimento.setSpacing(true);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setMargin(false);
		hl.setSpacing(true);

		mocSeguimento = new ManyToOneCombo<SeguimentoEntity>();
		mocSeguimento.setCaption("Seguimento");
		mocSeguimento.setWidth("400px");
		mocSeguimento.setHeight("-1px");

		hl.addComponent(mocSeguimento);

		vltEmpresaSeguimento.addComponent(hl);

		vltEmpresaSeguimento.addComponent(bplEmpresaSeguimentoSubForm());

		return vltEmpresaSeguimento;
	}

	public void montaAbaEndereco() {
		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();

		tfRazaoSocial = ComponentUtil.buildTextField("Razão social");
		tfRazaoSocial.setRequired(true);

		layout.addComponent(tfRazaoSocial, 0, 0);
		tabSubForm.addTab(layout, "Endereço", null);
	}

	public void montaAbaContatos() {
		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();

		tfRazaoSocial = ComponentUtil.buildTextField("Razão social");
		tfRazaoSocial.setRequired(true);

		layout.addComponent(tfRazaoSocial, 0, 0);
		tabSubForm.addTab(layout, "Contatos", null);
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
		// gridLayout_1 = buildGridLayout_1();
		verticalLayout_2.addComponent(new TextField());

		return verticalLayout_2;
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

		// tfRazaoSocial
		tfRazaoSocial = new TextField();
		tfRazaoSocial.setRequired(true);
		tfRazaoSocial.setCaption("Razão social");
		tfRazaoSocial.setImmediate(false);
		tfRazaoSocial.setWidth("400px");
		tfRazaoSocial.setHeight("-1px");
		horizontalLayout_1.addComponent(tfRazaoSocial);

		// tfNomeFantasia
		tfNomeFantasia = new TextField();
		tfNomeFantasia.setRequired(true);
		tfNomeFantasia.setCaption("Nome fantasia");
		tfNomeFantasia.setImmediate(false);
		tfNomeFantasia.setWidth("454px");
		tfNomeFantasia.setHeight("-1px");
		horizontalLayout_1.addComponent(tfNomeFantasia);

		return horizontalLayout_1;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);

		// cbMatriz
		cbMatriz = new ComboBox();
		cbMatriz.setCaption("Matriz");
		cbMatriz.setImmediate(false);
		cbMatriz.setWidth("400px");
		cbMatriz.setHeight("-1px");
		horizontalLayout_2.addComponent(cbMatriz);

		// cbContador
		cbContador = new ComboBox();
		cbContador.setCaption("Contador");
		cbContador.setImmediate(false);
		cbContador.setWidth("454px");
		cbContador.setHeight("-1px");
		horizontalLayout_2.addComponent(cbContador);

		return horizontalLayout_2;
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

		// cbSindicato
		cbSindicato = new ComboBox();
		cbSindicato.setCaption("Sindicato");
		cbSindicato.setImmediate(false);
		cbSindicato.setWidth("400px");
		cbSindicato.setHeight("-1px");
		horizontalLayout_3.addComponent(cbSindicato);

		// cbFpas
		cbFpas = new ComboBox();
		cbFpas.setCaption("Fpas");
		cbFpas.setImmediate(false);
		cbFpas.setWidth("454px");
		cbFpas.setHeight("-1px");
		horizontalLayout_3.addComponent(cbFpas);

		return horizontalLayout_3;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_1() {
		// common part: create layout
		absoluteLayout_1 = new AbsoluteLayout();
		absoluteLayout_1.setImmediate(false);
		absoluteLayout_1.setWidth("100.0%");
		absoluteLayout_1.setHeight("100.0%");

		// tabForm
		tabForm = new TabSheet();
		tabForm.setHeight("50%");
		buildTabForm();
		absoluteLayout_1.addComponent(tabForm, "top:0.0px;left:20.0px;");

		return absoluteLayout_1;
	}

	@AutoGenerated
	private TabSheet buildTabForm() {
		// common part: create layout

		// absoluteLayout_2
		tabForm = new TabSheet();
		tabForm.addTab(montaAbaDadosGerais(), "Dados gerais", null);
		tabForm.addTab(bplPessoaEnderecoSubForm(), "Endereço", null);
		tabForm.addTab(vlAbaEmpresaSeguimento(), "Seguimento", null);

		return tabForm;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setCaption("Dados");
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");

		// absoluteLayout_5
		absoluteLayout_5 = buildAbsoluteLayout_5();
		absoluteLayout_2.addComponent(absoluteLayout_5,
				"top:-2.0px;left:-1.0px;");

		return absoluteLayout_2;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_5() {
		// common part: create layout
		absoluteLayout_5 = new AbsoluteLayout();
		absoluteLayout_5.setImmediate(false);
		absoluteLayout_5.setWidth("100.0%");
		absoluteLayout_5.setHeight("100.0%");
		absoluteLayout_5.setVisible(true);
		// pdfDataInicioAtividades
		pdfDataInicioAtividades = new PopupDateField();
		pdfDataInicioAtividades.setCaption("Início das atividades");
		pdfDataInicioAtividades.setImmediate(false);
		pdfDataInicioAtividades.setWidth("135px");
		pdfDataInicioAtividades.setHeight("-1px");
		absoluteLayout_5.addComponent(pdfDataInicioAtividades,
				"top:20.0px;left:25.0px;");

		// txtCnpj
		absoluteLayout_5.addComponent(mtfCnpj, "top:19.0px;left:180.0px;");

		// tfInscricaoEstadual
		tfInscricaoEstadual = new TextField();
		tfInscricaoEstadual.setCaption("Inscrição estadual");
		tfInscricaoEstadual.setImmediate(false);
		tfInscricaoEstadual.setWidth("-1px");
		tfInscricaoEstadual.setHeight("-1px");
		absoluteLayout_5.addComponent(tfInscricaoEstadual,
				"top:19.0px;left:360.0px;");

		// tfInscricaoEstadualSt
		tfInscricaoEstadualSt = new TextField();
		tfInscricaoEstadualSt.setCaption("Inscrição estadual ST");
		tfInscricaoEstadualSt.setImmediate(false);
		tfInscricaoEstadualSt.setWidth("-1px");
		tfInscricaoEstadualSt.setHeight("-1px");
		absoluteLayout_5.addComponent(tfInscricaoEstadualSt,
				"top:19.0px;left:520.0px;");

		// tfInscricaoMunicipal
		tfInscricaoMunicipal = new TextField();
		tfInscricaoMunicipal.setCaption("Inscrição municipal");
		tfInscricaoMunicipal.setImmediate(false);
		tfInscricaoMunicipal.setWidth("-1px");
		tfInscricaoMunicipal.setHeight("-1px");
		absoluteLayout_5.addComponent(tfInscricaoMunicipal,
				"top:19.0px;left:680.0px;");

		// tfInscricaoJuntaComercial
		tfInscricaoJuntaComercial = new TextField();
		tfInscricaoJuntaComercial.setCaption("Inscrição na junta comercial");
		tfInscricaoJuntaComercial.setImmediate(false);
		tfInscricaoJuntaComercial.setWidth("-1px");
		tfInscricaoJuntaComercial.setHeight("-1px");
		absoluteLayout_5.addComponent(tfInscricaoJuntaComercial,
				"top:20.0px;left:839.0px;");

		// pdfDataInscricaoJuntaComercial
		pdfDataInscricaoJuntaComercial = new PopupDateField();
		pdfDataInscricaoJuntaComercial
				.setCaption("Inscrição na junta comercial");
		pdfDataInscricaoJuntaComercial.setImmediate(false);
		pdfDataInscricaoJuntaComercial.setWidth("160px");
		pdfDataInscricaoJuntaComercial.setHeight("-1px");
		absoluteLayout_5.addComponent(pdfDataInscricaoJuntaComercial,
				"top:21.0px;left:1000.0px;");

		// cbTipoEmpresa
		cbTipoEmpresa = new ComboBox();
		cbTipoEmpresa.setCaption("Tipo de empresa");
		cbTipoEmpresa.setImmediate(false);
		cbTipoEmpresa.setWidth("135px");
		cbTipoEmpresa.setHeight("-1px");
		absoluteLayout_5.addComponent(cbTipoEmpresa, "top:60.0px;left:25.0px;");

		// cbTipoRegime
		cbTipoRegime = new ComboBox();
		cbTipoRegime.setCaption("Tipo de regime");
		cbTipoRegime.setImmediate(false);
		cbTipoRegime.setWidth("160px");
		cbTipoRegime.setHeight("-1px");
		absoluteLayout_5.addComponent(cbTipoRegime, "top:60.0px;left:180.0px;");

		// tfSuframa
		tfSuframa = new TextField();
		tfSuframa.setCaption("Suframa");
		tfSuframa.setImmediate(false);
		tfSuframa.setWidth("-1px");
		tfSuframa.setHeight("-1px");
		absoluteLayout_5.addComponent(tfSuframa, "top:56.0px;left:360.0px;");

		// tfContato
		tfContato = new TextField();
		tfContato.setCaption("Contato");
		tfContato.setImmediate(false);
		tfContato.setWidth("320px");
		tfContato.setHeight("-1px");
		absoluteLayout_5.addComponent(tfContato, "top:56.0px;left:840.0px;");

		// tfCodigoTerceiros
		tfCodigoTerceiros = new TextField();
		tfCodigoTerceiros.setCaption("Código de terceiros");
		tfCodigoTerceiros.setImmediate(false);
		tfCodigoTerceiros.setWidth("135px");
		tfCodigoTerceiros.setHeight("-1px");
		absoluteLayout_5.addComponent(tfCodigoTerceiros,
				"top:96.0px;left:25.0px;");

		// tfCei
		tfCei = new TextField();
		tfCei.setCaption("CEI");
		tfCei.setImmediate(false);
		tfCei.setWidth("160px");
		tfCei.setHeight("-1px");
		absoluteLayout_5.addComponent(tfCei, "top:96.0px;left:180.0px;");

		// tfAliquotaPis
		tfAliquotaPis = new TextField();
		tfAliquotaPis.setCaption("Alíquota PIS");
		tfAliquotaPis.setImmediate(false);
		tfAliquotaPis.setWidth("-1px");
		tfAliquotaPis.setHeight("-1px");
		absoluteLayout_5
				.addComponent(tfAliquotaPis, "top:96.0px;left:360.0px;");

		// tfAliquotaCofins
		tfAliquotaCofins = new TextField();
		tfAliquotaCofins.setCaption("Alíquota COFINS");
		tfAliquotaCofins.setImmediate(false);
		tfAliquotaCofins.setWidth("-1px");
		tfAliquotaCofins.setHeight("-1px");
		absoluteLayout_5.addComponent(tfAliquotaCofins,
				"top:96.0px;left:520.0px;");

		// tfAliquotaSat
		tfAliquotaSat = new TextField();
		tfAliquotaSat.setCaption("Alíquota SAT");
		tfAliquotaSat.setImmediate(false);
		tfAliquotaSat.setWidth("-1px");
		tfAliquotaSat.setHeight("-1px");
		absoluteLayout_5
				.addComponent(tfAliquotaSat, "top:96.0px;left:680.0px;");

		// tfMunicipio
		tfMunicipio = new TextField();
		tfMunicipio.setCaption("Código IBGE Município");
		tfMunicipio.setImmediate(false);
		tfMunicipio.setWidth("-1px");
		tfMunicipio.setHeight("-1px");
		absoluteLayout_5.addComponent(tfMunicipio, "top:96.0px;left:839.0px;");

		// tfUf
		tfUf = new TextField();
		tfUf.setCaption("Código IBGE UF");
		tfUf.setImmediate(false);
		tfUf.setWidth("160px");
		tfUf.setHeight("-1px");
		absoluteLayout_5.addComponent(tfUf, "top:96.0px;left:1000.0px;");

		// tfCodigoGps
		tfCodigoGps = new TextField();
		tfCodigoGps.setCaption("Código GPS");
		tfCodigoGps.setImmediate(false);
		tfCodigoGps.setWidth("135px");
		tfCodigoGps.setHeight("-1px");
		absoluteLayout_5.addComponent(tfCodigoGps, "top:146.0px;left:25.0px;");

		// tfCnaePrincipal
		tfCnaePrincipal = new TextField();
		tfCnaePrincipal.setCaption("CNAE principal");
		tfCnaePrincipal.setImmediate(false);
		tfCnaePrincipal.setWidth("480px");
		tfCnaePrincipal.setHeight("-1px");
		absoluteLayout_5.addComponent(tfCnaePrincipal,
				"top:146.0px;left:180.0px;");

		// tfEmail
		tfEmail = new TextField();
		tfEmail.setCaption("Email");
		tfEmail.setImmediate(false);
		tfEmail.setWidth("480px");
		tfEmail.setHeight("-1px");
		absoluteLayout_5.addComponent(tfEmail, "top:146.0px;left:680.0px;");

		return absoluteLayout_5;
	}

	@AutoGenerated
	private GridLayout montaLayout() {
		// common part: create layout
		GridLayout layout = new GridLayout(7, 7);
		layout.setSpacing(true);

		// pdfDataInicioAtividades
		pdfDataInicioAtividades = ComponentUtil
				.buildPopupDateField("Início das atividades");
		layout.addComponent(pdfDataInicioAtividades, 0, 0);

		// mtfCnpj
		mtfCnpj = ComponentUtil.buildCnpjField("CNPJ");
		layout.addComponent(mtfCnpj, 1, 0);

		tfInscricaoEstadual = ComponentUtil
				.buildTextField("Inscrição estadual");
		layout.addComponent(tfInscricaoEstadual, 2, 0);

		// tfInscricaoEstadualSt
		tfInscricaoEstadualSt = ComponentUtil
				.buildTextField("Inscrição estadual ST");
		layout.addComponent(tfInscricaoEstadualSt, 3, 0);

		// tfInscricaoMunicipal
		tfInscricaoMunicipal = ComponentUtil
				.buildTextField("Inscrição municipal");
		layout.addComponent(tfInscricaoMunicipal, 4, 0);

		// tfInscricaoJuntaComercial
		tfInscricaoJuntaComercial = ComponentUtil
				.buildTextField("Inscrição na junta comercial");
		layout.addComponent(tfInscricaoJuntaComercial, 5, 0);

		// pdfDataInscricaoJuntaComercial
		pdfDataInscricaoJuntaComercial = ComponentUtil
				.buildPopupDateField("Data de inscrição na junta comercial");
		layout.addComponent(pdfDataInscricaoJuntaComercial, 0, 1);

		// cbTipoEmpresa
		cbTipoEmpresa = ComponentUtil.buildComboBox("Tipo de empresa");
		layout.addComponent(cbTipoEmpresa, 1, 1);

		// cbTipoRegime
		cbTipoRegime = ComponentUtil.buildComboBox("Tipo de regime");
		layout.addComponent(cbTipoRegime, 2, 1);

		// tfSuframa
		tfSuframa = ComponentUtil.buildTextField("SUFRAMA");
		layout.addComponent(tfSuframa, 3, 1);

		tfContato = ComponentUtil.buildTextField("Contato");
		layout.addComponent(tfContato, 4, 1, 5, 1);

		// tfCodigoTerceiros
		tfCodigoTerceiros = ComponentUtil.buildTextField("Código de terceiros");
		layout.addComponent(tfCodigoTerceiros, 0, 2);

		tfCei = ComponentUtil.buildTextField("CEI");
		layout.addComponent(tfCei, 1, 2);

		// tfAliquotaPis
		tfAliquotaPis = ComponentUtil.buildTextField("Alíquota PIS");
		layout.addComponent(tfAliquotaPis, 2, 2);

		// tfAliquotaCofins
		tfAliquotaCofins = ComponentUtil.buildTextField("Alíquota COFINS");
		layout.addComponent(tfAliquotaCofins, 3, 2);

		// tfAliquotaSat
		tfAliquotaSat = ComponentUtil.buildTextField("Alíquota SAT");
		layout.addComponent(tfAliquotaSat, 4, 2);

		// tfCodigoGps
		tfCodigoGps = ComponentUtil.buildTextField("Código GPS");
		layout.addComponent(tfCodigoGps, 0, 3);

		// txtCnaePrincipal
		// txtCnaePrincipal = ComponentUtil.buildTextField("CNAE Principal");
		// layout.addComponent(txtCnaePrincipal,1,3);

		cbCnaePrincipal = ComponentUtil.buildComboBox("CNAE principal");
		// cmbCnaePrincipal.setContainerDataSource(controller
		// .carregarEmpresaCnae());
		layout.addComponent(cbCnaePrincipal, 1, 3);

		tfMunicipio = ComponentUtil.buildTextField("Código IBGE Municipio");
		layout.addComponent(tfMunicipio, 2, 3);

		// tfUf
		tfUf = ComponentUtil.buildTextField("Código IBGE UF");
		layout.addComponent(tfUf, 3, 3);

		// cbCrt
		cbCrt = ComponentUtil.buildComboBox("CRT");
		layout.addComponent(cbCrt, 0, 4);

		return layout;
	}

	public SubFormComponent<PessoaEnderecoEntity, Integer> getSfPessoaEndereco() {
		return sfPessoaEndereco;
	}

	public void setSfPessoaEndereco(
			SubFormComponent<PessoaEnderecoEntity, Integer> sfPessoaEndereco) {
		this.sfPessoaEndereco = sfPessoaEndereco;
	}

	public void mensagemErro(String message) {
		new Notification(DcConstants.ERROR_TITLE, message, Type.ERROR_MESSAGE,
				true).show(Page.getCurrent());
	}

	/**
	 * PESSOAENDERECO
	 */

	@AutoGenerated
	private Panel bplPessoaEnderecoSubForm() {
		// common part: create layout
		Panel plPessoaEnderecoSubForm = new Panel();
		plPessoaEnderecoSubForm.setImmediate(false);
		plPessoaEnderecoSubForm.setSizeFull();

		plPessoaEnderecoSubForm.setContent(buildPessoaEnderecoSubForm());

		return plPessoaEnderecoSubForm;
	}

	private SubFormComponent<PessoaEnderecoEntity, Integer> buildPessoaEnderecoSubForm() {
		sfPessoaEndereco = new SubFormComponent<PessoaEnderecoEntity, Integer>(
				PessoaEnderecoEntity.class, new String[] { "logradouro",
						"numero", "complemento", "bairro", "cidade", "cep",
						"municipioIbge", "fone" }, new String[] { "Logradouro",
						"Número", "Complemento", "Bairro", "Cidade", "Cep",
						"Município Ibge", "Telefone" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						if ("logradouro".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Logradouro");

							return textField;
						} else if ("numero".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Número");

							return textField;
						} else if ("complemento".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Complemento");

							return textField;
						} else if ("bairro".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Bairro");

							return textField;
						} else if ("cidade".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Cidade");

							return textField;
						} else if ("cep".equals(propertyId)) {
							MaskedTextField textField = ComponentUtil
									.buildMaskedTextField("CEP", "##.###-###");

							return textField;
						} else if ("municipioIbge".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Código Municipio");

							return textField;
						} else if ("fone".equals(propertyId)) {
							MaskedTextField textField = ComponentUtil
									.buildMaskedTextField("Telefone",
											"(##)####-####");

							return textField;
						}

						return null;
					}

				};
			}

			protected PessoaEnderecoEntity getNovo() {
				PessoaEnderecoEntity endereco = controller
						.aderirPessoaEndereco();

				return endereco;
			}

			protected void getRemoverSelecionados(
					List<PessoaEnderecoEntity> values) {
				controller.removerPessoaEndereco(values);
			}

			@Override
			public boolean validateItems(List<PessoaEnderecoEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		return sfPessoaEndereco;
	}

	/**
	 * EMPRESASEGUIMENTO
	 */

	@AutoGenerated
	private Panel bplEmpresaSeguimentoSubForm() {
		// common part: create layout
		Panel plEmpresaSeguimentoSubForm = new Panel();
		plEmpresaSeguimentoSubForm.setImmediate(false);
		plEmpresaSeguimentoSubForm.setSizeFull();

		plEmpresaSeguimentoSubForm.setContent(buildEmpresaSeguimentoSubForm());

		return plEmpresaSeguimentoSubForm;
	}

	private SubFormComponent<EmpresaSeguimento, Integer> buildEmpresaSeguimentoSubForm() {
		// common part: create layout
		sfEmpresaSeguimento = new SubFormComponent<EmpresaSeguimento, Integer>(
				EmpresaSeguimento.class, new String[] { "seguimento",
						"empresa" }, new String[] { "Seguimento",
						"Empresa" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(final Container container,
							final Object itemId, final Object propertyId,
							final Component uiContext) {
					 if ("seguimento".equals(propertyId)) {
						ComboBox comboBox = ComponentUtil.buildComboBox(null);
						BeanItemContainer<SeguimentoEntity> sCntainer = new BeanItemContainer<>(SeguimentoEntity.class,
								controller.buscarSeguimentos());
						sCntainer.addNestedContainerProperty("nome");
						comboBox.setContainerDataSource(sCntainer);
						comboBox.setItemCaptionPropertyId("nome");
						return comboBox;
						
					}
					 
					 if ("empresa".equals(propertyId)) {
							ComboBox comboBox = ComponentUtil.buildComboBox(null);
							BeanItemContainer<EmpresaEntity> empresa = new BeanItemContainer<>(EmpresaEntity.class,
									controller.buscarEmpresas());
							empresa.addNestedContainerProperty("nomeFantasia");
							comboBox.setContainerDataSource(empresa);
							comboBox.setItemCaptionPropertyId("nomeFantasia");
							return comboBox;
							
						}

					else {
						return ComponentUtil.buildTextField(null);
					}
					 
				}

			};
		}

			@Override
			public boolean validateItems(List<EmpresaSeguimento> items) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			protected EmpresaSeguimento getNovo() {
				EmpresaSeguimento empresaSeguimento = controller
						.novoSeguimento();

				return empresaSeguimento;
			}

			@Override
			protected void getRemoverSelecionados(List<EmpresaSeguimento> values) {
				// TODO Auto-generated method stub
				controller.removerSeguimento(values);
			}

			@Override
			protected void onSelect(EmpresaSeguimento item) {
				// TODO Auto-generated method stub

			}

		};

		return sfEmpresaSeguimento;
	}

	/**
	 * CARREGAR SUBFORM
	 */

	public void carregarSfPessoaEndereco(
			List<PessoaEnderecoEntity> pessoaEnderecoList) {
		try {
			this.sfPessoaEndereco.fillWith(pessoaEnderecoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarSfEmpresaSeguimento(
			List<EmpresaSeguimento> nfeDetalheList) {
		try {
			sfEmpresaSeguimento.fillWith(nfeDetalheList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}