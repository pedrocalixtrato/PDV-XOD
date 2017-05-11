package dc.entidade.patrimonio;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.converter.RunField;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "patrim_apolice_seguro")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ApoliceSeguroEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_apolice_seguro_id_seq")
	@SequenceGenerator(name = "patrim_apolice_seguro_id_seq", sequenceName = "patrim_apolice_seguro_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	// @RunField(mappedName = "id")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "numero")
	@Field
	@Caption("Número")
	@Size(max = 20, message = "Tamanho inválido.")
	// @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	@RunField(mappedName = "numero")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero = "";

	@Column(name = "data_contratacao")
	@Field
	@Caption("Data da contratação")
	// @NotEmpty(message = "Não pode estar vazio.")
	@Temporal(value = TemporalType.TIMESTAMP)
	@RunField(mappedName = "dataContratacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataContratacao;

	@Column(name = "data_vencimento")
	@Field
	@Caption("Data do vencimento")
	@Temporal(value = TemporalType.TIMESTAMP)
	@RunField(mappedName = "dataVencimento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataVencimento;

	@Column(name = "valor_premio")
	@Field
	@Caption("Valor do prêmio")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@RunField(mappedName = "valorPremio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorPremio = new Double(0.0);

	@Column(name = "valor_segurado")
	@Field
	@Caption("Valor do segurado")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@RunField(mappedName = "valorSegurado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorSegurado = new Double(0.0);

	@Column(name = "observacao")
	@Field
	@Caption("Observação")
	@RunField(mappedName = "observacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	@Column(name = "imagem")
	@Field
	@Caption("Imagem")
	@RunField(mappedName = "imagem")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String imagem = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "ID_PATRIM_BEM", nullable = false)
	@Caption("Bem")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	@RunField(mappedName = "bem")
	private BemEntity bem;

	@ManyToOne
	@JoinColumn(name = "ID_SEGURADORA", nullable = false)
	@Caption("Seguradora")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	@RunField(mappedName = "seguradora")
	private SeguradoraEntity seguradora;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public ApoliceSeguroEntity() {

	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = (numero == null ? "" : numero.toUpperCase());
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValorPremio() {
		return valorPremio;
	}

	public void setValorPremio(Double valorPremio) {
		this.valorPremio = valorPremio;
	}

	public Double getValorSegurado() {
		return valorSegurado;
	}

	public void setValorSegurado(Double valorSegurado) {
		this.valorSegurado = valorSegurado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "" : observacao.toUpperCase());
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = (imagem == null ? "" : imagem.toUpperCase());
	}

	public BemEntity getBem() {
		return bem;
	}

	public void setBem(BemEntity bem) {
		this.bem = bem;
	}

	public SeguradoraEntity getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(SeguradoraEntity seguradora) {
		this.seguradora = seguradora;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}