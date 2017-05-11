package dc.entidade.sped;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

/**
 * 
 * 
 */

@Entity
@Table(name = "view_sped_c370")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedC370View implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "ID_NF_CABECALHO")
	private Integer idNfCabecalho;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "ID_PRODUTO")
	private Integer idProduto;

	@Column(name = "ITEM")
	private Integer item;

	@Basic(optional = false)
	@Column(name = "ID_UNIDADE_PRODUTO")
	private Integer idUnidadeProduto;

	@Column(name = "QUANTIDADE")
	private BigDecimal quantidade;

	@Column(name = "VALOR_TOTAL")
	private BigDecimal valorTotal;

	@Column(name = "CST")
	private String cst;

	@Column(name = "DESCONTO")
	private BigDecimal desconto;

	public SpedC370View() {

	}

	public Integer getIdNfCabecalho() {
		return idNfCabecalho;
	}

	public void setIdNfCabecalho(Integer idNfCabecalho) {
		this.idNfCabecalho = idNfCabecalho;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Integer getIdUnidadeProduto() {
		return idUnidadeProduto;
	}

	public void setIdUnidadeProduto(Integer idUnidadeProduto) {
		this.idUnidadeProduto = idUnidadeProduto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}