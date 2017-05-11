package dc.servicos.dao.framework.geral;

import java.io.Serializable;

import dc.model.dao.AbstractDAO;

public interface IListDAO extends AbstractDAO<Serializable>{

	public abstract void setPojoClass(Class c);

	public abstract Class<Serializable> getEntityClass();
	
	public abstract void setColunas(String[] colunas);

	public abstract String[] getColunas();

}