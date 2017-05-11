package dc.model.dao.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.AcessorioOsEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface AcessorioOsDAO<T> extends AbstractDAO<T> {

	List<AcessorioOsEntity> findByOrdemServico(OrdemServicoEntity ordemServico);

}