package dc.model.business.geral.produto;

import java.util.List;

import dc.entidade.financeiro.NaturezaFinanceira;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface NaturezaFinanceiraBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {

	public List<NaturezaFinanceira> findNatureza() throws Exception;

}
