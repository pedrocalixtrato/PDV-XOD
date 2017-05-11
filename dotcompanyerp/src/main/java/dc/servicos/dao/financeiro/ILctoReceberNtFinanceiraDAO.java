package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.LctoReceberNtFinanceiraEntity;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.model.dao.AbstractDAO;

public interface ILctoReceberNtFinanceiraDAO extends AbstractDAO<LctoReceberNtFinanceiraEntity> {

	public abstract List<LctoReceberNtFinanceiraEntity> findByNatureza(LancamentoReceber currentBean);

	public abstract List<NaturezaFinanceira> findByNaturezaFin(LancamentoReceber currentBean);

	public abstract List<LctoReceberNtFinanceiraEntity> findByNaturezaReceber(LancamentoReceber currentBean);

}