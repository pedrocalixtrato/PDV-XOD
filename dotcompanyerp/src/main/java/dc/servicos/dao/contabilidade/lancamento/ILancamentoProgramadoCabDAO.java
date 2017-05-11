package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoProgramadoCabDAO extends AbstractDAO<LancamentoProgramadoCabEntity>{

	List<LancamentoProgramadoCabEntity> procuraNomeContendo(String valor);

}