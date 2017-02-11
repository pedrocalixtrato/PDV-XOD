package br.com.pdv.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.domain.Cliente;
import br.com.pdv.util.jsf.FacesUtil;

@SuppressWarnings("serial")
@Named
@javax.faces.view.ViewScoped
public class ClienteBean implements Serializable{
	
	
	private Cliente cliente;
	@Inject
	private ClienteDAO clienteDAO;
	
	
	public ClienteBean(){
		cliente = new Cliente();
	}
	
	public void init(){
		
		
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}





	
	

}