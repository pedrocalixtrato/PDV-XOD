package dc.visao.sistema;

import java.util.Collection;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TreeTable;

import dc.control.enums.SimNaoEn;
import dc.framework.SimNaoConverter;

public class CustomTableFieldFactory implements TableFieldFactory {

	public static Logger logger = Logger.getLogger(CustomTableFieldFactory.class);

	public static final Object ALTERA_PROPERTY = "podeAlterar";
	public static final Object EXCLUI_PROPERTY = "podeConsultar";
	public static final Object CONSULTA_PROPERTY = "podeExcluir";
	public static final Object INSERE_PROPERTY = "podeInserir";
	public static final Object HABILITA_PROPERTY = "habilitado";

	public static final Object OBJ_PROPERTY = "papel_menu";

	private boolean listenCheckChanges = false;
	
	
	public CustomTableFieldFactory(boolean listenCheckChanges){
		this.listenCheckChanges  = listenCheckChanges;
	}
	
	public void setCheckChanges(boolean c){
		this.listenCheckChanges =  c;
	}
	
	@Override
	public Field<?> createField(final Container container, final Object itemId,
			final Object propertyId, final Component uiContext) {
		if(propertyId == CONSULTA_PROPERTY || propertyId == EXCLUI_PROPERTY || propertyId == ALTERA_PROPERTY|| propertyId == INSERE_PROPERTY || propertyId == HABILITA_PROPERTY){
			CheckBox c = new CheckBox();
			
				c.addValueChangeListener(new ValueChangeListener() {
					
					@Override
					public void valueChange(ValueChangeEvent event) {
						if(listenCheckChanges){
							// TODO Auto-generated method stub
							final String valueString = String.valueOf(event.getProperty()
			                        .getValue());
			                if(uiContext instanceof TreeTable){
			                	TreeTable t = (TreeTable) uiContext;
			                	if(event.getProperty().getValue() instanceof Boolean){
			                		Boolean marked = (Boolean) event.getProperty().getValue() ;
			                		Collection c = t.getChildren(itemId);
			                		if(c!= null){
			                			for(Object o : c){
				                			Item i = t.getItem(o);
				                			if(marked){
				                				i.getItemProperty(propertyId).setValue(SimNaoEn.S);	
				                			}else{
				                				i.getItemProperty(propertyId).setValue(SimNaoEn.N);
				                			}
				                		}	
			                		}
			                		
			                	}
			                	
			                	
			                }	
						}
						
					}
				});	
			
			c.setConverter(new SimNaoConverter());
			return c;
		}else{
			return null;	
		}
		
	}

}
