package br.com.pdv.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.domain.Clientes;

@SuppressWarnings("serial")
@Named
@javax.faces.view.ViewScoped
public class ClienteBean implements Serializable{
	
	
	private Clientes cliente;
	@Inject
	private ClienteDAO clienteDAO;
	
	
	@PostConstruct
	public void init(){
		
		cliente = new Clientes();
		
	}
	
	public void salvar(){
		
		try{			
			clienteDAO.salvar(cliente);
						
			Messages.addGlobalInfo("Salvo com sucesso!");
		}catch(RuntimeException e){
			Messages.addGlobalError("Erro ao salvar este cadastro");
			e.printStackTrace();
		}
		
		
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}





	
	

}