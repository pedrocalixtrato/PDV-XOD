package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.FluxoCaixaDetalheEntity;
import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.model.dao.AbstractDAO;

public interface INaturezaFinanceiraDAO extends AbstractDAO<NaturezaFinanceira>{

	public abstract List<NaturezaFinanceira> findByNatureza(LancamentoPagarEntity currentBean);

	public abstract List<NaturezaFinanceira> findByNaturezaReceber(LancamentoReceber currentBean);

	public abstract List<FluxoCaixaDetalheEntity> findByNatureza(FluxoCaixaEntity currentBean);

}