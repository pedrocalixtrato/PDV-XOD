package dc.entidade.contabilidade.demonstrativo;

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
@Table(name = "CONTABIL_DRE_DETALHE")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class DreDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_dre_detalhe_id_seq")
	@SequenceGenerator(name = "contabil_dre_detalhe_id_seq", sequenceName = "contabil_dre_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "classificacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Classificação")
	private String classificacao = "";

	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao = "";

	@Field
	@Column(name = "forma_calculo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Forma do cálculo")
	private String formaCalculo = "";

	@Field
	@Column(name = "sinal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Sinal")
	private String sinal = "";

	@Field
	@Column(name = "natureza")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Natureza")
	private String natureza = "";

	@Field
	@Caption("Valor")
	@Column(name = "VALOR", precision = 14, scale = 0)
	private BigDecimal valor;

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	// id_contabil_dre_cabecalho integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_dre_cabecalho", nullable = false)
	@Caption("DRE cabeçalho")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private DreCabecalhoEntity dreCabecalho;

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
		return getClassificacao();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public DreDetalheEntity() {
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

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = (classificacao == null ? "" : classificacao
				.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getFormaCalculo() {
		return formaCalculo;
	}

	public void setFormaCalculo(String formaCalculo) {
		this.formaCalculo = (formaCalculo == null ? "" : formaCalculo
				.toUpperCase());
	}

	public String getSinal() {
		return sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = (sinal == null ? "" : sinal.toUpperCase());
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = (natureza == null ? "" : natureza.toUpperCase());
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = (valor == null ? new BigDecimal(0.0) : valor);
	}

	public DreCabecalhoEntity getDreCabecalho() {
		return dreCabecalho;
	}

	public void setDreCabecalho(DreCabecalhoEntity dreCabecalho) {
		this.dreCabecalho = dreCabecalho;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}