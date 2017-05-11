package dc.servicos.dao.suprimentos.estoque;

import dc.entidade.suprimentos.NotaFiscalEmitente;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface INFEmitenteDAO extends AbstractDAO<NotaFiscalEmitente> {

	NotaFiscalEmitente findByNota(NotaFiscal currentBean);

}
