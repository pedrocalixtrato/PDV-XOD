//package br.com.pdv.bean;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.event.ActionEvent;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.omnifaces.util.Messages;
//
//import br.com.pdv.dao.ClienteDAO;
//import br.com.pdv.dao.ItemDAO;
//import br.com.pdv.dao.PedidoDAO;
//import br.com.pdv.dao.ProdutoDAO;
//import br.com.pdv.dao.UsuarioDAO;
//import br.com.pdv.domain.Cliente;
//import br.com.pdv.domain.FormaPagamento;
//import br.com.pdv.domain.ItemPedido;
//import br.com.pdv.domain.Pedido;
//import br.com.pdv.domain.Produto;
//import br.com.pdv.domain.StatusPedido;
//import br.com.pdv.domain.Usuario;
//
//@SuppressWarnings("serial")
//@Named
//@ViewScoped
//public class PedidoBean implements Serializable {
//	
//	private Pedido pedido;
//	private List<FormaPagamento> formaPagamento;
//	private List<StatusPedido> statusPedido;
//	private List<Produto> produtos;
//	private Produto produto;
//	private List<ItemPedido> itensPedido;
//	private ItemPedido itemPedido;
//	private List<Cliente> clientes;
//	private List<Usuario> usuarios;
//	private Cliente cliente;
//	
//	private Usuario usuario;
//	
//	
//	@Inject
//	private PedidoDAO pedidoDAO;
//	
//	@Inject
//	private ProdutoDAO produtoDAO;
//	@Inject
//	private UsuarioDAO usuarioDAO;
//	@Inject
//	private ClienteDAO clienteDAO;
//	@Inject
//	private ItemDAO itemDAO;
//	
//	
//	
//		
//	public void novo(){
//		
//		try{
//			
//			pedidoDAO.salvar(pedido);
//			
//		}catch(RuntimeException e){
//			Messages.addGlobalError("Não foi possivel salvar este cadastro");
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void salvar(){
//		try{			
//			
//			pedidoDAO.salvar(pedido);		
//			
//			//pedidoDAO.salvarPedido(pedido, itensPedido);
//			
//			
//			Messages.addGlobalInfo("Venda Finalizada!");		
//			
//		}catch(RuntimeException e){
//			Messages.addGlobalError("Não foi possivel salvar este cadastro");
//			e.printStackTrace();
//		}
//	}
//	
//	@PostConstruct
//	public void init(){
//		this.pedido = new Pedido();	
//		pedido.setValorTotal(new BigDecimal("0.00"));
//		
//		this.formaPagamento = Arrays.asList(FormaPagamento.values());
//		this.produtos = produtoDAO.listar(produto);	
//		this.itensPedido = new ArrayList<>();
//		this.usuarios = usuarioDAO.listar(usuario);
//		this.clientes = clienteDAO.listar(cliente);
//		pedido.setDataCriacao(new Date());	
//		
//		
//		
//	}
//	
//	public void finalizar(){				
//			
//		
//	}
//	
//	public void adcionar(ActionEvent evento){
//		
//		
//		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
//			
//		int achou = -1;
//		for(int posicao = 0; posicao < itensPedido.size();posicao++ ){
//			if(itensPedido.get(posicao).getProduto().equals(produto)){
//				achou = posicao;
//			}
//			
//		}
//		if(achou < 0){
//		ItemPedido itemPedido = new ItemPedido();		
//		itemPedido.setValorParcial(produto.getValorUnitario());
//		itemPedido.setQuantidade(new BigDecimal(1));
//		itemPedido.setProduto(produto);
//		
//		itensPedido.add(itemPedido);
//		}else{
//			ItemPedido itemPedido  = itensPedido.get(achou);
//			itemPedido.setQuantidade(new BigDecimal(itemPedido.getQuantidade().add(new BigDecimal(1))+ ""));
//			itemPedido.setValorParcial((produto.getValorUnitario().multiply(itemPedido.getQuantidade())));
//		}
//		calcular();
//		
//	}
//	
//	public void removerItem(ActionEvent evento){
//		
//		itemPedido = (ItemPedido) evento.getComponent().getAttributes().get("itemSelecionado");
//		System.out.println("remover ativado");
//		
//		int achou = -1;
//		for(int posicao =0; posicao < itensPedido.size();posicao++){
//			if(itensPedido.get(posicao).getProduto().equals(itemPedido.getProduto())){
//				achou = posicao;
//			}
//		}
//		if (achou > -1){
//			itensPedido.remove(achou);
//		}
//		
//	}
//	
//	public void calcular(){
//		pedido.setValorTotal(new BigDecimal ("0.00"));
//		
//		for (int posicao = 0; posicao < itensPedido.size(); posicao++){
//			itemPedido = itensPedido.get(posicao);
//			pedido.setValorTotal( pedido.getValorTotal().add(itemPedido.getValorParcial()));
//		}
//	}
//	
//	public List<Produto> getListaProduto(String descricao) {
//		List<Produto> listaProduto = new ArrayList<>();
//		try {
//			listaProduto = produtoDAO.listar(produto);
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
//		return listaProduto;
//	}
//	
//	
//	
//	public Pedido getPedido() {
//		return pedido;
//	}
//	public void setPedido(Pedido pedido) {
//		this.pedido = pedido;
//	}
//	public List<FormaPagamento> getFormaPagamento() {
//		return formaPagamento;
//	}
//	public void setFormaPagamento(List<FormaPagamento> formaPagamento) {
//		this.formaPagamento = formaPagamento;
//	}
//	public List<StatusPedido> getStatusPedido() {
//		return statusPedido;
//	}
//	public void setStatusPedido(List<StatusPedido> statusPedido) {
//		this.statusPedido = statusPedido;
//	}
//
//	public List<Produto> getProdutos() {
//		return produtos;
//	}
//
//	public void setProdutos(List<Produto> produtos) {
//		this.produtos = produtos;
//	}
//
//	public Produto getProduto() {
//		return produto;
//	}
//
//	public void setProduto(Produto produto) {
//		this.produto = produto;
//	}
//
//	public List<ItemPedido> getItensPedido() {if(itensPedido==null){
//		itensPedido = new ArrayList<>();
//	}
//		return itensPedido;
//	}
//
//	public void setItensPedido(List<ItemPedido> itensPedido) {
//		this.itensPedido = itensPedido;
//	}
//
//	public ItemPedido getItemPedido() {if(itemPedido == null){
//		itemPedido = new ItemPedido();
//	}
//		return itemPedido;
//	}
//
//	public void setItemPedido(ItemPedido itemPedido) {
//		this.itemPedido = itemPedido;
//	}
//
//	public List<Cliente> getClientes() {
//		return clientes;
//	}
//
//	public void setClientes(List<Cliente> clientes) {
//		this.clientes = clientes;
//	}
//
//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	public Cliente getCliente() {
//		return cliente;
//	}
//
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
