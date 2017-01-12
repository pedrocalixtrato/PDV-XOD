//package br.com.pdv.converter;
//
//
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//
//import br.com.pdv.domain.Produto;
//
//@SuppressWarnings("rawtypes")
//@FacesConverter(forClass = Produto.class)
//public class ProdutoConverter implements Converter {
//
//	@Inject
//	private EntityManager em;
//
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		Produto retorno = null;
//		
//
//		if (value != null) {
//			this.em.find(Produto.class, Long.valueOf(value));
//		}
//
//		return retorno;
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value != null) {
//			Long codigo = ((Produto) value).getCodigo();
//			String retorno = (codigo == null ? null : codigo.toString());
//
//			return retorno;
//		}
//
//		return "";
//	}
//}