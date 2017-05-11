package dc.model.business.ordemservico;

import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface LaudoTecnicoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	LaudoTecnicoEntity findByOrdemServico(OrdemServicoEntity t);
}