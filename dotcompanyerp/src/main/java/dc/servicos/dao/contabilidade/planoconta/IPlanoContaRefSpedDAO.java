package dc.servicos.dao.contabilidade.planoconta;

import java.util.List;

import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.model.dao.AbstractDAO;

public interface IPlanoContaRefSpedDAO extends AbstractDAO<PlanoContaRefSped>{

	public abstract List<PlanoContaRefSped> procuraNomeContendo(String query);

}