package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LancamentoOrcadoEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoOrcadoDAO extends AbstractDAO<LancamentoOrcadoEntity> {

	List<LancamentoOrcadoEntity> procuraNomeContendo(String valor);

}