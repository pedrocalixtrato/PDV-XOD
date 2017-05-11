package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.ParcelaPagar;
import dc.model.dao.AbstractDAO;

public interface IParcelaPagamentoDAO extends AbstractDAO<ParcelaPagamento>{

	public abstract List<ParcelaPagamento> buscaPorParcelaPagar(ParcelaPagar parcelaPagar);

}