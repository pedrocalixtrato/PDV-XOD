package dc.servicos.dao.suprimentos.estoque;

import java.util.List;

import dc.entidade.suprimentos.NfeDuplicata;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface IDuplicataDAO extends AbstractDAO<NfeDuplicata> {

	List<NfeDuplicata> buscarDuplicatasPorNota(NotaFiscal currentBean);

}
