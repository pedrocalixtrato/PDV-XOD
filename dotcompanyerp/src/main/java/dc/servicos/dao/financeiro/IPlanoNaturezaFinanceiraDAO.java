package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.PlanoNaturezaFinanceira;
import dc.model.dao.AbstractDAO;

public interface IPlanoNaturezaFinanceiraDAO extends AbstractDAO<PlanoNaturezaFinanceira>{

	public abstract List<NaturezaFinanceira> listCentros(PlanoNaturezaFinanceira planoNatureza);

}