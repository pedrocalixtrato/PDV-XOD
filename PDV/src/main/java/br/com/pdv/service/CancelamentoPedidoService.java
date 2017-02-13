package br.com.pdv.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pdv.Enum.StatusPedido;
import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.domain.Pedido;
import br.com.pdv.util.jpa.Transactional;
@Stateless
public class CancelamentoPedidoService implements Serializable {

	 	 	 
	private static final long serialVersionUID = 1L;
	@EJB
	private PedidoDAO pedidoDAO;
	
	@Inject
	private EstoqueService estoqueService;
	
	
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidoDAO.buscarPeloCodigo(pedido.getId());
		
		if(pedido.isNaoCancelavel()){
			throw new NegocioException("Pedido nao pode ser cancelado no status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		if(pedido.isEmitido()){
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		
		pedido = this.pedidoDAO.guardar(pedido);
		
		return pedido;
	}

}
