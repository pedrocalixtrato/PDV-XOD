package dc.model.business.tributario;

import java.util.List;

import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface IcmsCustomizadoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	
	public List<T> list() throws Exception;

}