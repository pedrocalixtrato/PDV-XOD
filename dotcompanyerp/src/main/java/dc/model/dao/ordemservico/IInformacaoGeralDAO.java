package dc.model.dao.ordemservico;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface IInformacaoGeralDAO extends AbstractDAO<InformacaoGeralEntity> {
	InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity t);
}