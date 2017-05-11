package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LancamentoDetalheEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoDetalheDAO extends AbstractDAO<LancamentoDetalheEntity> {

	List<LancamentoDetalheEntity> procuraNomeContendo(String valor);

}