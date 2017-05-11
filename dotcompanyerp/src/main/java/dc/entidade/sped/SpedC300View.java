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
@Table(name = "view_sped_c300")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedC300View  implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "SERIE")
	private String serie;

	@Column(name = "SUBSERIE")
	private String subserie;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "SOMA_TOTAL_NF")
	private BigDecimal somaTotalNf;

	@Column(name = "SOMA_PIS")
	private BigDecimal somaPis;

	@Column(name = "SOMA_COFINS")
	private BigDecimal somaCofins;

	public SpedC300View() {

	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getSubserie() {
		return subserie;
	}

	public void setSubserie(String subserie) {
		this.subserie = subserie;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getSomaTotalNf() {
		return somaTotalNf;
	}

	public void setSomaTotalNf(BigDecimal somaTotalNf) {
		this.somaTotalNf = somaTotalNf;
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