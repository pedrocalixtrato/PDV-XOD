package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LancamentoProgramadoDetEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoProgramadoDetDAO extends AbstractDAO<LancamentoProgramadoDetEntity>{

	public abstract List<LancamentoProgramadoDetEntity> procuraNomeContendo(String query);

}