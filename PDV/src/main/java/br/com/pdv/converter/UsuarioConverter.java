package br.com.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Usuario;
import br.com.pdv.util.jpa.CDIServiceLocator;




@FacesConverter ("usuarioConverter")
public class UsuarioConverter implements Converter {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarioDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Usuario) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}
}