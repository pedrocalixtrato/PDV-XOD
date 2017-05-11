package dc.visao.framework.component.export;
import com.vaadin.data.Container;
import com.vaadin.ui.Table;

public class PdfExporter extends Exporter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableHolder tableHolder;
	
    public PdfExporter() {
        super();
    }

    public PdfExporter(Table table) {
        super(table);
    }

   public PdfExporter(Container container, Object[] visibleColumns) {
        super(container, visibleColumns);
    }

    public PdfExporter(Container container) {
        super(container);
    }

    @Override
    protected FileBuilder createFileBuilder(Container container) {
        return new PdfFileBuilder(container);
    }

    @Override
    protected String getDownloadFileName() {
    	if(downloadFileName == null){
    		return "exported-pdf.pdf";
        }
    	if(downloadFileName.endsWith(".pdf")){
    		return downloadFileName;
    	}else{
    		return downloadFileName + ".pdf";
    	}
    }

    public void setWithBorder(boolean withBorder) {
        ((PdfFileBuilder) fileBuilder).setWithBorder(withBorder);
    }

	@Override
	public void convertTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFilename() {
		return getDownloadFileName();
	}

}
