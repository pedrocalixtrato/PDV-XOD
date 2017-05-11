package dc.visao.framework.component.export;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import com.ibm.icu.math.BigDecimal;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Table;

public class ExcelExporter extends Exporter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcelExporter() {
		super();
	}

	public ExcelExporter(Table table) {
		super(table);
	}

	public ExcelExporter(Container container, Object[] visibleColumns) {
		super(container, visibleColumns);
	}

	public ExcelExporter(Container container) {
		super(container);
	}

	@Override
	protected FileBuilder createFileBuilder(Container container) {
		return new ExcelFileBuilder(container);
	}

	protected Sheet sheet;
	protected CellStyle rowHeaderCellStyle = null;
	protected Sheet hierarchicalTotalsSheet = null;
	protected String reportTitle;
	protected String sheetName;
	protected String exportFileName;

	protected boolean rowHeaders = false;

	protected boolean useTableFormatPropertyValue = false;

	protected Workbook workbook;

	protected Row titleRow, headerRow, totalsRow;
	protected Row hierarchicalTotalsRow;
	protected CreationHelper createHelper;
	protected DataFormat dataFormat;

	protected CellStyle dateCellStyle, doubleCellStyle, integerCellStyle, totalsDoubleCellStyle, totalsIntegerCellStyle,
			columnHeaderCellStyle, titleCellStyle;
	protected Short dateDataFormat, doubleDataFormat, integerDataFormat;
	protected boolean displayTotals;

	@Override
	protected String getDownloadFileName() {
		if (downloadFileName == null) {
			return "exported-excel.xls";
		}
		if (downloadFileName.endsWith(".xls")) {
			return downloadFileName;
		} else {
			return downloadFileName + ".xls";
		}
	}

	private void init(final String shtName, final String rptTitle, final String xptFileName,
			final boolean hasTotalsRow) {
		if ((null == shtName) || ("".equals(shtName))) {
			this.sheetName = "Table Export";
		} else {
			this.sheetName = shtName;
		}
		if (null == rptTitle) {
			this.reportTitle = "";
		} else {
			this.reportTitle = rptTitle;
		}
		if ((null == xptFileName) || ("".equals(xptFileName))) {
			this.exportFileName = "Table-Export.xls";
		} else {
			this.exportFileName = xptFileName;
		}
		this.displayTotals = hasTotalsRow;

		this.sheet = this.workbook.createSheet(this.sheetName);
		this.createHelper = this.workbook.getCreationHelper();
		this.dataFormat = this.workbook.createDataFormat();
		this.dateDataFormat = defaultDateDataFormat(this.workbook);
		this.doubleDataFormat = defaultDoubleDataFormat(this.workbook);
		this.integerDataFormat = defaultIntegerDataFormat(this.workbook);

		this.doubleCellStyle = defaultDataCellStyle(this.workbook);
		this.doubleCellStyle.setDataFormat(doubleDataFormat);

		this.integerCellStyle = defaultDataCellStyle(this.workbook);
		this.integerCellStyle.setDataFormat(integerDataFormat);

		this.dateCellStyle = defaultDataCellStyle(this.workbook);
		this.dateCellStyle.setDataFormat(this.dateDataFormat);

		this.totalsDoubleCellStyle = defaultTotalsDoubleCellStyle(this.workbook);
		this.totalsIntegerCellStyle = defaultTotalsIntegerCellStyle(this.workbook);
		this.columnHeaderCellStyle = defaultHeaderCellStyle(this.workbook);
		this.titleCellStyle = defaultTitleCellStyle(this.workbook);
	}

	/*
	 * This will exclude columns from the export that are not visible due to
	 * them being collapsed. This should be called before convertTable() is
	 * called.
	 */
	public void excludeCollapsedColumns() {
		final Iterator<Object> iterator = getPropIds().iterator();
		while (iterator.hasNext()) {
			final Object propId = iterator.next();
			if (getTableHolder().isColumnCollapsed(propId)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Creates the workbook containing the exported table data, without
	 * exporting it to the user.
	 */
	@Override
	public void convertTable() {
		final int startRow;
		startRow = addTitleRow();
		int row = startRow;

		// add header row
		row++;

		// add data rows
		// add totals row
		if (displayTotals) {
			addTotalsRow(row, startRow);
		}

		// final sheet format before export
	}

	protected int addTitleRow() {
		if ((null == reportTitle) || ("".equals(reportTitle))) {
			return 0;
		}
		titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(45);
		final Cell titleCell;
		final CellRangeAddress cra;
		if (rowHeaders) {
			titleCell = titleRow.createCell(1);
			cra = new CellRangeAddress(0, 0, 1, getPropIds().size() - 1);
			sheet.addMergedRegion(cra);
		} else {
			titleCell = titleRow.createCell(0);
			cra = new CellRangeAddress(0, 0, 0, getPropIds().size() - 1);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, getPropIds().size() - 1));
		}
		titleCell.setCellValue(reportTitle);
		titleCell.setCellStyle(titleCellStyle);
		// cell borders don't work on merged ranges so, if there are borders
		// we apply them to the merged range here.
		if (titleCellStyle.getBorderLeft() != CellStyle.BORDER_NONE) {
			RegionUtil.setBorderLeft(titleCellStyle.getBorderLeft(), cra, sheet, workbook);
		}
		if (titleCellStyle.getBorderRight() != CellStyle.BORDER_NONE) {
			RegionUtil.setBorderRight(titleCellStyle.getBorderRight(), cra, sheet, workbook);
		}
		if (titleCellStyle.getBorderTop() != CellStyle.BORDER_NONE) {
			RegionUtil.setBorderTop(titleCellStyle.getBorderTop(), cra, sheet, workbook);
		}
		if (titleCellStyle.getBorderBottom() != CellStyle.BORDER_NONE) {
			RegionUtil.setBorderBottom(titleCellStyle.getBorderBottom(), cra, sheet, workbook);
		}
		return 1;
	}

	protected CellStyle getColumnHeaderStyle(final int row, final int col) {
		if ((rowHeaders) && (col == 0)) {
			return titleCellStyle;
		}
		return columnHeaderCellStyle;
	}

	/**
	 * For Hierarchical Containers, this method recursively adds root items and
	 * child items. The child items are appropriately grouped using
	 * grouping/outlining sheet functionality. Override this method to make any
	 * changes. To change the CellStyle used for all Table data use
	 * setDataStyle(). For different data cells to have different CellStyles,
	 * override getDataStyle().
	 *
	 * @param row
	 *            the row
	 * @return the int
	 */
	protected int addHierarchicalDataRows(final Sheet sheetToAddTo, final int row) {
		final Collection<?> roots;
		int localRow = row;
		roots = ((Container.Hierarchical) getTableHolder().getContainerDataSource()).rootItemIds();
		/*
		 * For Hierarchical Containers, the outlining/grouping in the sheet is
		 * with the summary row at the top and the grouped/outlined
		 * subcategories below.
		 */
		sheet.setRowSumsBelow(false);
		int count = 0;
		for (final Object rootId : roots) {
			count = addDataRowRecursively(sheetToAddTo, rootId, localRow);
			// for totals purposes, we just want to add rootIds which contain
			// totals
			// so we store just the totals in a separate sheet.
			if (displayTotals) {
				addDataRow(hierarchicalTotalsSheet, rootId, localRow);
			}
			if (count > 1) {
				sheet.groupRow(localRow + 1, (localRow + count) - 1);
				sheet.setRowGroupCollapsed(localRow + 1, true);
			}
			localRow = localRow + count;
		}
		return localRow;
	}

	/**
	 * this method adds row items for non-Hierarchical Containers. Override this
	 * method to make any changes. To change the CellStyle used for all Table
	 * data use setDataStyle(). For different data cells to have different
	 * CellStyles, override getDataStyle().
	 *
	 * @param row
	 *            the row
	 * @return the int
	 */
	protected int addDataRows(final Sheet sheetToAddTo, final int row) {
		final Collection<?> itemIds = getTableHolder().getContainerDataSource().getItemIds();
		int localRow = row;
		int count = 0;
		for (final Object itemId : itemIds) {
			addDataRow(sheetToAddTo, itemId, localRow);
			count = 1;
			if (count > 1) {
				sheet.groupRow(localRow + 1, (localRow + count) - 1);
				sheet.setRowGroupCollapsed(localRow + 1, true);
			}
			localRow = localRow + count;
		}
		return localRow;
	}

	/**
	 * Used by addHierarchicalDataRows() to implement the recursive calls.
	 *
	 * @param rootItemId
	 *            the root item id
	 * @param row
	 *            the row
	 * @return the int
	 */
	private int addDataRowRecursively(final Sheet sheetToAddTo, final Object rootItemId, final int row) {
		int numberAdded = 0;
		int localRow = row;
		addDataRow(sheetToAddTo, rootItemId, row);
		numberAdded++;
		if (((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)) {
			final Collection<?> children = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
					.getChildren(rootItemId);
			for (final Object child : children) {
				localRow++;
				numberAdded = numberAdded + addDataRowRecursively(sheetToAddTo, child, localRow);
			}
		}
		return numberAdded;
	}

	/**
	 * This method is ultimately used by either addDataRows() or
	 * addHierarchicalDataRows() to actually add the data to the Sheet.
	 *
	 * @param rootItemId
	 *            the root item id
	 * @param row
	 *            the row
	 */
	protected void addDataRow(final Sheet sheetToAddTo, final Object rootItemId, final int row) {
		final Row sheetRow = sheetToAddTo.createRow(row);
		Property prop;
		Object propId;
		Object value;
		Cell sheetCell;
		for (int col = 0; col < getPropIds().size(); col++) {
			propId = getPropIds().get(col);
			prop = getProperty(rootItemId, propId);
			if (null == prop) {
				value = null;
			} else {
				value = prop.getValue();
			}
			sheetCell = sheetRow.createCell(col);
			final Short poiAlignment = getTableHolder().getCellAlignment(propId);
			CellUtil.setAlignment(sheetCell, workbook, poiAlignment);
			if (null != value) {
				if (!isNumeric(prop.getType())) {
					if (java.util.Date.class.isAssignableFrom(prop.getType())) {
						sheetCell.setCellValue((Date) value);
					} else {
						sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
					}
				} else {
					try {
						// parse all numbers as double, the format will
						// determine how they appear
						final Double d = Double.parseDouble(value.toString());
						sheetCell.setCellValue(d);
					} catch (final NumberFormatException nfe) {
						// LOGGER.warning("NumberFormatException parsing a
						// numeric value: " + nfe);
						sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
					}
				}
			}
		}
	}

	private Property getProperty(final Object rootItemId, final Object propId) {
		Property prop;
		if (getTableHolder().isGeneratedColumn(propId)) {
			prop = getTableHolder().getPropertyForGeneratedColumn(propId, rootItemId);
		} else {
			prop = getTableHolder().getContainerDataSource().getContainerProperty(rootItemId, propId);
			if (useTableFormatPropertyValue) {
				if (getTableHolder().isExportableFormattedProperty()) {
					final String formattedProp = getTableHolder().getFormattedPropertyValue(rootItemId, propId, prop);
					if (null == prop) {
						prop = new ObjectProperty<String>(formattedProp, String.class);
					} else {
						final Object val = prop.getValue();
						if (null == val) {
							prop = new ObjectProperty<String>(formattedProp, String.class);
						} else {
							if (!val.toString().equals(formattedProp)) {
								prop = new ObjectProperty<String>(formattedProp, String.class);
							}
						}
					}
				} else {
					// LOGGER.warning("Cannot use Table formatted property
					// unless Table is instance of " +
					// "ExportableFormattedProperty");
				}
			}
		}
		return prop;
	}

	private Class<?> getPropertyType(final Object propId) {
		Class<?> classType;
		if (getTableHolder().isGeneratedColumn(propId)) {
			classType = getTableHolder().getPropertyTypeForGeneratedColumn(propId);
		} else {
			classType = getTableHolder().getContainerDataSource().getType(propId);
		}
		return classType;
	}

	protected void addTotalsRow(final int currentRow, final int startRow) {
		totalsRow = sheet.createRow(currentRow);
		totalsRow.setHeightInPoints(30);
		Cell cell;
		CellRangeAddress cra;
		for (int col = 0; col < getPropIds().size(); col++) {
			final Object propId = ((Map<Short, CellStyle>) getPropIds()).get(col);
			cell = totalsRow.createCell(col);
			final Short poiAlignment = getTableHolder().getCellAlignment(propId);
			CellUtil.setAlignment(cell, workbook, poiAlignment);
			final Class<?> propType = getPropertyType(propId);
			if (isNumeric(propType)) {
				cra = new CellRangeAddress(startRow, currentRow - 1, col, col);
			} else {
				if (0 == col) {
					cell.setCellValue(createHelper.createRichTextString("Total"));
				}
			}
		}
	}

	/**
	 * Final formatting of the sheet upon completion of writing the data. For
	 * example, we can only size the column widths once the data is in the
	 * report and the sheet knows how wide the data is.
	 */
	protected void finalSheetFormat() {
		workbook.setActiveSheet(workbook.getSheetIndex(sheet));
		if (hierarchicalTotalsSheet != null) {
			workbook.removeSheetAt(workbook.getSheetIndex(hierarchicalTotalsSheet));
		}
		for (int col = 0; col < getPropIds().size(); col++) {
			sheet.autoSizeColumn(col);
		}
	}

	/**
	 * Returns the default title style. Obtained from:
	 * http://svn.apache.org/repos/asf/poi
	 * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
	 *
	 * @param wb
	 *            the wb
	 * @return the cell style
	 */
	protected CellStyle defaultTitleCellStyle(final Workbook wb) {
		CellStyle style;
		final Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 18);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(titleFont);
		return style;
	}

	/**
	 * Returns the default header style. Obtained from:
	 * http://svn.apache.org/repos/asf/poi
	 * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
	 *
	 * @param wb
	 *            the wb
	 * @return the cell style
	 */
	protected CellStyle defaultHeaderCellStyle(final Workbook wb) {
		CellStyle style;
		final Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 11);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		return style;
	}

	/**
	 * Returns the default data cell style. Obtained from:
	 * http://svn.apache.org/repos/asf/poi
	 * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
	 *
	 * @param wb
	 *            the wb
	 * @return the cell style
	 */
	protected CellStyle defaultDataCellStyle(final Workbook wb) {
		CellStyle style;
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setDataFormat(doubleDataFormat);
		return style;
	}

	/**
	 * Returns the default totals row style for Double data. Obtained from:
	 * http://svn.apache.org/repos/asf/poi
	 * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
	 *
	 * @param wb
	 *            the wb
	 * @return the cell style
	 */
	protected CellStyle defaultTotalsDoubleCellStyle(final Workbook wb) {
		CellStyle style;
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(doubleDataFormat);
		return style;
	}

	/**
	 * Returns the default totals row style for Integer data. Obtained from:
	 * http://svn.apache.org/repos/asf/poi
	 * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
	 *
	 * @param wb
	 *            the wb
	 * @return the cell style
	 */
	protected CellStyle defaultTotalsIntegerCellStyle(final Workbook wb) {
		CellStyle style;
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(integerDataFormat);
		return style;
	}

	protected short defaultDoubleDataFormat(final Workbook wb) {
		return createHelper.createDataFormat().getFormat("0.00");
	}

	protected short defaultIntegerDataFormat(final Workbook wb) {
		return createHelper.createDataFormat().getFormat("0");
	}

	protected short defaultDateDataFormat(final Workbook wb) {
		return createHelper.createDataFormat().getFormat("mm/dd/yyyy");
	}

	public void setDoubleDataFormat(final String excelDoubleFormat) {
		CellStyle prevDoubleDataStyle = null;
		doubleDataFormat = createHelper.createDataFormat().getFormat(excelDoubleFormat);
		if (null != prevDoubleDataStyle) {
			doubleCellStyle = prevDoubleDataStyle;
			doubleCellStyle.setDataFormat(doubleDataFormat);
		}
	}

	public void setIntegerDataFormat(final String excelIntegerFormat) {
		CellStyle prevIntegerDataStyle = null;
		integerDataFormat = createHelper.createDataFormat().getFormat(excelIntegerFormat);
		if (null != prevIntegerDataStyle) {
			integerCellStyle = prevIntegerDataStyle;
			integerCellStyle.setDataFormat(integerDataFormat);
		}
	}

	public void setDateDataFormat(final String excelDateFormat) {
		CellStyle prevDateDataStyle = null;
		dateDataFormat = createHelper.createDataFormat().getFormat(excelDateFormat);
		if (null != prevDateDataStyle) {
			dateCellStyle = prevDateDataStyle;
			dateCellStyle.setDataFormat(dateDataFormat);
		}
	}

	/**
	 * Utility method to determine whether value being put in the Cell is
	 * numeric.
	 *
	 * @param type
	 *            the type
	 * @return true, if is numeric
	 */
	private boolean isNumeric(final Class<?> type) {
		if (isIntegerLongShortOrBigDecimal(type)) {
			return true;
		}
		if (isDoubleOrFloat(type)) {
			return true;
		}
		return false;
	}

	/**
	 * Utility method to determine whether value being put in the Cell is
	 * integer-like type.
	 *
	 * @param type
	 *            the type
	 * @return true, if is integer-like
	 */
	private boolean isIntegerLongShortOrBigDecimal(final Class<?> type) {
		if ((Integer.class.equals(type) || (int.class.equals(type)))) {
			return true;
		}
		if ((Long.class.equals(type) || (long.class.equals(type)))) {
			return true;
		}
		if ((Short.class.equals(type)) || (short.class.equals(type))) {
			return true;
		}
		if ((BigDecimal.class.equals(type)) || (BigDecimal.class.equals(type))) {
			return true;
		}
		return false;
	}

	/**
	 * Utility method to determine whether value being put in the Cell is
	 * double-like type.
	 *
	 * @param type
	 *            the type
	 * @return true, if is double-like
	 */
	private boolean isDoubleOrFloat(final Class<?> type) {
		if ((Double.class.equals(type)) || (double.class.equals(type))) {
			return true;
		}
		if ((Float.class.equals(type)) || (float.class.equals(type))) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the workbook.
	 *
	 * @return the workbook
	 */
	public Workbook getWorkbook() {
		return this.workbook;
	}

	/**
	 * Gets the sheet name.
	 *
	 * @return the sheet name
	 */
	public String getSheetName() {
		return this.sheetName;
	}

	/**
	 * Gets the report title.
	 *
	 * @return the report title
	 */
	public String getReportTitle() {
		return this.reportTitle;
	}

	/**
	 * Gets the export file name.
	 *
	 * @return the export file name
	 */
	public String getExportFileName() {
		return this.exportFileName;
	}

	/**
	 * Gets the cell style used for report data..
	 *
	 * @return the cell style
	 */
	public CellStyle getDoubleDataStyle() {
		return this.doubleCellStyle;
	}

	/**
	 * Gets the cell style used for report data..
	 *
	 * @return the cell style
	 */
	public CellStyle getIntegerDataStyle() {
		return this.integerCellStyle;
	}

	public CellStyle getDateDataStyle() {
		return this.dateCellStyle;
	}

	/**
	 * Gets the cell style used for the report headers.
	 *
	 * @return the column header style
	 */
	public CellStyle getColumnHeaderStyle() {
		return this.columnHeaderCellStyle;
	}

	/**
	 * Gets the cell title used for the report title.
	 *
	 * @return the title style
	 */
	public CellStyle getTitleStyle() {
		return this.titleCellStyle;
	}

	/**
	 * Sets the text used for the report title.
	 *
	 * @param reportTitle
	 *            the new report title
	 */
	public void setReportTitle(final String reportTitle) {
		this.reportTitle = reportTitle;
	}

	/**
	 * Sets the export file name.
	 *
	 * @param exportFileName
	 *            the new export file name
	 */
	public void setExportFileName(final String exportFileName) {
		this.exportFileName = exportFileName;
	}

	/**
	 * Sets the cell style used for report data.
	 *
	 * @param doubleDataStyle
	 *            the new data style
	 */
	public void setDoubleDataStyle(final CellStyle doubleDataStyle) {
		this.doubleCellStyle = doubleDataStyle;
	}

	/**
	 * Sets the cell style used for report data.
	 *
	 * @param integerDataStyle
	 *            the new data style
	 */
	public void setIntegerDataStyle(final CellStyle integerDataStyle) {
		this.integerCellStyle = integerDataStyle;
	}

	/**
	 * Sets the cell style used for report data.
	 *
	 * @param dateDataStyle
	 *            the new data style
	 */
	public void setDateDataStyle(final CellStyle dateDataStyle) {
		this.dateCellStyle = dateDataStyle;
	}

	/**
	 * Sets the cell style used for the report headers.
	 *
	 * @param columnHeaderStyle
	 *            CellStyle
	 */
	public void setColumnHeaderStyle(final CellStyle columnHeaderStyle) {
		this.columnHeaderCellStyle = columnHeaderStyle;
	}

	/**
	 * Sets the cell style used for the report title.
	 *
	 * @param titleStyle
	 *            the new title style
	 */
	public void setTitleStyle(final CellStyle titleStyle) {
		this.titleCellStyle = titleStyle;
	}

	/**
	 * Gets the title row.
	 *
	 * @return the title row
	 */
	public Row getTitleRow() {
		return this.titleRow;
	}

	/**
	 * Gets the header row.
	 *
	 * @return the header row
	 */
	public Row getHeaderRow() {
		return this.headerRow;
	}

	/**
	 * Gets the totals row.
	 *
	 * @return the totals row
	 */
	public Row getTotalsRow() {
		return this.totalsRow;
	}

	/**
	 * Gets the cell style used for the totals row.
	 *
	 * @return the totals style
	 */
	public CellStyle getTotalsDoubleStyle() {
		return this.totalsDoubleCellStyle;
	}

	/**
	 * Sets the cell style used for the totals row.
	 *
	 * @param totalsDoubleStyle
	 *            the new totals style
	 */
	public void setTotalsDoubleStyle(final CellStyle totalsDoubleStyle) {
		this.totalsDoubleCellStyle = totalsDoubleStyle;
	}

	/**
	 * Gets the cell style used for the totals row.
	 *
	 * @return the totals style
	 */
	public CellStyle getTotalsIntegerStyle() {
		return this.totalsIntegerCellStyle;
	}

	/**
	 * Sets the cell style used for the totals row.
	 *
	 * @param totalsIntegerStyle
	 *            the new totals style
	 */
	public void setTotalsIntegerStyle(final CellStyle totalsIntegerStyle) {
		this.totalsIntegerCellStyle = totalsIntegerStyle;
	}

	/**
	 * Flag indicating whether a totals row will be added to the report or not.
	 *
	 * @return true, if totals row will be added
	 */
	public boolean isDisplayTotals() {
		return this.displayTotals;
	}

	/**
	 * Sets the flag indicating whether a totals row will be added to the report
	 * or not.
	 *
	 * @param displayTotals
	 *            boolean
	 */
	public void setDisplayTotals(final boolean displayTotals) {
		this.displayTotals = displayTotals;
	}

	public void setUseTableFormatPropertyValue(final boolean useFormatPropertyValue) {
		this.useTableFormatPropertyValue = useFormatPropertyValue;
	}

	/**
	 * See value of flag indicating whether the first column should be treated
	 * as row headers.
	 *
	 * @return boolean
	 */
	public boolean hasRowHeaders() {
		return this.rowHeaders;
	}

	/**
	 * Method getRowHeaderStyle.
	 *
	 * @return CellStyle
	 */
	public CellStyle getRowHeaderStyle() {
		return this.rowHeaderCellStyle;
	}

	/**
	 * Set value of flag indicating whether the first column should be treated
	 * as row headers.
	 *
	 * @param rowHeaders
	 *            boolean
	 */
	public void setRowHeaders(final boolean rowHeaders) {
		this.rowHeaders = rowHeaders;
	}

	/**
	 * Method setRowHeaderStyle.
	 *
	 * @param rowHeaderStyle
	 *            CellStyle
	 */
	public void setRowHeaderStyle(final CellStyle rowHeaderStyle) {
		this.rowHeaderCellStyle = rowHeaderStyle;
	}

	@Override
	public String getFilename() {
		return getDownloadFileName();
	}

}