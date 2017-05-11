package dc.model.business;

import java.io.Serializable;
import java.util.List;

import com.vaadin.data.Container.Filter;

/**
 * 
 * 
 */

public interface AbstractBusiness<T> {

	public abstract Class<T> getEntityClass();

	public void delete(T t) throws Exception;

	public void deleteAll(List<Serializable> list) throws Exception;
	
	

	public T find(Serializable id) throws Exception;

	public T find(T t) throws Exception;

	public List<T> find(String s) throws Exception;

	public List<T> findAll() throws Exception;

	public List<T> findAll(T t) throws Exception;

	public List<T> fullTextSearch(String valor) throws Exception;

	public List<T> fullTextSearch(String valor, int first, int pageSize,
			String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception;

	public List<T> fullTextSearch(String valor, String[] sortingFields,
			boolean[] states, List<Filter> filters) throws Exception;

	public <E> List<E> getAll(final Class<E> type) throws Exception;

	public void save(T t) throws Exception;

	public <E> void saveOrUpdate(final E o) throws Exception;

}