package br.com.pdv.bean;

import br.com.pdv.domain.Pedido;

public class PedidoAlteradoEvent {
	
	private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido){
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
		
	
}
