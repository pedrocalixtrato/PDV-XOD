package dc.visao.framework.geral;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.context.ApplicationContext;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;

import com.vaadin.server.DownloadStream;
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.framework.AbstractModel;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.RelatorioParameterView;
import dc.entidade.relatorio.TipoRelatorio;
import dc.servicos.util.Validator;

public class RelatorioMenuBuilder {

	private List<Relatorio> relatorios;
	private CRUDListController crudListController;
	private ApplicationContext applicationContext;

	public MenuBar buildRelatorioMenu(List<Relatorio> relatorios, CRUDListController crudListController, ApplicationContext applicationContext) {
		this.relatorios = relatorios;
		this.crudListController = crudListController;
		this.applicationContext = applicationContext;

		RelatorioTree root = new RelatorioTree();
		Comparator<Relatorio> relatoriComparator = new Comparator<Relatorio>() {
			@Override
			public int compare(Relatorio relatorio1, Relatorio relatorio2) {
				if (relatorio1.getRelatorioParent() == null && relatorio2.getRelatorioParent() != null) {

					return -1;
				} else if (relatorio1.getRelatorioParent() != null && relatorio2.getRelatorioParent() == null) {

					return 1;
				} else {

					return relatorio1.getId().compareTo(relatorio2.getId());
				}

			}
		};

		Collections.sort(relatorios, relatoriComparator);
		for (Relatorio relatorio : relatorios) {
			RelatorioTree child = new RelatorioTree(relatorio);
			if (relatorio.getRelatorioParent() == null) {
				root.addChild(new RelatorioTree(relatorio));
			} else {
				RelatorioTree parent = root.searchNodeByRelatorio(relatorio.getRelatorioParent());
				parent.addChild(child);
			}
		}

		// Button relatorioButton = new Button(relatorio.getNome());

		// addButtonListenerReport(relatorioButton, relatorio);

		// view.getPopupButtonReportContent().addComponent(relatorioButton);

		MenuBar menuRelatorio = new MenuBar();
		MenuItem relatorioMenuItem = menuRelatorio.addItem("Relatorios", null, null);
		menuRelatorio.addStyleName("relatorio-bar");
		menuRelatorio.setHeight("-1px");
		menuRelatorio.setWidth("-1px");

		addMenuItem(relatorioMenuItem, root);

		return menuRelatorio;
	}

	private void addMenuItem(MenuItem parent, RelatorioTree node) {

		if (node.isRoot()) {

			for (RelatorioTree relatorioTree : node.getFilhos()) {
				addMenuItem(parent, relatorioTree);
			}
		} else {
			Relatorio relatorio = node.getRelatorio();
			RelatorioParameterView relatorioParameterView = null;
			if (Validator.validateString(relatorio.getTelaParametros())) {
				try {
					Class clazz = Class.forName(relatorio.getTelaParametros());
					relatorioParameterView = (RelatorioParameterView) applicationContext.getBean(clazz);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			MenuItem menuItem = parent.addItem(node.getRelatorio().getNome(), new MenuRelatorioCommand(relatorio, crudListController,
					relatorioParameterView));
			if (!node.isLeaf()) {
				for (RelatorioTree relatorioTree : node.getFilhos()) {

					addMenuItem(menuItem, relatorioTree);

				}
			}

		}

	}

	private class MenuRelatorioCommand implements MenuBar.Command {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Relatorio relatorio;
		private final CRUDListController crudListController;
		private final RelatorioParameterView relatorioParameterView;

		public MenuRelatorioCommand(Relatorio relatorio, CRUDListController crudListController, RelatorioParameterView parameterView) {
			this.relatorio = relatorio;
			this.crudListController = crudListController;
			this.relatorioParameterView = parameterView;
		}

		@Override
		public void menuSelected(MenuItem selectedItem) {
			if (Validator.validateString(relatorio.getJasperPath())) {
				try {

					if (relatorioParameterView != null) {

						VerticalLayout relatorioPopUp = new VerticalLayout();
						relatorioPopUp.setMargin(true);
						relatorioPopUp.setSpacing(true);
						relatorioPopUp.addComponent(relatorioParameterView);

						Button imprimir = new Button();
						imprimir.setCaption("Imprimir");
						relatorioPopUp.addComponent(imprimir);

						crudListController.openOnNewWindow(3, CRUDListController.WINDOW_LIST, relatorioPopUp);
						imprimir.addClickListener(new ClickListener() {

							/**
						 * 
						 */
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								try {
									printListener(relatorioParameterView.getParametersMap(), relatorioParameterView.getJRDataSource());
								} catch (JRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						});

					} else {

						JRDataSource dataSource;
						if (TipoRelatorio.getEnum(relatorio.getTipo()).equals(TipoRelatorio.LISTAGEM)) {
							LazyQueryContainer containerDataSource = (LazyQueryContainer) crudListController.getTable().getContainerDataSource();

							List<?> itemIds = containerDataSource.getItemIds(0, Integer.MAX_VALUE);

							List<AbstractModel> items = new ArrayList<>();
							for (Object id : itemIds) {
								AbstractModel bean = (AbstractModel) crudListController.getMainDao().find((Serializable) id);
								items.add(bean);
							}

							dataSource = new JRBeanCollectionDataSource(items);
						} else {
							List<AbstractModel> modelBeanList = new ArrayList<>();
							AbstractModel modelBean = this.crudListController.getFormController().getModelBean();
							modelBeanList.add(modelBean);
							dataSource = new JRBeanCollectionDataSource(modelBeanList);
						}

						printListener(new HashMap<String, Object>(), dataSource);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private void printListener(Map<String, Object> params, JRDataSource dataSource) throws JRException {

			if (dataSource == null) {
				dataSource = new JREmptyDataSource();
			}

			final byte[] bytes;

			if (isJasper()) {
				bytes = JasperRunManager.runReportToPdf(relatorio.getJasperPath(), params, dataSource);
			} else if (isJrXml()) {
				JasperReport report = JasperCompileManager.compileReport(relatorio.getJasperPath());
				bytes = JasperRunManager.runReportToPdf(report, params, dataSource);
			} else {
				bytes = new byte[0];
			}

			final StreamSource s = new StreamSource() {

				public java.io.InputStream getStream() {
					ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
					return bais;
				}
			};

			String filename = relatorio.getNome().replace(" ", "_").concat(".pdf").toLowerCase();

			filename = filename.replaceAll("[éèêë]", "e");
			filename = filename.replaceAll("[áàäâã]", "a");
			filename = filename.replaceAll("[úùüû]", "u");
			filename = filename.replaceAll("[óòôõö]", "o");
			filename = filename.replaceAll("[íìïî]", "i");
			filename = filename.replaceAll("ç", "c");

			StreamResource resource = new StreamResource(s, filename);

			DownloadStream stream = resource.getStream();
			stream.setContentType("application/pdf");
			stream.setFileName(filename);
			stream.setParameter("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			stream.setParameter("Content-Length", String.valueOf(bytes.length));
			resource.setCacheTime(5000);
			resource.setMIMEType("application/pdf");
			Page.getCurrent().open(resource, "_blank", false);
		}

		private boolean isJrXml() {
			return ".jrxml".equalsIgnoreCase(getExtensao(relatorio.getJasperPath()));
		}

		private boolean isJasper() {
			return ".jasper".equalsIgnoreCase(getExtensao(relatorio.getJasperPath()));
		}

		private String getExtensao(String caminho) {
			if (caminho != null && !caminho.isEmpty()) {
				int indiceExtensao = caminho.lastIndexOf(".");
				if (indiceExtensao > -1) {
					return caminho.substring(indiceExtensao, caminho.length());
				}
			}
			return "";
		}

	}
}
