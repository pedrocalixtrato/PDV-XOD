package dc.servicos.dao.suprimentos.estoque;

import java.util.List;

import dc.entidade.suprimentos.CupomFiscalReferenciadoEntity;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.model.dao.AbstractDAO;

public interface ICupomVinculadoDAO extends AbstractDAO<CupomFiscalReferenciadoEntity> {

	List<CupomFiscalReferenciadoEntity> buscaCuponsPorNota(
			NotaFiscal currentBean);

}
