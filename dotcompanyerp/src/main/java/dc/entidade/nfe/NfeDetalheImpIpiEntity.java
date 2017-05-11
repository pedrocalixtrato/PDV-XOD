package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_detalhe_imposto_ipi")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpIpiEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_ipi_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_ipi_id_seq", sequenceName = "nfe_detalhe_imposto_ipi_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "enquadramento_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String enquadramentoIpi = "";

	@Field
	@Column(name = "cnpj_produtor")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpjProdutorIpi = "";

	@Field
	@Column(name = "codigo_selo_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoSeloIpi = "";

	@Field
	@Column(name = "quantidade_selo_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeSeloIpi = new Integer(0);

	@Field
	@Column(name = "enquadramento_legal_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String enquadramentoLegalIpi = "";

	@Field
	@Column(name = "cst_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstIpi = "";

	@Field
	@Column(name = "valor_base_calculo_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBaseCalculoIpi = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIpi = new BigDecimal(0);

	@Field
	@Column(name = "quantidade_unidade_tributavel")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeUnidadeTributavel = new BigDecimal(0);

	@Field
	@Column(name = "valor_unidade_tributavel")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnidadeTributavel = new BigDecimal(0);

	@Field
	@Column(name = "valor_ipi")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIpi = new BigDecimal(0);

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne
	@JoinColumn(name = "id_nfe_detalhe")
	private NfeDetalheEntity nfeDetalhe;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDetalheImpIpiEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnquadramentoIpi() {
		return enquadramentoIpi;
	}

	public void setEnquadramentoIpi(String enquadramentoIpi) {
		this.enquadramentoIpi = (enquadramentoIpi == null ? ""
				: enquadramentoIpi.toUpperCase().trim());
	}

	public String getCnpjProdutorIpi() {
		return cnpjProdutorIpi;
	}

	public void setCnpjProdutorIpi(String cnpjProdutorIpi) {
		this.cnpjProdutorIpi = (cnpjProdutorIpi == null ? "" : cnpjProdutorIpi
				.toUpperCase().trim());
	}

	public String getCodigoSeloIpi() {
		return codigoSeloIpi;
	}

	public void setCodigoSeloIpi(String codigoSeloIpi) {
		this.codigoSeloIpi = (codigoSeloIpi == null ? "" : codigoSeloIpi
				.toUpperCase().trim());
	}

	public Integer getQuantidadeSeloIpi() {
		return quantidadeSeloIpi;
	}

	public void setQuantidadeSeloIpi(Integer quantidadeSeloIpi) {
		this.quantidadeSeloIpi = (quantidadeSeloIpi == null ? new Integer(0)
				: quantidadeSeloIpi);
	}

	public String getEnquadramentoLegalIpi() {
		return enquadramentoLegalIpi;
	}

	public void setEnquadramentoLegalIpi(String enquadramentoLegalIpi) {
		this.enquadramentoLegalIpi = (enquadramentoLegalIpi == null ? ""
				: enquadramentoLegalIpi.toUpperCase().trim());
	}

	public String getCstIpi() {
		return cstIpi;
	}

	public void setCstIpi(String cstIpi) {
		this.cstIpi = (cstIpi == null ? "" : cstIpi.toUpperCase().trim());
	}

	public BigDecimal getValorBaseCalculoIpi() {
		return valorBaseCalculoIpi;
	}

	public void setValorBaseCalculoIpi(BigDecimal valorBaseCalculoIpi) {
		this.valorBaseCalculoIpi = (valorBaseCalculoIpi == null ? new BigDecimal(
				0) : valorBaseCalculoIpi);
	}

	public BigDecimal getAliquotaIpi() {
		return aliquotaIpi;
	}

	public void setAliquotaIpi(BigDecimal aliquotaIpi) {
		this.aliquotaIpi = (aliquotaIpi == null ? new BigDecimal(0)
				: aliquotaIpi);
	}

	public BigDecimal getQuantidadeUnidadeTributavel() {
		return quantidadeUnidadeTributavel;
	}

	public void setQuantidadeUnidadeTributavel(
			BigDecimal quantidadeUnidadeTributavel) {
		this.quantidadeUnidadeTributavel = (quantidadeUnidadeTributavel == null ? new BigDecimal(
				0) : quantidadeUnidadeTributavel);
	}

	public BigDecimal getValorUnidadeTributavel() {
		return valorUnidadeTributavel;
	}

	public void setValorUnidadeTributavel(BigDecimal valorUnidadeTributavel) {
		this.valorUnidadeTributavel = (valorUnidadeTributavel == null ? new BigDecimal(
				0) : valorUnidadeTributavel);
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = (valorIpi == null ? new BigDecimal(0) : valorIpi);
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}