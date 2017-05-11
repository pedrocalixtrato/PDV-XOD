package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.ParcelaPagar;
import dc.model.dao.AbstractDAO;

public interface IParcelaPagarDAO extends AbstractDAO<ParcelaPagar> {

	public abstract List<ParcelaPagar> buscaPorParcelaPagar(ParcelaPagar parcelaPagar);

}