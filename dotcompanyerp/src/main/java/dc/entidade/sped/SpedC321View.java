package dc.entidade.sped;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "view_sped_c321")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedC321View implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "ID_PRODUTO")
	private Integer idProduto;

	@Column(name = "DESCRICAO_UNIDADE")
	private String descricaoUnidade;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "SOMA_QUANTIDADE")
	private BigDecimal somaQuantidade;

	@Column(name = "SOMA_ITEM")
	private BigDecimal somaItem;

	@Column(name = "SOMA_DESCONTO")
	private BigDecimal somaDesconto;

	@Column(name = "SOMA_BASE_ICMS")
	private BigDecimal somaBaseIcms;

	@Column(name = "SOMA_ICMS")
	private BigDecimal somaIcms;

	@Column(name = "SOMA_PIS")
	private BigDecimal somaPis;

	@Column(name = "SOMA_COFINS")
	private BigDecimal somaCofins;

	public SpedC321View() {

	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getSomaQuantidade() {
		return somaQuantidade;
	}

	public void setSomaQuantidade(BigDecimal somaQuantidade) {
		this.somaQuantidade = somaQuantidade;
	}

	public BigDecimal getSomaItem() {
		return somaItem;
	}

	public void setSomaItem(BigDecimal somaItem) {
		this.somaItem = somaItem;
	}

	public BigDecimal getSomaDesconto() {
		return somaDesconto;
	}

	public void setSomaDesconto(BigDecimal somaDesconto) {
		this.somaDesconto = somaDesconto;
	}

	public BigDecimal getSomaBaseIcms() {
		return somaBaseIcms;
	}

	public void setSomaBaseIcms(BigDecimal somaBaseIcms) {
		this.somaBaseIcms = somaBaseIcms;
	}

	public BigDecimal getSomaIcms() {
		return somaIcms;
	}

	public void setSomaIcms(BigDecimal somaIcms) {
		this.somaIcms = somaIcms;
	}

	public BigDecimal getSomaPis() {
		return somaPis;
	}

	public void setSomaPis(BigDecimal somaPis) {
		this.somaPis = somaPis;
	}

	public BigDecimal getSomaCofins() {
		return somaCofins;
	}

	public void setSomaCofins(BigDecimal somaCofins) {
		this.somaCofins = somaCofins;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}