package dc.model.business.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface OrcamentoItemOsBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {

	public List<T> findByOrcamentoOs(OrcamentoOsEntity orcamentoOs) throws Exception;

}