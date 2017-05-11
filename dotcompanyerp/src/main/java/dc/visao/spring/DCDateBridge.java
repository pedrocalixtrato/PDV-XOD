package dc.visao.spring;

import java.util.Date;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.builtin.NumericEncodingDateBridge;

public class DCDateBridge extends NumericEncodingDateBridge {
	public static Resolution RESOLUTION = Resolution.SECOND;


	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		if (value != null) {
			Long indexedValue = Long.valueOf(DateTools.dateToString((Date) value, RESOLUTION));
			luceneOptions.addNumericFieldToDocument(name, indexedValue, document);
		}
	}

	@Override
	public Object get(String name, Document document) {
		String fromLucene = document.get(name);
		return Long.parseLong(fromLucene);
	}
}