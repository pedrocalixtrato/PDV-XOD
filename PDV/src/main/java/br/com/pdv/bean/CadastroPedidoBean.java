package br.com.pdv.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Clientes;
import br.com.pdv.domain.EnderecoEntrega;
import br.com.pdv.domain.Pedido;
import br.com.pdv.domain.Usuario;

@ManagedBean
@RequestScoped
public class CadastroPedidoBean {

	private Pedido pedido;
	private List<Usuario> vendedores;
	private Usuario usuario;
	
	
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private ClienteDAO clienteDAO;
	
	
		
	
	public CadastroPedidoBean(){
		limpar();
	}
	
	
	public void init(){
		
		this.vendedores = usuarioDAO.listar(usuario);
		
	}
	
	public List<Clientes> completarCliente(Clientes cliente){
		
		return this.clienteDAO.filtrar(cliente, "CLI_RAZAO");
	}
	
	
	public void limpar(){
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());;
	}
	
	public void salvar(){	
		
		
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public List<Usuario> getVendedores() {
		return vendedores;
	}


	public void setVendedores(List<Usuario> vendedores) {
		this.vendedores = vendedores;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	


	
	 
	
	
}