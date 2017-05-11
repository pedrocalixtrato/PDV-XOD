package dc.servicos.dao.suprimentos.estoque;

import dc.entidade.suprimentos.NfeFatura;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface INFeFaturaDAO extends AbstractDAO<NfeFatura> {

	NfeFatura buscaFaturaPorNota(NotaFiscal currentBean);

}
