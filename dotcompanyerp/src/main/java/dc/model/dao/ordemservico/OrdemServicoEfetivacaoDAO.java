package dc.model.dao.ordemservico;

import dc.entidade.ordemservico.OrdemServicoEfetivacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface OrdemServicoEfetivacaoDAO<T> extends AbstractDAO<T> {
	OrdemServicoEfetivacaoEntity findByOrdemServico(OrdemServicoEntity t);
}