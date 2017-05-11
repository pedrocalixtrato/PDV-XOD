package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoPagarDAO extends AbstractDAO<LancamentoPagarEntity> {

	public abstract List<LancamentoPagarEntity> buscar(FornecedorEntity fornecedor);

	public abstract List<LancamentoPagarEntity> procuraNomeContendo(String query);

}