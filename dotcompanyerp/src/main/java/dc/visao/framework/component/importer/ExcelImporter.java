package dc.visao.framework.component.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

import dc.servicos.dao.framework.geral.IListDAO;
import dc.visao.framework.geral.CRUDListController;

public class ExcelImporter extends Importer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private CRUDListController controller;

	public void processarArquivo(File value) {
		try {
			IListDAO genericDAO = controller.getGenericDAO();

			FileInputStream fileInputStream = new FileInputStream(value);
			HSSFWorkbook xls = new HSSFWorkbook(fileInputStream);

			HSSFSheet sheet = xls.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			// pula o header. Primeira linha
			if (iterator.hasNext()) {
				iterator.next();
			}

			while (iterator.hasNext()) {
				Row row = iterator.next();
				// criar bean
				Object bean = controller.getEntityClass().newInstance();

				int coluna = -1;
				for (Object column : controller.getTable().getVisibleColumns()) {
					// Ignora a primeira coluna pois eh check box
					if (coluna >= 0) {
						try {
							Cell cell = row.getCell(coluna);
							PropertyUtils.getPropertyDescriptor(bean, column.toString()).getPropertyType();
							PropertyUtils.setSimpleProperty(bean, column.toString(),
									getCellValue(cell, PropertyUtils.getPropertyDescriptor(bean, column.toString()).getPropertyType()));

						} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
							new Notification("Arquivo", "Ocorreu um erro na importação!", Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page
									.getCurrent());
							e.printStackTrace();

						}

					}
					coluna++;
				}

				// salvar

				genericDAO.saveOrUpdate((Serializable) bean);

			}
			fileInputStream.close();
			value.deleteOnExit();

			new Notification("Arquivo", "Importação realizada com sucesso!", Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page.getCurrent());

			controller.doSearch("");

		} catch (IOException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Object getCellValue(Cell cell, Class<?> clazz) {
		Object returnValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				Double numericCellValue = cell.getNumericCellValue();
				if (clazz.isInstance(numericCellValue)) {
					returnValue = numericCellValue;
				} else {

					if (numericCellValue instanceof Double) {
						returnValue = String.valueOf(numericCellValue.intValue());
					} else {
						returnValue = numericCellValue.toString();
					}
				}
				break;

			case Cell.CELL_TYPE_STRING:
				returnValue = cell.getStringCellValue();
				break;

			case Cell.CELL_TYPE_BOOLEAN:
				returnValue = cell.getBooleanCellValue();
				break;

			default:
				break;
			}
		}
		return returnValue;
	}

	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Override protected void handleFile(File file, String fileName, String
	 * mimeType, long length) { processaArquivo(file);
	 * 
	 * 
	 * 
	 * }
	 */

	public void setController(@SuppressWarnings("rawtypes") CRUDListController controller) {
		this.controller = controller;

	}

}
