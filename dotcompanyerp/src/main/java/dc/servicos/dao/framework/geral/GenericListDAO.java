package dc.servicos.dao.framework.geral;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository
public class GenericListDAO extends AbstractCrudDAO<Serializable> implements IListDAO {

	private Class pojoClass;
	private String[] colunas;
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IListDAO#setPojoClass(java.lang.Class)
	 */
	@Override
	public void setPojoClass(Class c){
		this.pojoClass = c; 
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IListDAO#getEntityClass()
	 */
	@Override
	public Class<Serializable> getEntityClass() {
		// TODO Auto-generated method stub
		return pojoClass;
	}

	@Override
	public void setColunas(String[] colunas) {
		this.colunas = colunas;
		
	}

	@Override
	public String[] getColunas() {
		// TODO Auto-generated method stub
		return this.colunas;
	}
	
	@Override
	public String[] getDefaultSearchFields() {
		return getColunas();
	}
}
