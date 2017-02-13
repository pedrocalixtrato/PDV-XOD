package br.com.pdv.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.domain.ItemPedido;
import br.com.pdv.domain.Pedido;
import br.com.pdv.util.jpa.Transactional;
@Stateless
public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;


	@EJB
	private PedidoDAO pedidoDAO;
	
	
	
	
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidoDAO.buscarPeloCodigo(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {			
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}
	
	

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidoDAO.buscarPeloCodigo(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
		
	}
	
	
}
