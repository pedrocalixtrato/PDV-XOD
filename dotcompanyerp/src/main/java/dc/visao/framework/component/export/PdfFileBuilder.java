package dc.visao.framework.component.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfBoolean;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;

public class PdfFileBuilder extends FileBuilder {
	private static final long serialVersionUID = 1L;
	private Document document;
	    private PdfPTable table;
	    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
	            Font.UNDERLINE);
	    private static Font subCell = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL);
	    private boolean withBorder;

	    public PdfFileBuilder(Container container) {
	        super(container);
	    }
	    
	    public PdfFileBuilder() {
			super();
		}

	    @Override
	    protected void buildHeader() {
	        if (getHeader() != null) {
	            Paragraph title = new Paragraph(getHeader(), catFont);
	            title.add(new Paragraph(" "));
	            title.setAlignment(Element.ALIGN_CENTER);
	            try {
	                document.add(title);
	            } catch (DocumentException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    @Override
	    protected void buildColumnHeaderCell(String header) {
	        PdfPCell cell = new PdfPCell(new Phrase(header, subFont));
	        if (!withBorder) {
	            cell.setBorder(Rectangle.NO_BORDER);
	        }
	        table.addCell(cell);
	    }

	    @Override
	    protected void buildCell(Object value) {
	    	PdfPCell cell;
	    	if(value == null){
	    		cell = new PdfPCell(new Phrase("", subCell));
	    		
	    		
	    	}else if(value instanceof Calendar){
	    		Calendar calendar = (Calendar) value;
	    		cell = new PdfPCell(new Phrase(formatDate(calendar.getTime()), subCell));
	    	}else if(value instanceof Date){
	    		cell = new PdfPCell(new Phrase(formatDate((Date) value), subCell));
	    	} else if(value instanceof Link){
	    		Link link = (Link) value;
	    		Chunk chunk = new Chunk(link.getCaption(), subCell);
//	    		try {
//					PdfAction pdfAction = new PdfAction(new URL(((ExternalResource)link.getResource()).getURL().toString()));
//					chunk.setAction(pdfAction);
//				} catch (MalformedURLException e) {
//					System.out.println();
//				}
	    		Paragraph paragraph = new Paragraph(chunk);
	    		paragraph.add(".");
				cell = new PdfPCell(paragraph); 
	    	} else if(value instanceof Button){
	    		Button button = (Button) value;
	    		cell = new PdfPCell(new Phrase(button.getCaption(), subCell));
	    	}
	    	else {
	    		cell = new PdfPCell(new Phrase(value.toString(), subCell));
	    	}
	        
	        if (!withBorder) {
	            cell.setBorder(Rectangle.NO_BORDER);
	        }
	        table.addCell(cell);
	    }

	    @Override
	    protected String getFileExtension() {
	        return ".pdf";
	    }

	    @Override
	    protected void writeToFile() {
	        try {
		        PdfWriter writer = null;
		        try {
		        	writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		        	writer.setPdfVersion(PdfWriter.VERSION_1_7);
		        	writer.addViewerPreference(PdfName.PRINTSCALING, PdfName.NONE);
		        	writer.addViewerPreference(PdfName.PICKTRAYBYPDFSIZE, PdfBoolean.PDFTRUE);
		        } catch (FileNotFoundException e1) {
		        	// TODO Auto-generated catch block
		        	e1.printStackTrace();
		        } catch (DocumentException e1) {
		        	// TODO Auto-generated catch block
		        	e1.printStackTrace();
		        }
				document.open(); //20131111: Tulasi --- Added the statement to open the document created before adding the table
	        	document.add(table);
	            document.close();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean isWithBorder() {
	        return withBorder;
	    }

	    public void setWithBorder(boolean withBorder) {
	        this.withBorder = withBorder;
	    }

	    @Override
	    protected void resetContent() {
	    	int columnCount = getNumberofColumns();
	    	Rectangle a4Landscape = PageSize.A4_LANDSCAPE;
	    	float fWidth = a4Landscape.getWidth();
	    	if(columnCount > 10) {
			    catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			            Font.BOLD);
			    subFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
			            Font.UNDERLINE);
			    subCell = new Font(Font.FontFamily.TIMES_ROMAN, 11,
			            Font.NORMAL);
	    	}
	    	if(columnCount > 12) {
	    		return;
	    	}
	    	
	         
	    	//Rectangle rectangle = new Rectangle(14400f, 216f);
	        Rectangle rectangle = new Rectangle(fWidth, a4Landscape.getHeight());
	    	document = new Document(rectangle, 20, 20, 20, 20);
	        table = new PdfPTable(getNumberofColumns());
	        table.setWidthPercentage(100f);
	        table.getDefaultCell().setColspan(2);
	        table.getDefaultCell().setPadding(2);
	        
	        try {
	            PdfWriter.getInstance(document, new FileOutputStream(file));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	        document.open();
	    }
}
