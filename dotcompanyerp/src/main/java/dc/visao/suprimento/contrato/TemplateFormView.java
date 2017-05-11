package dc.visao.suprimento.contrato;

import org.vaadin.easyuploads.FileBuffer;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;

public class TemplateFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private TextArea txaDescricao;
	@AutoGenerated
	private TextField txtNome;

	private UploadField upArquivo;

	private String nomeArquivo;

	private String path;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public TemplateFormView() {
		buildMainLayout();
		configuraComponentes();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	public UploadField getUpArquivo() {
		return upArquivo;
	}

	public void setUpArquivo(UploadField upArquivo) {
		this.upArquivo = upArquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TextArea getTxaDescricao() {
		return txaDescricao;
	}

	public void setTxaDescricao(TextArea txaDescricao) {
		this.txaDescricao = txaDescricao;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	@SuppressWarnings("serial")
	private void configuraComponentes() {

		upArquivo.setFieldType(FieldType.FILE);
		upArquivo.addListener(new ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				UploadField upload = (UploadField) event.getProperty();

				Upload up = (Upload) upload.getRootLayout().getComponent(0);
				nomeArquivo = ((FileBuffer) up.getReceiver()).getLastFileName();

				path = ((FileBuffer) up.getReceiver()).getFile()
						.getAbsolutePath();

			}
		});

	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("500px");

		// top-level component properties
		setWidth("100.0%");
		setHeight("500px");

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		mainLayout.addComponent(gridLayout_1, "top:0.0px;left:0.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("-1px");
		gridLayout_1.setHeight("-1px");
		gridLayout_1.setMargin(false);
		gridLayout_1.setRows(3);

		// txtNome
		txtNome = new TextField();
		txtNome.setCaption("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("281px");
		txtNome.setHeight("-1px");
		gridLayout_1.addComponent(txtNome, 0, 0);

		// txaDescricao
		txaDescricao = new TextArea();
		txaDescricao.setCaption("Descrição");
		txaDescricao.setImmediate(false);
		txaDescricao.setWidth("280px");
		txaDescricao.setHeight("78px");
		gridLayout_1.addComponent(txaDescricao, 0, 1);

		// upload
		upArquivo = new UploadField();
		upArquivo.setCaption("Modelo Template");
		upArquivo.setImmediate(false);
		upArquivo.setButtonCaption("Selecione o modelo");
		gridLayout_1.addComponent(upArquivo, 0, 2);

		return gridLayout_1;
	}

}