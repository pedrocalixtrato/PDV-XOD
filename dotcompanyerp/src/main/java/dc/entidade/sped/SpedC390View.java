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
 */

@Entity
@Table(name = "view_sped_c390")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedC390View implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "CST")
	private String cst;

	@Basic(optional = false)
	@Column(name = "CFOP")
	private Integer cfop;

	@Column(name = "TAXA_ICMS")
	private BigDecimal taxaIcms;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "SOMA_ITEM")
	private BigDecimal somaItem;

	@Column(name = "SOMA_BASE_ICMS")
	private BigDecimal somaBaseIcms;

	@Column(name = "SOMA_ICMS")
	private BigDecimal somaIcms;

	@Column(name = "SOMA_ICMS_OUTRAS")
	private BigDecimal somaIcmsOutras;

	public SpedC390View() {

	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public BigDecimal getTaxaIcms() {
		return taxaIcms;
	}

	public void setTaxaIcms(BigDecimal taxaIcms) {
		this.taxaIcms = taxaIcms;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getSomaItem() {
		return somaItem;
	}

	public void setSomaItem(BigDecimal somaItem) {
		this.somaItem = somaItem;
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

	public BigDecimal getSomaIcmsOutras() {
		return somaIcmsOutras;
	}

	public void setSomaIcmsOutras(BigDecimal somaIcmsOutras) {
		this.somaIcmsOutras = somaIcmsOutras;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}