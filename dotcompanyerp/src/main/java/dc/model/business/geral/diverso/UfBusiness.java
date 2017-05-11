package dc.model.business.geral.diverso;

import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface UfBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {

	public T getObject(String sigla) throws Exception;

}