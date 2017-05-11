package dc.servicos.dao.suprimentos.contrato;

import dc.entidade.suprimentos.contrato.ContratoEntity;
import dc.entidade.suprimentos.contrato.PrevFaturamentoEntity;
import dc.model.dao.AbstractDAO;

public interface IContratoDAO extends AbstractDAO<ContratoEntity>{

	public abstract void delete(PrevFaturamentoEntity contratoPrevFaturamento);

}