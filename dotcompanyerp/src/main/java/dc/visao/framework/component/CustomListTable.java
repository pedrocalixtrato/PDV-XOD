package dc.visao.framework.component;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sun.istack.logging.Logger;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

import dc.visao.spring.SecuritySessionProvider;

public class CustomListTable extends Table {

	private static final long serialVersionUID = 735711713125549382L;
	public static Logger logger = Logger.getLogger(CustomListTable.class);
	private CompanyFileHandler fileHandler = new CompanyFileHandler();
	private String entityName;

	/*
	 * 
	 * Selecionar (AJUSTES)
	 */

	public static final Object CUSTOM_SELECT_ID = "";
	final CheckBox checkbox = new CheckBox();

	private static final String COLLUMN_INFO = "col_info";
	private static final String COLLAPSED = "collapsed";

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
			if (!propertyId.equals("DEBUG_PROPERTY_ID_QUERY_COUT") && !propertyId.equals("DEBUG_PROPERTY_ID_BATCH_INDEX")
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

}
