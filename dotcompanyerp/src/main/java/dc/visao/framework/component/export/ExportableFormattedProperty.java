package dc.visao.framework.component.export;

import java.io.Serializable;

import com.vaadin.data.Property;

public interface ExportableFormattedProperty extends Serializable {

    public String getFormattedPropertyValue(Object rowId, Object colId, Property property);

}
