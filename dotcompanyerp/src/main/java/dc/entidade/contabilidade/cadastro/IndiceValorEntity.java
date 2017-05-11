package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_INDICE_VALOR")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IndiceValorEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contabil_indice_valor_id_seq")
	@SequenceGenerator(name = "contabil_indice_valor_id_seq", sequenceName = "contabil_indice_valor_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "data_indice")
	@Caption(value = "Data do índice")
	@Temporal(TemporalType.DATE)
	private Date dataIndice;

	@Field
	@Column(name = "valor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Valor")
	@NotNull(message = "Valor é Obrigatório")
	private BigDecimal valor = new BigDecimal(0.0);

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	// id_contabil_indice integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_indice", nullable = false)
	@Caption("Índice")
	@NotNull(message = "Indice é Obrigatório!")
	private IndiceEntity indice;

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
		return getValor().toString();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public IndiceValorEntity() {
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

	public Date getDataIndice() {
		return dataIndice;
	}

	public void setDataIndice(Date dataIndice) {
		this.dataIndice = dataIndice;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = (valor == null ? new BigDecimal(0.0) : valor);
		;
	}

	public IndiceEntity getIndice() {
		return indice;
	}

	public void setIndice(IndiceEntity indice) {
		this.indice = indice;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}