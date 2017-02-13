package br.com.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pdv.dao.GrupoDAO;
import br.com.pdv.domain.Grupo;
import br.com.pdv.util.jpa.CDIServiceLocator;

@FacesConverter("grupoConverter")
public class GrupoConverter implements Converter {

	private GrupoDAO grupoDAO;

	public GrupoConverter() {
		this.grupoDAO = CDIServiceLocator.getBean(GrupoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;

		if (value != null) {
			retorno = this.grupoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Grupo) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}
}
