//package br.com.pdv.converter;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//
//import br.com.pdv.domain.Pessoa;
//
//
//@FacesConverter(forClass = Pessoa.class)
//public class PessoaConverter implements Converter {
//	
//	@Inject
//	private EntityManager em;
//	
//
//
//
//
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		Pessoa retorno = null;
//
//		if (value != null) {
//			retorno = this.em.find(Pessoa.class, Long.valueOf(value));
//		}
//
//		return retorno;
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value != null) {
//			Long codigo = ((Pessoa) value).getCodigo();
//			String retorno = (codigo == null ? null : codigo.toString());
//
//			return retorno;
//		}
//
//		return "";
//	}
//}
