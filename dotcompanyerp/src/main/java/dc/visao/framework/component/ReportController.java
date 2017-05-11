package dc.visao.framework.component;

import java.util.HashMap;
import java.util.UUID;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.sun.istack.logging.Logger;
import com.vaadin.navigator.View;

import dc.entidade.framework.PapelMenu;
import dc.visao.framework.geral.Controller;
import dc.visao.framework.geral.ControllerAcesso;
import dc.visao.framework.geral.ControllerTask;

public class ReportController extends ControllerTask implements Controller, ControllerAcesso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger(ReportController.class);

	private ReportView view;

	public ReportController() {
		view = new ReportView(this);
	}

	@Override
	public View getView() {
		return view;
	}

	@Override
	public String getControllerTitle() {
		return "Relatório";
	}

	@Override
	public Controller getController() {
		return this;
	}

	@Override
	public String getViewIdentifier() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void setAcessoLiberado() {
	}

	@Override
	public void setPapelMenu(PapelMenu pf) {
	}

	public void loadReport() {
		try {
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("reports/teste.jasper");
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>());
			byte[] pdfStream = JasperExportManager.exportReportToPdf(print);

			view.showReport(pdfStream);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void setChildModuleID(String id) {
		// nothinf for now
	}

}