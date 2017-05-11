package dc.model.dao.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.VendaPecaEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface VendaPecaDAO<T> extends AbstractDAO<T> {
	List<VendaPecaEntity> findByOrdemServico(OrdemServicoEntity t);
}