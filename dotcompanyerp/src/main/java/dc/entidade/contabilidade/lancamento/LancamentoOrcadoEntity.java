package dc.entidade.contabilidade.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_LANCAMENTO_ORCADO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoOrcadoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_lancamento_orcado_id_seq")
	@SequenceGenerator(name = "contabil_lancamento_orcado_id_seq", sequenceName = "contabil_lancamento_orcado_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "ano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Ano")
	@NotNull(message = "Ano é Obrigatório!")
	private String ano;

	@Field
	@Column(name = "janeiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Janeiro")
	@NotNull(message = "Janeiro é Obrigatório!")
	private BigDecimal janeiro;

	@Field
	@Column(name = "fevereiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Fevereiro")
	@NotNull(message = "Fevereiro é Obrigatório!")
	private BigDecimal fevereiro;

	@Field
	@Column(name = "marco")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Março")
	@NotNull(message = "Março é Obrigatório!")
	private BigDecimal marco;

	@Field
	@Column(name = "abril")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Abril")
	@NotNull(message = "Abril é Obrigatório!")
	private BigDecimal abril;

	@Field
	@Column(name = "Maio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Maio")
	@NotNull(message = "Maio é Obrigatório!")
	private BigDecimal maio;

	@Field
	@Column(name = "junho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Junho")
	@NotNull(message = "Junho é Obrigatório!")
	private BigDecimal junho;

	@Field
	@Column(name = "julho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Julho")
	@NotNull(message = "Julho é Obrigatório!")
	private BigDecimal julho;

	@Field
	@Column(name = "agosto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Agosto")
	@NotNull(message = "Agosto é Obrigatório!")
	private BigDecimal agosto;

	@Field
	@Column(name = "setembro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Setembro")
	@NotNull(message = "Setembro é Obrigatório!")
	private BigDecimal setembro;

	@Field
	@Column(name = "outubro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Outubro")
	@NotNull(message = "Outubro é Obrigatório!")
	private BigDecimal outubro;

	@Field
	@Column(name = "novembro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Novembro")
	@NotNull(message = "Novembro é Obrigatório!")
	private BigDecimal novembro;

	@Field
	@Column(name = "dezembro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Dezembro")
	@NotNull(message = "Dezembro é Obrigatório!")
	private BigDecimal dezembro;

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_contabil_conta integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_conta", nullable = false)
	@Caption("Conta")
	private ContaEntity conta;

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
		return getAno();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public LancamentoOrcadoEntity() {
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

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = (ano);
	}

	public BigDecimal getJaneiro() {
		return janeiro;
	}

	public void setJaneiro(BigDecimal janeiro) {
		this.janeiro = (janeiro == null ? new BigDecimal(0.0) : janeiro);
	}

	public BigDecimal getFevereiro() {
		return fevereiro;
	}

	public void setFevereiro(BigDecimal fevereiro) {
		this.fevereiro = (fevereiro == null ? new BigDecimal(0.0) : fevereiro);
	}

	public BigDecimal getMarco() {
		return marco;
	}

	public void setMarco(BigDecimal marco) {
		this.marco = (marco == null ? new BigDecimal(0.0) : marco);
	}

	public BigDecimal getAbril() {
		return abril;
	}

	public void setAbril(BigDecimal abril) {
		this.abril = (abril == null ? new BigDecimal(0.0) : abril);
	}

	public BigDecimal getMaio() {
		return maio;
	}

	public void setMaio(BigDecimal maio) {
		this.maio = (maio == null ? new BigDecimal(0.0) : maio);
	}

	public BigDecimal getJunho() {
		return junho;
	}

	public void setJunho(BigDecimal junho) {
		this.junho = (junho == null ? new BigDecimal(0.0) : junho);
	}

	public BigDecimal getJulho() {
		return julho;
	}

	public void setJulho(BigDecimal julho) {
		this.julho = (julho == null ? new BigDecimal(0.0) : julho);
	}

	public BigDecimal getAgosto() {
		return agosto;
	}

	public void setAgosto(BigDecimal agosto) {
		this.agosto = (agosto == null ? new BigDecimal(0.0) : agosto);
	}

	public BigDecimal getSetembro() {
		return setembro;
	}

	public void setSetembro(BigDecimal setembro) {
		this.setembro = (setembro == null ? new BigDecimal(0.0) : setembro);
	}

	public BigDecimal getOutubro() {
		return outubro;
	}

	public void setOutubro(BigDecimal outubro) {
		this.outubro = (outubro == null ? new BigDecimal(0.0) : outubro);
	}

	public BigDecimal getNovembro() {
		return novembro;
	}

	public void setNovembro(BigDecimal novembro) {
		this.novembro = (novembro == null ? new BigDecimal(0.0) : novembro);
	}

	public BigDecimal getDezembro() {
		return dezembro;
	}

	public void setDezembro(BigDecimal dezembro) {
		this.dezembro = (dezembro == null ? new BigDecimal(0.0) : dezembro);
	}

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof LancamentoOrcadoEntity)) {
	           return false;
	    }

	    LancamentoOrcadoEntity that = (LancamentoOrcadoEntity) obj;
	    EqualsBuilder eb = new EqualsBuilder();
	    eb.append(getId(), that.getId());
	    return eb.isEquals();
	}

	@Override
	public int hashCode() {
	    if (getId() == null) {
	          return super.hashCode();
	    } else {
	          return new HashCodeBuilder()
	                    .append(id)
	                    .toHashCode();
	    }
	}

}