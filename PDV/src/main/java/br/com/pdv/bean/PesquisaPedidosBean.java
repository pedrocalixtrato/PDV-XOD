package br.com.pdv.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.pdv.Enum.StatusPedido;
import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.domain.Pedido;
import br.com.pdv.filter.PedidoFilter;
@Named
@javax.faces.view.ViewScoped
public class PesquisaPedidosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoDAO pedidoDAO;
	
	private Pedido pedido;
	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltrados;
	
	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		
		pedidosFiltrados = pedidoDAO.filtrados(filtro);
		//pedidosFiltrados = pedidoDAO.listar(pedido);
		System.out.println("esta imprimindo" + pedidosFiltrados);
		
		
	}
	
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	

}
