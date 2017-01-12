//package br.com.pdv.converter;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//
//import br.com.pdv.domain.Cliente;
//
//
//
//
//@SuppressWarnings("rawtypes")
//@FacesConverter (forClass = Cliente.class)
//public class ClienteConverter implements Converter {
//	
//	@Inject
//	private EntityManager em;
//	
//	
//	
//
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		Cliente retorno = null; 
//		System.out.printf("o valor do value é",value);
//
//		if (value != null) {
//			this.em.find(Cliente.class, Long.valueOf(value));
//		}
//
//		return retorno;
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value != null) {
//			Long codigo = ((Cliente) value).getCodigo();
//			String retorno = (codigo == null ? null : codigo.toString());
//
//			return retorno;
//		}
//
//		return "";
//	}
//}