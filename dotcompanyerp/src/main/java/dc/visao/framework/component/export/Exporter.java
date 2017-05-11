package dc.visao.framework.component.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import com.vaadin.data.Container;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

import dc.visao.framework.component.export.OnDemandFileDownloader.OnDemandStreamResource;

public abstract class Exporter extends Button implements OnDemandStreamResource, Serializable {

	private static final long serialVersionUID = 1L;

	private FileDownloader fileDownloader;
	protected FileBuilder fileBuilder;
	private Locale locale;
	private String dateFormatString;
	protected String downloadFileName;

	public static String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	private TableHolder tableHolder;

	private File file;

	public List<Object> getPropIds() {
		return tableHolder.getPropIds();
	}

	public TableHolder getTableHolder() {
		return tableHolder;
	}

	public final void setTable(final Table table) {
		tableHolder = new DefaultTableHolder(table);
	}

	public void setTableHolder(final TableHolder tableHolder) {
		this.tableHolder = tableHolder;
	}

	public Exporter() {
		fileDownloader = new FileDownloader(new StreamResource(this, getDownloadFileName()));
		fileDownloader.extend(this);
	}

	public Exporter(CustomTable table) {
		this();
		setTableToBeExported(table);
		file = fileBuilder.getFile();
	}

	public File exportToPDF(CustomTable table) {
		setTableToBeExported(table);
		file = fileBuilder.getFile();
		return file;
	}

	public Exporter(Container container, Object[] visibleColumns) {
		this();
		setCaption("Exporter");
		setContainerToBeExported(container);
		setVisibleColumns(visibleColumns);
	}

	public Exporter(Container container) {
		this(container, null);
	}

	public Exporter(TableHolder tableHolder) {
		this.tableHolder = tableHolder;
	}

	public void setTableToBeExported(CustomTable table) {
		setContainerToBeExported(table.getContainerDataSource());
		setVisibleColumns(table.getVisibleColumns());
		setHeader(table.getCaption());
		int coluna = 1;
		for (Object column : table.getVisibleColumns()) {

			if (coluna > 1) {
				String header = table.getColumnHeader(column);
				if (header != null) {
					setColumnHeader(column, header);
				}

			}

			coluna++;
		}
	}

	public void setContainerToBeExported(Container container) {
		fileBuilder = createFileBuilder(container);
		if (locale != null) {
			fileBuilder.setLocale(locale);
		}
		if (dateFormatString != null) {
			fileBuilder.setDateFormat(dateFormatString);
		}
	}

	public void setVisibleColumns(Object[] visibleColumns) {
		fileBuilder.setVisibleColumns(visibleColumns);
	}

	public void setColumnHeader(Object propertyId, String header) {
		fileBuilder.setColumnHeader(propertyId, header);
	}

	public void setHeader(String header) {
		fileBuilder.setHeader(header);
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormatString = dateFormat;
	}

	protected abstract FileBuilder createFileBuilder(Container container);

	protected abstract String getDownloadFileName();

	public void setDownloadFileName(String fileName) {
		downloadFileName = fileName;
		((StreamResource) fileDownloader.getFileDownloadResource()).setFilename(getDownloadFileName());
	}

	@Override
	public InputStream getStream() {
		try {
			FileInputStream fis = new FileInputStream(fileBuilder.getFile());
			return fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public abstract void convertTable();

	protected String mimeType;

	/**
	 * Create and export the Table contents as some sort of file type. In the
	 * case of conversion to Excel it would be an ".xls" file containing the
	 * contents as a report. Only the export() method needs to be called. If the
	 * user wishes to manipulate the converted object to export, then
	 * convertTable() should be called separately, and, after manipulation,
	 * sendConverted().
	 */

	public void export() {
		convertTable();
	}

	/**
	 * Utility method to send the converted object to the user, if it has been
	 * written to a temporary File.
	 * 
	 * Code obtained from:
	 * http://vaadin.com/forum/-/message_boards/view_message/159583
	 * 
	 * @return true, if successful
	 */
	public boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName,
			final String mimeType) {
		setMimeType(mimeType);
		return sendConvertedFileToUser(app, fileToExport, exportFileName);

	}

	protected boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName) {
		TemporaryFileDownloadResource resource;
		try {
			resource = new TemporaryFileDownloadResource(app, exportFileName, mimeType, fileToExport);
			if (null == app) {
				UI.getCurrent().getPage().open(resource, null, false);
			} else {
				app.getPage().open(resource, null, false);
			}
		} catch (final FileNotFoundException e) {
			// LOGGER.warning("Sending file to user failed with
			// FileNotFoundException " + e);
			return false;
		}
		return true;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

}