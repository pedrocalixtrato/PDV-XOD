package dc.model.business.ordemservico;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ordemservico.ParametroOsEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface ParametroOsBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {
	ParametroOsEntity buscaParametroOs(EmpresaEntity empresa);
}