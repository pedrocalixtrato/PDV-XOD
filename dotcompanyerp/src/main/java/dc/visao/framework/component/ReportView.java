package dc.visao.framework.component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class ReportView extends CustomComponent implements View {

	private static final long serialVersionUID = -9048088946584713661L;

	private ReportController controller;

	private BrowserFrame reportFrame;

	@SuppressWarnings("serial")
	public ReportView(final ReportController controller) {
		this.controller = controller;
		this.setSizeFull();
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		

		Button carregar = new Button("Carregar");
		carregar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				controller.loadReport();
			}
		});
		layout.addComponent(carregar);
		
		reportFrame = new BrowserFrame("Relatório");
		layout.addComponent(reportFrame);
		
		setCompositionRoot(layout);
	}

	@SuppressWarnings("serial")
	public class ArrayStreamSource implements StreamResource.StreamSource {
		private byte[] object = null;

		public ArrayStreamSource(byte[] object) {
			this.object = object;
		}

		@Override
		public InputStream getStream() {
			return new ByteArrayInputStream(object);
		}

	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	public void showReport(byte[] pdfStream) {
		reportFrame.setSource(new StreamResource(new ArrayStreamSource(pdfStream), "relatorio.pdf"));
	}

}
