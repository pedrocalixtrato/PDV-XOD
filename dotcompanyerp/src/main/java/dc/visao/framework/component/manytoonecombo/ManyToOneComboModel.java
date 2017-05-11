package dc.visao.framework.component.manytoonecombo;

import java.util.List;


public interface ManyToOneComboModel<T> {
	
	String getCaptionProperty();
	
	Class<T> getEntityClass();
	
	void onCriarNovo(String filter);
	
	List<T> getResultado(String q);

	void onEditar(T value);

	List<T> getAll();

	void onAdvancedSearch();
	
	
}
