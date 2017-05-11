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
@Table(name = "nfe_det_especifico_combustivel")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetEspecificoCombustivelEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_det_especifico_combustivel_id_seq")
	@SequenceGenerator(name = "nfe_det_especifico_combustivel_id_seq", sequenceName = "nfe_det_especifico_combustivel_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "codigo_anp")
	@Caption(value = "Código ANP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoAnp = new Integer(0);

	@Field
	@Column(name = "codif")
	@Caption(value = "CODIF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codif = "";

	@Field
	@Column(name = "quantidade_temp_ambiente")
	@Caption(value = "Quantidade temp ambiente")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeTempAmbiente = new BigDecimal(0);

	@Field
	@Column(name = "uf_consumo")
	@Caption(value = "UF consumo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufConsumo = "";

	@Field
	@Column(name = "base_calculo_cide")
	@Caption(value = "Base de cálculo do CIDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoCide = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_cide")
	@Caption(value = "Alíquota do CIDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaCide = new BigDecimal(0);

	@Field
	@Column(name = "valor_cide")
	@Caption(value = "Valor do CIDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCide = new BigDecimal(0);
	
	@Field
	@Column(name = "percentual_gas_natural")
	@Caption(value = "Percentual Gás Natural")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal percentualGasNatural = new BigDecimal(0);

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

	public NfeDetEspecificoCombustivelEntity() {
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

	public Integer getCodigoAnp() {
		return codigoAnp;
	}

	public void setCodigoAnp(Integer codigoAnp) {
		this.codigoAnp = (codigoAnp == null ? new Integer(0) : codigoAnp);
	}

	public String getCodif() {
		return codif;
	}

	public void setCodif(String codif) {
		this.codif = (codif == null ? "" : codif.toUpperCase().trim());
	}

	public BigDecimal getQuantidadeTempAmbiente() {
		return quantidadeTempAmbiente;
	}

	public void setQuantidadeTempAmbiente(BigDecimal quantidadeTempAmbiente) {
		this.quantidadeTempAmbiente = (quantidadeTempAmbiente == null ? new BigDecimal(
				0) : quantidadeTempAmbiente);
	}

	public String getUfConsumo() {
		return ufConsumo;
	}

	public void setUfConsumo(String ufConsumo) {
		this.ufConsumo = (ufConsumo == null ? "" : ufConsumo.toUpperCase()
				.trim());
	}

	public BigDecimal getBaseCalculoCide() {
		return baseCalculoCide;
	}

	public void setBaseCalculoCide(BigDecimal baseCalculoCide) {
		this.baseCalculoCide = (baseCalculoCide == null ? new BigDecimal(0)
				: baseCalculoCide);
	}

	public BigDecimal getAliquotaCide() {
		return aliquotaCide;
	}

	public void setAliquotaCide(BigDecimal aliquotaCide) {
		this.aliquotaCide = (aliquotaCide == null ? new BigDecimal(0)
				: aliquotaCide);
	}

	public BigDecimal getValorCide() {
		return valorCide;
	}

	public void setValorCide(BigDecimal valorCide) {
		this.valorCide = (valorCide == null ? new BigDecimal(0) : valorCide);
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}
	
	public BigDecimal getPercentualGasNatural() {
		return percentualGasNatural;
	}

	public void setPercentualGasNatural(BigDecimal percentualGasNatural) {
		this.percentualGasNatural = (percentualGasNatural == null ? new BigDecimal(0)
				: percentualGasNatural);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}