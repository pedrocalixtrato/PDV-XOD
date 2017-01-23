package br.com.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.domain.Clientes;
import br.com.pdv.util.jpa.CDIServiceLocator;




@SuppressWarnings("rawtypes")
@FacesConverter (forClass = Clientes.class)
public class ClienteConverter implements Converter {
	
	//@Inject
		private ClienteDAO clienteDAO;
		
		public ClienteConverter() {
			this.clienteDAO = (ClienteDAO) CDIServiceLocator.getBean(ClienteDAO.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Clientes retorno = null;

			if (value != null) {
				retorno = this.clienteDAO.buscarPeloCodigo(new Long(value));
			}

			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				return ((Clientes) value).getCodigo().toString();
			}
			return "";
		}
}