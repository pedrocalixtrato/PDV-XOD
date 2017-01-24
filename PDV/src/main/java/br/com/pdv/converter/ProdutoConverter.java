package br.com.pdv.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.pdv.dao.ProdutoDAO;
import br.com.pdv.domain.Produto;
import br.com.pdv.util.jpa.CDIServiceLocator;

@SuppressWarnings("rawtypes")
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	//@Inject
		private ProdutoDAO produtoDAO;
		
		public ProdutoConverter() {
			produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Produto retorno = null;
			
			if (value != null) {
				Long codigo = new Long(value);
				retorno = produtoDAO.buscarPeloCodigo(codigo);
			}
			
			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				Produto produto = (Produto) value;
				return produto.getCodigo() == null ? null : produto.getCodigo().toString();
			}
			
			return "";
		}
}