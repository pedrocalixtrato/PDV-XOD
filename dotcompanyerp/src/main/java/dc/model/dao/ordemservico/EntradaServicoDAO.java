package dc.model.dao.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.EntradaServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface EntradaServicoDAO<T> extends AbstractDAO<T> {
	List<EntradaServicoEntity> findByOrdemServico(OrdemServicoEntity t);
}