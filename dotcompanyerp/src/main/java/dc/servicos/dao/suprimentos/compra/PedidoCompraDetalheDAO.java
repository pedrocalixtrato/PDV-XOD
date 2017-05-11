package dc.servicos.dao.suprimentos.compra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraPedidoDetalheDAO")
public class PedidoCompraDetalheDAO extends AbstractCrudDAO<PedidoDetalheEntity> implements IPedidoDetalheDAO{

	@Override
	public Class<PedidoDetalheEntity> getEntityClass() {
		return PedidoDetalheEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"pedido","produto" };
	}

	@Override
	public List<PedidoDetalheEntity> findByPedido(PedidoEntity currentBean) {
		List<PedidoDetalheEntity> lista = new ArrayList<>();

		try{
			if(currentBean!=null){
				lista =  getSession()
						.createQuery("from PedidoDetalhe i where i.pedido = :lancamentoPagar")
						.setParameter("pedido", currentBean).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}


}