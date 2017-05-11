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
@Table(name = "view_sped_c190")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedC190View  implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "CST_ICMS")
	private String cstIcms;

	@Column(name = "CFOP")
	private Integer cfop;

	@Column(name = "ALIQUOTA_ICMS")
	private BigDecimal aliquotaIcms;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "SOMA_VALOR_OPERACAO")
	private BigDecimal somaValorOperacao;

	@Column(name = "SOMA_BASE_CALCULO_ICMS")
	private BigDecimal somaBaseCalculoIcms;

	@Column(name = "SOMA_VALOR_ICMS")
	private BigDecimal somaValorIcms;

	@Column(name = "SOMA_BASE_CALCULO_ICMS_ST")
	private BigDecimal somaBaseCalculoIcmsSt;

	@Column(name = "SOMA_VALOR_ICMS_ST")
	private BigDecimal somaValorIcmsSt;

	@Column(name = "SOMA_VL_RED_BC")
	private BigDecimal somaVlRedBc;

	@Column(name = "SOMA_VALOR_IPI")
	private BigDecimal somaValorIpi;

	public SpedC190View() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCstIcms() {
		return cstIcms;
	}

	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getSomaValorOperacao() {
		return somaValorOperacao;
	}

	public void setSomaValorOperacao(BigDecimal somaValorOperacao) {
		this.somaValorOperacao = somaValorOperacao;
	}

	public BigDecimal getSomaBaseCalculoIcms() {
		return somaBaseCalculoIcms;
	}

	public void setSomaBaseCalculoIcms(BigDecimal somaBaseCalculoIcms) {
		this.somaBaseCalculoIcms = somaBaseCalculoIcms;
	}

	public BigDecimal getSomaValorIcms() {
		return somaValorIcms;
	}

	public void setSomaValorIcms(BigDecimal somaValorIcms) {
		this.somaValorIcms = somaValorIcms;
	}

	public BigDecimal getSomaBaseCalculoIcmsSt() {
		return somaBaseCalculoIcmsSt;
	}

	public void setSomaBaseCalculoIcmsSt(BigDecimal somaBaseCalculoIcmsSt) {
		this.somaBaseCalculoIcmsSt = somaBaseCalculoIcmsSt;
	}

	public BigDecimal getSomaValorIcmsSt() {
		return somaValorIcmsSt;
	}

	public void setSomaValorIcmsSt(BigDecimal somaValorIcmsSt) {
		this.somaValorIcmsSt = somaValorIcmsSt;
	}

	public BigDecimal getSomaVlRedBc() {
		return somaVlRedBc;
	}

	public void setSomaVlRedBc(BigDecimal somaVlRedBc) {
		this.somaVlRedBc = somaVlRedBc;
	}

	public BigDecimal getSomaValorIpi() {
		return somaValorIpi;
	}

	public void setSomaValorIpi(BigDecimal somaValorIpi) {
		this.somaValorIpi = somaValorIpi;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}