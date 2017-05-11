package dc.model.dao.ordemservico;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ordemservico.ParametroOsEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface ParametroOsDAO<T> extends AbstractDAO<T> {
	ParametroOsEntity buscaParametroOs(EmpresaEntity empresa);
}