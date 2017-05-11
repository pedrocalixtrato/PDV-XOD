package dc.model.business.financeiro;

import java.util.List;

import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface LctoPagarNtFinanceiraBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {

	public List<LctoPagarNtFinanceira> findNatureza() throws Exception;

}
