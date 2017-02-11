package br.com.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.domain.Cliente;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PesquisaClienteBean implements Serializable{

	private List<Cliente> clientes;
	private Cliente cliente;
	
	
	@Inject
	ClienteDAO ClienteDAO;

	
	//@PostConstruct
	public void init() {
		
		clientes = ClienteDAO.listar(cliente);
		
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
	
	
	
