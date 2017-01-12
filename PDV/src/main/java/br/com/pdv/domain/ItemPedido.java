//package br.com.pdv.domain;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Transient;
//
//@SuppressWarnings("serial")
//@Entity
//public class ItemPedido implements Serializable {
//
//	private Long codigo;
//	private BigDecimal quantidade ;
//	private BigDecimal valorParcial;
//	private Produto produto;
//	private Pedido pedido;
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	public Long getCodigo() {
//		return codigo;
//	}
//
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
//
//	
//
//	
//	
//	public BigDecimal getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(BigDecimal quantidade) {
//		this.quantidade = quantidade;
//	}
//
//	public BigDecimal getValorParcial() {
//		return valorParcial;
//	}
//
//	public void setValorParcial(BigDecimal valorParcial) {
//		this.valorParcial = valorParcial;
//	}
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	public Produto getProduto() {
//		return produto;
//	}
//
//	public void setProduto(Produto produto) {
//		this.produto = produto;
//	}
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	public Pedido getPedido() {
//		return pedido;
//	}
//
//	public void setPedido(Pedido pedido) {
//		this.pedido = pedido;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ItemPedido other = (ItemPedido) obj;
//		if (codigo == null) {
//			if (other.codigo != null)
//				return false;
//		} else if (!codigo.equals(other.codigo))
//			return false;
//		return true;
//	}
//		
//
//}
