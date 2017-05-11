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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "patrim_depreciacao_bem")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class DepreciacaoBemEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_depreciacao_bem_id_seq")
	@SequenceGenerator(name = "patrim_depreciacao_bem_id_seq", sequenceName = "patrim_depreciacao_bem_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_depreciacao")
	@Field
	@Caption("Data da depreciação")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataDepreciacao;

	@Column(name = "dias")
	@Field
	@Caption("Dias")
	private Integer dias = new Integer(0);

	@Column(name = "taxa")
	@Field
	@Caption("Taxa")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double taxa = new Double(0.0);

	@Column(name = "indice")
	@Field
	@Caption("Índice")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double indice = new Double(0.0);

	@Column(name = "valor")
	@Field
	@Caption("Valor")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double valor = new Double(0.0);

	@Column(name = "depreciacao_acumulada")
	@Field
	@Caption("Depreciação acumulada")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double depreciacaoAcumulada = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "ID_PATRIM_BEM", nullable = false)
	@Caption("Bem")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private BemEntity bem;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public DepreciacaoBemEntity() {

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

	public Date getDataDepreciacao() {
		return dataDepreciacao;
	}

	public void setDataDepreciacao(Date dataDepreciacao) {
		this.dataDepreciacao = dataDepreciacao;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = (dias == null ? new Integer(0) : dias);
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = (taxa == null ? new Double(0.0) : taxa);
	}

	public Double getIndice() {
		return indice;
	}

	public void setIndice(Double indice) {
		this.indice = (indice == null ? new Double(0.0) : indice);
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = (valor == null ? new Double(0.0) : valor);
	}

	public Double getDepreciacaoAcumulada() {
		return depreciacaoAcumulada;
	}

	public void setDepreciacaoAcumulada(Double depreciacaoAcumulada) {
		this.depreciacaoAcumulada = (depreciacaoAcumulada == null ? new Double(
				0.0) : depreciacaoAcumulada);
	}

	public BemEntity getBem() {
		return bem;
	}

	public void setBem(BemEntity bem) {
		this.bem = bem;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}