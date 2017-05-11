package dc.servicos.business.nfe;

import java.util.List;

import dc.model.business.AbstractBusiness;

public interface NfeDeclaracaoImportacaoBusiness<T> extends AbstractBusiness<T> {

	public List<T> find(String s) throws Exception;

	public List<T> listAll() throws Exception;

	public List<T> search(T entity) throws Exception;

}