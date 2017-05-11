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
@Table(name = "view_sped_c425")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedC425View implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "ID_ECF_PRODUTO")
	private Integer idEcfProduto;

	@Column(name = "DESCRICAO_UNIDADE")
	private String descricaoUnidade;

	@Column(name = "TOTALIZADOR_PARCIAL")
	private String totalizadorParcial;

	@Column(name = "DATA_VENDA")
	@Temporal(TemporalType.DATE)
	private Date dataVenda;

	@Column(name = "SOMA_QUANTIDADE")
	private BigDecimal somaQuantidade;

	@Column(name = "SOMA_ITEM")
	private BigDecimal somaItem;

	@Column(name = "SOMA_PIS")
	private BigDecimal somaPis;

	@Column(name = "SOMA_COFINS")
	private BigDecimal somaCofins;

	public SpedC425View() {

	}

	public Integer getIdEcfProduto() {
		return idEcfProduto;
	}

	public void setIdEcfProduto(Integer idEcfProduto) {
		this.idEcfProduto = idEcfProduto;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public String getTotalizadorParcial() {
		return totalizadorParcial;
	}

	public void setTotalizadorParcial(String totalizadorParcial) {
		this.totalizadorParcial = totalizadorParcial;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
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