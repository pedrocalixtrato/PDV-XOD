package br.com.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.domain.Pedido;
import br.com.pdv.util.jpa.CDIServiceLocator;





@FacesConverter (forClass = Pedido.class)
public class PedidoConverter implements Converter {
	
	//@Inject
		private PedidoDAO pedidoDAO;
		
		public PedidoConverter() {
			this.pedidoDAO = CDIServiceLocator.getBean(PedidoDAO.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Pedido retorno = null;

						
			if(value != null){
				Long id = new  Long(value);				
				retorno = pedidoDAO.buscarPeloCodigo(id);
			}

			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				Pedido pedido = (Pedido) value;
				return pedido.getId() == null ? null : pedido.getId().toString();
			}
			return "";
		}
}