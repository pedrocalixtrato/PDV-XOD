package dc.visao.framework.component.export;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

public abstract class CSVExporter extends Exporter {
    public CSVExporter() {
        super();
    }

    public CSVExporter(Table table) {
        super(table);
    }

   public CSVExporter(Container container, Object[] visibleColumns) {
        super(container, visibleColumns);
    }

    public CSVExporter(Container container) {
        super(container);
    }

    @Override
    protected FileBuilder createFileBuilder(Container container) {
        // TODO Auto-generated method stub
        return new CSVFileBuilder(container);
    }

    @Override
    protected String getDownloadFileName() {
    	if(downloadFileName == null){
    		return "exported-csv.csv";
        }
    	if(downloadFileName.endsWith(".csv")){
    		return downloadFileName;
    	}else{
    		return downloadFileName + ".csv";
    	}
    }

	/*@Override
	public InputStream getStream() {
		try {
			FileInputStream fis = new FileInputStream(fileBuilder.getFile());
			return fis;
			//return new FileInputStream(fileBuilder.getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}*/

}
