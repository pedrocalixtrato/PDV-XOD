package dc.model.business.geral.pessoal;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface ClienteBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {
	ClienteEntity findById(ClienteEntity t);
}