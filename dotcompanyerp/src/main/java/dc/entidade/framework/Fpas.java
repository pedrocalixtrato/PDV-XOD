package dc.entidade.framework;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;

@Entity
@Table(name = "fpas")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Fpas extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fpas_id_seq")
	@SequenceGenerator(name = "fpas_id_seq", sequenceName = "fpas_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "CODIGO")
	private Integer codigo;

	@Field
	@Caption("Cnae")
	@Column(name = "CNAE")
	private String cnae;

	@Column(name = "ALIQUOTA_SAT")
	private BigDecimal aliquotaSat;

	@Field
	@Caption("Descricao")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "PERCENTUAL_INSS_PATRONAL")
	private BigDecimal percentualInssPatronal;

	@Column(name = "CODIGO_TERCEIRO")
	private String codigoTerceiro;

	@Column(name = "PERCENTUAL_TERCEIROS")
	private BigDecimal percentualTerceiros;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getCnae();
	}

	/**
	 * CONSTRUTOR
	 */

	public Fpas() {

	}

	public Fpas(Integer id) {
		this.id = id;
	}

	public Fpas(Integer id, String cnae) {
		this.id = id;
		this.cnae = cnae;
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public BigDecimal getAliquotaSat() {
		return aliquotaSat;
	}

	public void setAliquotaSat(BigDecimal aliquotaSat) {
		this.aliquotaSat = aliquotaSat;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPercentualInssPatronal() {
		return percentualInssPatronal;
	}

	public void setPercentualInssPatronal(BigDecimal percentualInssPatronal) {
		this.percentualInssPatronal = percentualInssPatronal;
	}

	public String getCodigoTerceiro() {
		return codigoTerceiro;
	}

	public void setCodigoTerceiro(String codigoTerceiro) {
		this.codigoTerceiro = codigoTerceiro;
	}

	public BigDecimal getPercentualTerceiros() {
		return percentualTerceiros;
	}

	public void setPercentualTerceiros(BigDecimal percentualTerceiros) {
		this.percentualTerceiros = percentualTerceiros;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}