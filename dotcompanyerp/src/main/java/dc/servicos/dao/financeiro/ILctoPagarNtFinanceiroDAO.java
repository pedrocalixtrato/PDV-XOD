package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.model.dao.AbstractDAO;

public interface ILctoPagarNtFinanceiroDAO extends AbstractDAO<LctoPagarNtFinanceira>{

	public abstract List<LctoPagarNtFinanceira> findByNatureza(LancamentoPagarEntity currentBean);

	public abstract List<NaturezaFinanceira> findByNaturezaFin(LancamentoPagarEntity currentBean);

}