package dc.model.business.ordemservico;

import dc.entidade.ordemservico.TipoEfetivacaoOsEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface TipoEfetivacaoOsBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	TipoEfetivacaoOsEntity findByCodigo(Integer codigo) throws Exception;
}