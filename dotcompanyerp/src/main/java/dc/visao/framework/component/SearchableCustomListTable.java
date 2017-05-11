package dc.visao.framework.component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.tepi.filtertable.FilterTable;
import org.vaadin.addons.lazyquerycontainer.CompositeItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sun.istack.logging.Logger;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;

import dc.anotacoes.AnotacoesUtil;
import dc.anotacoes.Caption;
import dc.visao.spring.SecuritySessionProvider;

public class SearchableCustomListTable extends FilterTable {

	public enum State {
		CREATED, PROCESSING, PROCESSED, FINISHED;
	}

	private static final long serialVersionUID = 735711713125549382L;
	public static Logger logger = Logger.getLogger(SearchableCustomListTable.class);
	private CompanyFileHandler fileHandler = new CompanyFileHandler();
	private String entityName;
	private boolean filterReset = true;

	public static final Object CUSTOM_SELECT_ID = "";
	final CheckBox checkbox = new CheckBox();

	private static final String COLLUMN_INFO = "col_info";
	private static final String COLLAPSED = "collapsed";
	private static final DecimalFormat CURRENCY_DECIMAL_FORMAT =  new DecimalFormat("'R$' 0.00");
	private static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("0.00");

	public void setEntityName(String name) {
		this.entityName = name;
	}

	public void setColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException {
		if (!propertyId.equals(CUSTOM_SELECT_ID)) {
			super.setColumnCollapsed(propertyId, collapsed);
			logger.info("collapsed" + collapsed);
			logger.info(String.valueOf(propertyId));
			saveToFile();
		}

	}

	public boolean loadFromFile() {
		Integer userId = SecuritySessionProvider.getUsuario().getId();
		Integer companyId = SecuritySessionProvider.getUsuario().getConta().getEmpresa().getId();
		JsonArray collumns = fileHandler.load(String.valueOf(companyId), String.valueOf(userId), entityName);
		if (collumns != null && collumns.size() > 0) {
			java.util.Iterator<JsonElement> it = collumns.iterator();
			ArrayList<String> vaadinCols = new ArrayList<String>();
			while (it.hasNext()) {
				JsonElement e = it.next();
				String colName = mapSingleCollumn(e.getAsJsonObject());
				if (colName != null) {
					vaadinCols.add(colName);
				}
			}
			this.setVisibleColumns(vaadinCols.toArray());
		}
		return collumns != null && collumns.size() > 0;
	}

	private String mapSingleCollumn(JsonObject o) {
		JsonObject colInfo = o.getAsJsonObject(COLLUMN_INFO);
		String colName = mapColInfo(colInfo);
		if (o.has(COLLAPSED)) {
			setColumnCollapsedWithoutSave(colName, o.get(COLLAPSED).getAsBoolean());
		}
		return colName;

	}

	private void setColumnCollapsedWithoutSave(String pid, boolean collapsed) {
		super.setColumnCollapsed(pid, collapsed);

	}

	private String mapColInfo(JsonObject o) {
		Set<Entry<String, JsonElement>> s = o.entrySet();
		java.util.Iterator<Entry<String, JsonElement>> it = s.iterator();

		while (it.hasNext()) {
			Entry k = it.next();
			String collumnName = String.valueOf(k.getKey());
			String collumnWidth = String.valueOf(k.getValue());
			logger.info(collumnName);
			logger.info(collumnWidth);

			JsonPrimitive p = (JsonPrimitive) k.getValue();
			setColumnWidth(k.getKey(), p.getAsInt());
			return collumnName;
		}

		return null;
	}

	public void saveToFile() {
		Integer userId = SecuritySessionProvider.getUsuario().getId();
		Integer companyId = SecuritySessionProvider.getUsuario().getConta().getEmpresa().getId();
		Object[] collumns = getVisibleColumns();
		logger.info("saving to file, all visible collumns");
		JsonArray columnsMetadata = new JsonArray();
		for (Object o : collumns) {
			JsonObject colRoot = new JsonObject();
			JsonObject colInfo = new JsonObject();
			String propertyId = String.valueOf(o);
			int w = getColumnWidth(propertyId);
			logger.info("property: " + propertyId);
			logger.info("width" + w);
			if (!propertyId.equals("DEBUG_PROPERTY_ID_QUERY_COUT")
					&& !propertyId.equals("DEBUG_PROPERTY_ID_BATCH_INDEX")
					&& !propertyId.equals("DEBUG_PROPERTY_ID_BATCH_QUERY_TIME")) {
				colInfo.addProperty(propertyId, String.valueOf(w));
			}
			colRoot.add(COLLUMN_INFO, colInfo);
			colRoot.addProperty(COLLAPSED, isColumnCollapsed(propertyId));
			columnsMetadata.add(colRoot);
		}

		fileHandler.save(columnsMetadata, String.valueOf(companyId), String.valueOf(userId), entityName);

	}

	public void setFileHandler(CompanyFileHandler handle) {
		this.fileHandler = handle;
	}

	@Override
	public void setRefreshingEnabled(boolean enabled) {

		super.setRefreshingEnabled(enabled);
		refreshFooter();

	}

	private void refreshFooter() {
		if (getVisibleColumns().length > 0) {
			setColumnFooter(SearchableCustomListTable.CUSTOM_SELECT_ID, "Total: ");

			setFooterVisible(true);

			Object[] columnHeaders2 = getVisibleColumns();
			Object column = null;
			for (Object col : columnHeaders2) {
				if (col != null && !"".equals(col)) {
					column = col;
					break;
				}
			}

			setColumnFooter(SearchableCustomListTable.CUSTOM_SELECT_ID, null);
			setColumnFooter(column, null);
			setColumnFooter(column, size() + " registro(s) encontrado(s)");
			Map<String, BigDecimal> somatorio = new HashMap<String, BigDecimal>();
			List<Field> summableField = new ArrayList<Field>();
			try {

				setSummableFields(summableField);

				for (Object itemId : this.getItemIds()) {
					CompositeItem item = (CompositeItem) this.getItem(itemId);
					item.getItemKeys();
					BeanItem item2 = (BeanItem) item.getItem("bean");
					Object beanItem = item2.getBean();

					for (Field field : summableField) {
						Object value = field.get(beanItem);

						BigDecimal toSum = null;

						if (value instanceof Long) {
							toSum = new BigDecimal((Long) value);
						} else if (value instanceof Integer) {
							toSum = new BigDecimal((Integer) value);
						} else if (value instanceof Double) {
							toSum = new BigDecimal((Double) value);
						} else if (value instanceof BigDecimal) {
							toSum = (BigDecimal) value;
						} else {
							toSum = BigDecimal.ZERO;
						}

						BigDecimal sum = somatorio.get(field.getName());
						if (sum == null) {
							sum = BigDecimal.ZERO;
						}

						sum = sum.add(toSum);

						somatorio.put(field.getName(), sum);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			setColumnFooter(SearchableCustomListTable.CUSTOM_SELECT_ID, "Total: ");
			for (Field field : summableField) {
				BigDecimal sum = somatorio.get(field.getName());
				if (sum != null) {
					setColumnFooter(field.getName(), getDecimalFormat(field).format(sum.doubleValue()));
				}
			}

		}
	}

	private DecimalFormat getDecimalFormat(Field field) {
		try {
			NumberFormat anotacao = field.getAnnotation(NumberFormat.class);
			if (anotacao != null) {
				for (Method method : anotacao.annotationType().getDeclaredMethods()) {
					Object value;

					value = method.invoke(anotacao, (Object[]) null);

					if ("style".equals(method.getName()) && value.equals(Style.CURRENCY)) {
						return CURRENCY_DECIMAL_FORMAT;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return DEFAULT_DECIMAL_FORMAT;

	}

	private void setSummableFields(List<Field> summableField) {
		if (this.getItemIds().size() > 0) {

			CompositeItem item = (CompositeItem) this.getItem(this.getItemIds().iterator().next());
			item.getItemKeys();
			BeanItem item2 = (BeanItem) item.getItem("bean");
			Object beanItem = item2.getBean();

			Class<? extends Object> entityClass = beanItem.getClass();
			Field[] declaredFields = entityClass.getDeclaredFields();
			Caption captionAnn = null;
			for (Field field : declaredFields) {
				Object[] collumns = getVisibleColumns();
				boolean fieldVisable = false;
				for (Object collumn : collumns) {
					if (collumn.toString().equals(field.getName())) {
						fieldVisable = true;
						break;
					}
				}
				if (!fieldVisable) {
					continue;
				}

				captionAnn = AnotacoesUtil.getAnotacao(Caption.class, entityClass, field.getName());

				if (captionAnn != null && captionAnn.sum()) {
					field.setAccessible(true);
					summableField.add(field);
				}
			}

		}
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {
		filterReset = true;
		super.setContainerDataSource(newDataSource);

		Map<Object, Component> columns = getColumnIdToFilterMap();

		if (columns != null) {
			for (Object property : columns.keySet()) {
				AbstractField<?> component = (AbstractField<?>) columns.get(property);

				component.addValueChangeListener(new ValueChangeListener() {

					@Override
					public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
						refreshFooter();
					}
				});
			}
		}
	}

	@Override
	public void resetFilters() {
		if (filterReset) {
			super.resetFilters();
		}
		filterReset = false;
	}
}
