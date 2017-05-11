package dc.model.business.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.EntradaServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface EntradaServicoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	List<EntradaServicoEntity> findByOrdemServico(OrdemServicoEntity t);
}