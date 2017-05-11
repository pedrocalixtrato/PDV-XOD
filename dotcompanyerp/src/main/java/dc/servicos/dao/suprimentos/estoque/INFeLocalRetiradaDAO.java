package dc.servicos.dao.suprimentos.estoque;

import dc.entidade.suprimentos.NfeLocalRetirada;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface INFeLocalRetiradaDAO extends AbstractDAO<NfeLocalRetirada> {

	NfeLocalRetirada buscaRetiradaPorNota(NotaFiscal currentBean);

}
