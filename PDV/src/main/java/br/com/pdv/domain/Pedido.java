//package br.com.pdv.domain;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Transient;
//
//@SuppressWarnings("serial")
//@Entity
//public class Pedido implements Serializable{
//	
//	private Long id;
//	private Date dataCriacao;
//	private String observacao;
//	private Date dataEntrega;
//	private BigDecimal valorFrete = BigDecimal.ZERO;
//	private BigDecimal valorDesconto = BigDecimal.ZERO;
//	private BigDecimal valorTotal = BigDecimal.ZERO;
//	private StatusPedido status = StatusPedido.ORCAMENTO;
//	private FormaPagamento formaPagamento;	
//	private Cliente cliente;	
//	private List<ItemPedido> itens = new ArrayList<>();
//	private Usuario usuario;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	@Temporal(TemporalType.TIMESTAMP)
//	public Date getDataCriacao() {
//		return dataCriacao;
//	}
//	public void setDataCriacao(Date dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
//	public String getObservacao() {
//		return observacao;
//	}
//	public void setObservacao(String observacao) {
//		this.observacao = observacao;
//	}
//	@Temporal(TemporalType.TIMESTAMP)
//	public Date getDataEntrega() {
//		return dataEntrega;
//	}
//	public void setDataEntrega(Date dataEntrega) {
//		this.dataEntrega = dataEntrega;
//	}
//	public BigDecimal getValorFrete() {
//		return valorFrete;
//	}
//	public void setValorFrete(BigDecimal valorFrete) {
//		this.valorFrete = valorFrete;
//	}
//	public BigDecimal getValorDesconto() {
//		return valorDesconto;
//	}
//	public void setValorDesconto(BigDecimal valorDesconto) {
//		this.valorDesconto = valorDesconto;
//	}
//	public BigDecimal getValorTotal() {
//		return valorTotal;
//	}
//	public void setValorTotal(BigDecimal valorTotal) {
//		this.valorTotal = valorTotal;
//	}
//	@Enumerated(EnumType.STRING)
//	public StatusPedido getStatus() {
//		return status;
//	}
//	public void setStatus(StatusPedido status) {
//		this.status = status;
//	}
//	@Enumerated(EnumType.STRING)
//	public FormaPagamento getFormaPagamento() {
//		return formaPagamento;
//	}
//	public void setFormaPagamento(FormaPagamento formaPagamento) {
//		this.formaPagamento = formaPagamento;
//	}
//	@ManyToOne(fetch=FetchType.EAGER)
//	public Cliente getCliente() {
//		return cliente;
//	}
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
//	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	public List<ItemPedido> getItens() {
//		return itens;
//	}
//	public void setItens(List<ItemPedido> itens) {
//		this.itens = itens;
//	}
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Pedido other = (Pedido) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//	
//	
//	@Transient
//	public boolean isNovo() {
//		return getId() == null;
//	}
//	
//	@Transient
//	public boolean isExistente() {
//		return !isNovo();
//	}
//
//}
