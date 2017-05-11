package dc.model.business.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.MaterialServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface MaterialServicoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	List<MaterialServicoEntity> findByOrdemServico(OrdemServicoEntity t);
}