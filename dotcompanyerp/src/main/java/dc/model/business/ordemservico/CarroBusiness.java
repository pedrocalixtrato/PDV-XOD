package dc.model.business.ordemservico;

import java.util.List;

import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */

public interface CarroBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {

	public List<T> list() throws Exception;

}