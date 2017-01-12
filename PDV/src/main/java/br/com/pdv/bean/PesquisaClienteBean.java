package br.com.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.domain.Clientes;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PesquisaClienteBean implements Serializable{

	private List<Clientes> clientes;
	private Clientes cliente;
	
	
	@Inject
	ClienteDAO ClienteDAO;

	
	//@PostConstruct
	public void init() {
		
		clientes = ClienteDAO.listar(cliente);
		
	}


	public List<Clientes> getClientes() {
		return clientes;
	}


	public void setClientes(List<Clientes> clientes) {
		this.clientes = clientes;
	}


	public Clientes getCliente() {
		return cliente;
	}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	
	
	
}
	
	
	
