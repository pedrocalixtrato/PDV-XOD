package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.Adiantamento;
import dc.model.dao.AbstractDAO;

public interface IAdiantamentoDAO extends AbstractDAO<Adiantamento>{

	public abstract List<Adiantamento> procuraNomeContendo(String query);

}