package br.com.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.pdv.Enum.FormaPagamento;
import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.dao.PedidoDAO;
import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Clientes;
import br.com.pdv.domain.EnderecoEntrega;
import br.com.pdv.domain.Pedido;
import br.com.pdv.domain.Usuario;
import br.com.pdv.service.CadastroPedidoService;
import br.com.pdv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	private Usuario usuario;
	
	@Inject
	private PedidoDAO pedidoDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject	
	private CadastroPedidoService cadastroPedidoService;
	
	
		
	
	public CadastroPedidoBean(){
		limpar();
	}
	
	@PostConstruct
	public void init(){
				
		if(FacesUtil.isNotPostback()){
		this.vendedores = usuarioDAO.vendedores();
		
		this.recalcularPedido();
		}
	}
	
	public List<Clientes> completarCliente(String nome){
		
		return this.clienteDAO.porNome(nome);
	}
	
	
	public void limpar(){
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());;
	}
	
	public void salvar(){	
		try{
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		pedido.getId();
		System.out.println("este é o codigo" + pedido.getId());
		FacesUtil.addSuccessMessage("Pedido salvo com sucesso.");
		}catch(RuntimeException e){
			e.printStackTrace();
			Messages.addGlobalError("Nao foi possivel salvar este cadastro.");
		}
	}
	
	public void recalcularPedido(){
		if(this.pedido!= null){			
		this.pedido.recalcularValorTotal();
		}		
	}
	
	
	public boolean isEditando(){
		return this.pedido.getId()!= null;
	}
	
	
	public FormaPagamento[] getFormaPagamento(){
		return FormaPagamento.values();
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