package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.FechamentoEntity;
import dc.model.dao.AbstractDAO;

public interface IFechamentoDAO extends AbstractDAO<FechamentoEntity> {

	List<FechamentoEntity> procuraNomeContendo(String valor);

}