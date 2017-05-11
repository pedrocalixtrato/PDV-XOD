package dc.model.business.ordemservico;

import dc.entidade.ordemservico.ObservacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface ObservacaoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	ObservacaoEntity buscaObservacao(OrdemServicoEntity t);
}