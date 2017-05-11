package dc.visao.framework.component.export;

import com.vaadin.data.Property;


public interface ExportableColumnGenerator extends com.vaadin.ui.Table.ColumnGenerator {

    Property getGeneratedProperty(Object itemId, Object columnId);
    // the type of the generated property
    Class<?> getType();

}
