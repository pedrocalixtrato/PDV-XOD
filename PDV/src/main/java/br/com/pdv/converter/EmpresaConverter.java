package br.com.pdv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pdv.dao.EmpresaDAO;
import br.com.pdv.domain.Empresa;
import br.com.pdv.util.jpa.CDIServiceLocator;




@FacesConverter ("empresaConverter")
public class EmpresaConverter implements Converter {
	
	private EmpresaDAO empresaDAO;

	public EmpresaConverter() {
		this.empresaDAO = CDIServiceLocator.getBean(EmpresaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Empresa retorno = null;

		if (value != null) {
			retorno = this.empresaDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Empresa) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}
}