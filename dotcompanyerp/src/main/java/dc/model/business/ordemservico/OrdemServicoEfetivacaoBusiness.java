package dc.model.business.ordemservico;

import dc.entidade.ordemservico.OrdemServicoEfetivacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface OrdemServicoEfetivacaoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	OrdemServicoEfetivacaoEntity findByOrdemServico(OrdemServicoEntity t);
}