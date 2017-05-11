package dc.model.dao.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.MaterialServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface MaterialServicoDAO<T> extends AbstractDAO<T> {
	List<MaterialServicoEntity> findByOrdemServico(OrdemServicoEntity t);
}