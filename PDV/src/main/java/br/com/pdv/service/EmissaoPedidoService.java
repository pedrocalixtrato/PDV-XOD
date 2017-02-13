package br.com.pdv.service;

import java.io.Serializable;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.pdv.Enum.StatusPedido;
import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.domain.Pedido;
@Stateless
public class EmissaoPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@EJB
	private PedidoDAO pedidoDAO;	
	
	
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if (pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(pedido);		
		
		
		pedido.setStatus(StatusPedido.EMITIDO);
		pedido = this.pedidoDAO.guardar(pedido);
		
		return pedido;
	}
}
