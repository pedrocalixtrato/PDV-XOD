package dc.servicos.dao.suprimentos.compra;

import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.model.dao.AbstractDAO;

public interface IPedidoCompraDAO extends AbstractDAO<PedidoEntity> {

	boolean existsPedidoDetalheByCotacao(Integer id);

	void save(PedidoEntity pedidoCompra);

}
