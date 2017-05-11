package dc.visao.spring;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;

public class BigDecimalNumericFieldBridge  implements org.hibernate.search.bridge.TwoWayFieldBridge, org.hibernate.search.bridge.ParameterizedBridge {
	public static final BigDecimal storeFactor = BigDecimal.valueOf(100);

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		if (value != null) {
			BigDecimal decimalValue = (BigDecimal) value;
			long tmpLong = decimalValue.multiply(storeFactor).longValue();
			Long indexedValue = Long.valueOf(tmpLong);
			luceneOptions.addNumericFieldToDocument(name, indexedValue, document);
		}
	}

	@Override
	public Object get(String name, Document document) {
		String fromLucene = document.get(name);
		BigDecimal storedBigDecimal = new BigDecimal(fromLucene);
		return storedBigDecimal.divide(storeFactor);
	}

	@Override
	public void setParameterValues(Map<String, String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String objectToString(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}


}