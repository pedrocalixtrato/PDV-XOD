package dc.visao.financeiro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.financeiro.TalonarioCheque;
import dc.entidade.financeiro.type.StatusChequeType;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class ChequeFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout glGeral;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private PopupDateField dtStatus;

	@AutoGenerated
	private ComboBox cmbStatus;

	@AutoGenerated
	private TextField txtNumero;

	@AutoGenerated
	private ManyToOneComboField<TalonarioCheque> cmbTalonarioCheque;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ChequeFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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

		glGeral = bglGeral();
		mainLayout.addComponent(glGeral);
		
		for (StatusChequeType en : StatusChequeType.values()) {
			cmbStatus.addItem(en);
		}

	}

	/**
	 * GERAL
	 */

	@AutoGenerated
	private GridLayout bglGeral() {
		// common part: create layout
		glGeral = new GridLayout(4, 3);
		glGeral.setImmediate(false);
		glGeral.setWidth("100.0%");
		glGeral.setHeight("-1px");
		glGeral.setMargin(false);
		glGeral.setSpacing(true);

		// cmbTalonarioCheque
		cmbTalonarioCheque = new ManyToOneComboField<>(TalonarioCheque.class);
		cmbTalonarioCheque.setCaption("Talonário Cheque");
		glGeral.addComponent(cmbTalonarioCheque, 0,0);

		// txtNumero
		txtNumero = ComponentUtil.buildTextField("Número");
		txtNumero.setImmediate(false);
		glGeral.addComponent(txtNumero, 0,1);

		// cmbStatus
		cmbStatus = ComponentUtil.buildComboBox("Status");
		cmbStatus.setImmediate(false);
		glGeral.addComponent(cmbStatus, 1,1);

		// dtStatus
		dtStatus = ComponentUtil.buildPopupDateField("Data Status");
		dtStatus.setImmediate(false);
		glGeral.addComponent(dtStatus, 2,1);

		return glGeral;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public ComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(ComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public PopupDateField getDtStatus() {
		return dtStatus;
	}

	public void setDtStatus(PopupDateField dtStatus) {
		this.dtStatus = dtStatus;
	}

	public TextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public ManyToOneComboField<TalonarioCheque> getCmbTalonarioCheque() {
		return cmbTalonarioCheque;
	}

	public void setCmbTalonarioCheque(
			ManyToOneComboField<TalonarioCheque> cmbTalonarioCheque) {
		this.cmbTalonarioCheque = cmbTalonarioCheque;
	}

}
