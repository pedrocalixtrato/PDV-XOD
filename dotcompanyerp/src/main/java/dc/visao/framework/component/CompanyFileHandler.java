package dc.visao.framework.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.lucene.util.StringHelper;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Scope;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.json.client.JSONParser;

@org.springframework.stereotype.Component
@Scope("singleton")
public class CompanyFileHandler {
	
	@Value("${user.home}") 
	private String homePath;
	
	@Value("${custom.companyRoot}") 
	private String customRoot;
	
	@Value("${custom.companyBaseFolder}") 
	private String customCompanyBaseFolder;
	
	private static final String TABLE_LIST_BASE_FOLDER = "tabelas";

	public void save(JsonArray cols,String companyId, String user, String entityName  ) {
		// TODO Auto-generated method stub
		try {
			 
			File file = new File(getTableListFullPath(companyId,user,entityName));
			file.getParentFile().mkdirs();
			FileWriter writer= new FileWriter(file);
		
			writer.write(cols.toString());
			writer.flush();
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getTableListFullPath(String companyId, String user, String entityName) {
		if(customRoot != null && !customRoot.trim().equals("")){
			return customRoot + getRelativeTableListPath(companyId,user,entityName);
		}else{
			return homePath +  getRelativeTableListPath(companyId,user,entityName);	
		}
		
	}

	private String getRelativeTableListPath(String companyId, String user,
			String entityName) {
		return "/" + customCompanyBaseFolder + "/" + companyId + "/" + TABLE_LIST_BASE_FOLDER + "/" + user + "/"+entityName + ".json";
	}

	public JsonArray load(String company,String user,String entityName) {
		File f = new File(getTableListFullPath(company,user, entityName));
		if(f.exists()){
			FileReader reader;
			try {
				reader = new FileReader(getTableListFullPath(company,user, entityName));
				JsonParser p2 = new JsonParser();
				JsonElement el = p2.parse(reader);
				reader.close();
				return el.getAsJsonArray();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;	
		}else{
			return null;
		}
		// TODO Auto-generated method stub
		
		
	}

}
