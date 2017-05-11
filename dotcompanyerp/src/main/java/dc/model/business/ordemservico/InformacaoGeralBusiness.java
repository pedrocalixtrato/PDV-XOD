package dc.model.business.ordemservico;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface InformacaoGeralBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity t);
}