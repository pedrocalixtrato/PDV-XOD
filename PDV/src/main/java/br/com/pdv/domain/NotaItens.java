package br.com.pdv.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NotaItens implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	private Long PRO_COD;
	private Integer NI_QTD = 1;
	private BigDecimal NI_VALORUN = BigDecimal.ZERO;
	private Produto produto;
	private String NI_CST;
	private String NI_CFOP;
	private String NI_UNID;
	private BigDecimal NI_VALOR_DESCONTO;
	private BigDecimal NI_SUBTOT;
	private BigDecimal NI_VALOR_TOTAL;
	private BigDecimal NI_ICMS_VALOR_BASE_CALCULO;
	private BigDecimal NI_ICMS_VALOR;
	private BigDecimal NI_IPI_VALOR;
	private BigDecimal NI_ICMS_ALIQUOTA;
	private BigDecimal NI_IPI_ALIQUOTA;
	private BigDecimal NI_IPI_VALOR_BASE_CALCULO;
	private Nota nota;
	
	
	
	@Id
	@GeneratedValue
	public Long getPRO_COD() {
		return PRO_COD;
	}
	public void setPRO_COD(Long pRO_COD) {
		PRO_COD = pRO_COD;
	}
	public Integer getNI_QTD() {
		return NI_QTD;
	}
	public void setNI_QTD(Integer nI_QTD) {
		NI_QTD = nI_QTD;
	}
	public BigDecimal getNI_VALORUN() {
		return NI_VALORUN;
	}
	public void setNI_VALORUN(BigDecimal nI_VALORUN) {
		NI_VALORUN = nI_VALORUN;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id", nullable = false)
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getNI_CST() {
		return NI_CST;
	}
	public void setNI_CST(String nI_CST) {
		NI_CST = nI_CST;
	}
	public String getNI_CFOP() {
		return NI_CFOP;
	}
	public void setNI_CFOP(String nI_CFOP) {
		NI_CFOP = nI_CFOP;
	}
	public String getNI_UNID() {
		return NI_UNID;
	}
	public void setNI_UNID(String nI_UNID) {
		NI_UNID = nI_UNID;
	}
	public BigDecimal getNI_VALOR_DESCONTO() {
		return NI_VALOR_DESCONTO;
	}
	public void setNI_VALOR_DESCONTO(BigDecimal nI_VALOR_DESCONTO) {
		NI_VALOR_DESCONTO = nI_VALOR_DESCONTO;
	}
	public BigDecimal getNI_SUBTOT() {
		return NI_SUBTOT;
	}
	public void setNI_SUBTOT(BigDecimal nI_SUBTOT) {
		NI_SUBTOT = nI_SUBTOT;
	}
	public BigDecimal getNI_VALOR_TOTAL() {
		return NI_VALOR_TOTAL;
	}
	public void setNI_VALOR_TOTAL(BigDecimal nI_VALOR_TOTAL) {
		NI_VALOR_TOTAL = nI_VALOR_TOTAL;
	}
	public BigDecimal getNI_ICMS_VALOR_BASE_CALCULO() {
		return NI_ICMS_VALOR_BASE_CALCULO;
	}
	public void setNI_ICMS_VALOR_BASE_CALCULO(BigDecimal nI_ICMS_VALOR_BASE_CALCULO) {
		NI_ICMS_VALOR_BASE_CALCULO = nI_ICMS_VALOR_BASE_CALCULO;
	}
	public BigDecimal getNI_ICMS_VALOR() {
		return NI_ICMS_VALOR;
	}
	public void setNI_ICMS_VALOR(BigDecimal nI_ICMS_VALOR) {
		NI_ICMS_VALOR = nI_ICMS_VALOR;
	}
	public BigDecimal getNI_IPI_VALOR() {
		return NI_IPI_VALOR;
	}
	public void setNI_IPI_VALOR(BigDecimal nI_IPI_VALOR) {
		NI_IPI_VALOR = nI_IPI_VALOR;
	}
	public BigDecimal getNI_ICMS_ALIQUOTA() {
		return NI_ICMS_ALIQUOTA;
	}
	public void setNI_ICMS_ALIQUOTA(BigDecimal nI_ICMS_ALIQUOTA) {
		NI_ICMS_ALIQUOTA = nI_ICMS_ALIQUOTA;
	}
	public BigDecimal getNI_IPI_ALIQUOTA() {
		return NI_IPI_ALIQUOTA;
	}
	public void setNI_IPI_ALIQUOTA(BigDecimal nI_IPI_ALIQUOTA) {
		NI_IPI_ALIQUOTA = nI_IPI_ALIQUOTA;
	}
	public BigDecimal getNI_IPI_VALOR_BASE_CALCULO() {
		return NI_IPI_VALOR_BASE_CALCULO;
	}
	public void setNI_IPI_VALOR_BASE_CALCULO(BigDecimal nI_IPI_VALOR_BASE_CALCULO) {
		NI_IPI_VALOR_BASE_CALCULO = nI_IPI_VALOR_BASE_CALCULO;
	}
	@ManyToOne
	@JoinColumn(name = "nota_id", nullable = false)	
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PRO_COD == null) ? 0 : PRO_COD.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaItens other = (NotaItens) obj;
		if (PRO_COD == null) {
			if (other.PRO_COD != null)
				return false;
		} else if (!PRO_COD.equals(other.PRO_COD))
			return false;
		return true;
	}
	
	
	
	
}
