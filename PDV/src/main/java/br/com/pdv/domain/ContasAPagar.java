//package br.com.pdv.domain;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//
//@SuppressWarnings("serial")
//@Entity
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
//public class ContasAPagar implements Serializable {
//	
//	private Long codigo;
//	private StatusConta tipo;
//	private Date data;
//	private BigDecimal valor;
//	private String fornecedor;
//	private String cheque;
//	
//	
//	@Id	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getCodigo() {
//		return codigo;
//	}
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
//	
//	
//	@Enumerated(EnumType.STRING)
//	public StatusConta getTipo() {
//		return tipo;
//	}
//	public void setTipo(StatusConta tipo) {
//		this.tipo = tipo;
//	}
//	@Temporal(TemporalType.DATE)
//	public Date getData() {
//		return data;
//	}
//	public void setData(Date data) {
//		this.data = data;
//	}
//	public BigDecimal getValor() {
//		return valor;
//	}
//	public void setValor(BigDecimal valor) {
//		this.valor = valor;
//	}
//	public String getFornecedor() {
//		return fornecedor;
//	}
//	public void setFornecedor(String fornecedor) {
//		this.fornecedor = fornecedor;
//	}
//	public String getCheque() {
//		return cheque;
//	}
//	public void setCheque(String cheque) {
//		this.cheque = cheque;
//	}
//	
//	
//	
//	
//
//}
