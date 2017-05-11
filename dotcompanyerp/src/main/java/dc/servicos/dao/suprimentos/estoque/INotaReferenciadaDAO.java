package dc.servicos.dao.suprimentos.estoque;

import java.util.List;

import dc.entidade.suprimentos.NotaReferenciada;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface INotaReferenciadaDAO extends AbstractDAO<NotaReferenciada> {

	List<NotaReferenciada> buscaNotasReferenciadas(NotaFiscal currentBean);

}
