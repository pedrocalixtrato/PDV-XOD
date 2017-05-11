package dc.model.dao.ordemservico;

import dc.entidade.ordemservico.TipoEfetivacaoOsEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface TipoEfetivacaoOsDAO<T> extends AbstractDAO<T> {
	TipoEfetivacaoOsEntity findByCodigo(Integer codigo);
}