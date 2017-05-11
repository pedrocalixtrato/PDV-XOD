package dc.servicos.dao.suprimentos.estoque;

import dc.entidade.suprimentos.NFeTransporte;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface INFeTransporteDAO extends AbstractDAO<NFeTransporte> {

	NFeTransporte buscaTransportePorNota(NotaFiscal currentBean);

}
