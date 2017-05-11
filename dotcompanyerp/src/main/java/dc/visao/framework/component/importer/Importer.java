package dc.visao.framework.component.importer;

import java.io.File;

import org.vaadin.easyuploads.UploadField;

import com.vaadin.ui.Upload;

public abstract class Importer extends UploadField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;

	public Importer() {
		this.setFieldType(FieldType.FILE);
		this.setFileDeletesAllowed(false);
		this.addListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				UploadField upload = (UploadField) event.getProperty();
				file = (File) upload.getValue();
				processarArquivo(file);
				file = null;

			}

		});

		final Upload upload = (Upload) getRootLayout().getComponent(0);
		// Make uploading start immediately when file is selected
		upload.setImmediate(true);
		upload.setButtonCaption("Importar");
	}

	protected abstract void processarArquivo(File file);

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
