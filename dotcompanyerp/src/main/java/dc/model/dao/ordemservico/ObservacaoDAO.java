package dc.model.dao.ordemservico;

import dc.entidade.ordemservico.ObservacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface ObservacaoDAO<T> extends AbstractDAO<T> {

	ObservacaoEntity buscaObservacao(OrdemServicoEntity ordemServico);

}