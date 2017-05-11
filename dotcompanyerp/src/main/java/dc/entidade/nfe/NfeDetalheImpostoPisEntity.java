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
@Table(name = "nfe_detalhe_imposto_pis")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoPisEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_pis_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_pis_id_seq", sequenceName = "nfe_detalhe_imposto_pis_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "cst_pis")
	@Caption(value = "CST PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstPis = "";

	@Field
	@Column(name = "quantidade_vendida")
	@Caption(value = "Quantidade vendida")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeVendida = new BigDecimal(0);

	@Field
	@Column(name = "valor_base_calculo_pis")
	@Caption(value = "Valor da base de cálculo do PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBaseCalculoPis = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_pis_percentual")
	@Caption(value = "Alíquota do PIS percentual")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaPisPercentual = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_pis_reais")
	@Caption(value = "Alíquota do PIS reais")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaPisReais = new BigDecimal(0);

	@Field
	@Column(name = "valor_pis")
	@Caption(value = "Valor do PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPis = new BigDecimal(0);

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

	public NfeDetalheImpostoPisEntity() {
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

	public String getCstPis() {
		return cstPis;
	}

	public void setCstPis(String cstPis) {
		this.cstPis = (cstPis == null ? "" : cstPis.toUpperCase().trim());
	}

	public BigDecimal getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(BigDecimal quantidadeVendida) {
		this.quantidadeVendida = (quantidadeVendida == null ? new BigDecimal(0)
				: quantidadeVendida);
	}

	public BigDecimal getValorBaseCalculoPis() {
		return valorBaseCalculoPis;
	}

	public void setValorBaseCalculoPis(BigDecimal valorBaseCalculoPis) {
		this.valorBaseCalculoPis = (valorBaseCalculoPis == null ? new BigDecimal(
				0) : valorBaseCalculoPis);
	}

	public BigDecimal getAliquotaPisPercentual() {
		return aliquotaPisPercentual;
	}

	public void setAliquotaPisPercentual(BigDecimal aliquotaPisPercentual) {
		this.aliquotaPisPercentual = (aliquotaPisPercentual == null ? new BigDecimal(
				0) : aliquotaPisPercentual);
	}

	public BigDecimal getAliquotaPisReais() {
		return aliquotaPisReais;
	}

	public void setAliquotaPisReais(BigDecimal aliquotaPisReais) {
		this.aliquotaPisReais = (aliquotaPisReais == null ? new BigDecimal(0)
				: aliquotaPisReais);
	}

	public BigDecimal getValorPis() {
		return valorPis;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = (valorPis == null ? new BigDecimal(0) : valorPis);
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