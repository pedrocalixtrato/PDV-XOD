package dc.servicos.dao.suprimentos.estoque;

import dc.entidade.suprimentos.NfeLocalEntrega;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface INFeLocalEntregaDAO extends AbstractDAO<NfeLocalEntrega> {

	NfeLocalEntrega buscaEntregaPorNota(NotaFiscal currentBean);

}
