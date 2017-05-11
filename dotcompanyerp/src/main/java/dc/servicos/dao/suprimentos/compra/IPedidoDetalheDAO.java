package dc.servicos.dao.suprimentos.compra;

import java.util.List;

import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.model.dao.AbstractDAO;

public interface IPedidoDetalheDAO extends AbstractDAO<PedidoDetalheEntity> {

	public abstract List<PedidoDetalheEntity> findByPedido(PedidoEntity currentBean);

}
