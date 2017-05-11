package dc.servicos.dao.comercial;

import java.util.List;

import dc.entidade.comercial.Venda;
import dc.entidade.comercial.VendaDetalhe;
import dc.model.dao.AbstractDAO;

public interface IVendaDetalheDAO extends AbstractDAO<VendaDetalhe>{

	public abstract List<VendaDetalhe> detalhesPorVenda(Venda venda);

}