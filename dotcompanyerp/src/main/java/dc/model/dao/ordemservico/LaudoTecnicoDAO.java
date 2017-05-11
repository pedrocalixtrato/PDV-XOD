package dc.model.dao.ordemservico;

import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface LaudoTecnicoDAO<T> extends AbstractDAO<T> {
	LaudoTecnicoEntity findByOrdemServico(OrdemServicoEntity t);
}