package br.com.pdv.service;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pdv.Enum.StatusPedido;
import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.domain.Pedido;
import br.com.pdv.util.jpa.Transactional;
@Stateless
public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoDAO pedidoDAO;	
	
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		 
		pedido.recalcularValorTotal();
		
		if(pedido.isNaoAlteravel()){
			throw new NegocioException("Pedido nao pode ser alterado no status" + pedido.getStatus().getDescricao());
		}
		
		if (pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if (pedido.isValorTotalNegativo()) {
			throw new NegocioException("Valor total do pedido não pode ser negativo.");
		}
		
		pedido = this.pedidoDAO.guardar(pedido);
		return pedido;
	}

}
