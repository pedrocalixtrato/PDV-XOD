package dc.entidade.folhapagamento.movimento;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Length;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_ppp_exame_medico")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PppExameMedicoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "folha_ppp_exame_medico_id_seq")
	@SequenceGenerator(name = "folha_ppp_exame_medico_id_seq", sequenceName = "folha_ppp_exame_medico_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_ultimo")
	@Field
	@Caption("Data da última")
	private Date dataUltimo;

	@Column(name = "tipo")
	@Field
	@Caption("Tipo")
	@Length(max = 1, message = "O tamanho deve ser no máximo 1 caracter")
	private String tipo = "";

	@Column(name = "natureza")
	@Field
	@Caption("Natureza")
	private String natureza = "";

	@Column(name = "exame")
	@Field
	@Caption("Exame")
	@Length(max = 1, message = "O tamanho deve ser no máximo 1 caracter")
	private String exame = "";

	@Column(name = "indicacao_resultados")
	@Field
	@Caption("Indicação de resultados")
	private String indicacaoResultados = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_folha_ppp integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_folha_ppp", nullable = false)
	@Caption("Ppp")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private PppEntity ppp;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public PppExameMedicoEntity() {
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

	public Date getDataUltimo() {
		return dataUltimo;
	}

	public void setDataUltimo(Date dataUltimo) {
		this.dataUltimo = dataUltimo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = (natureza == null ? "" : natureza.toUpperCase());
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = (exame == null ? "" : exame.toUpperCase());
	}

	public String getIndicacaoResultados() {
		return indicacaoResultados;
	}

	public void setIndicacaoResultados(String indicacaoResultados) {
		this.indicacaoResultados = (indicacaoResultados == null ? ""
				: indicacaoResultados.toUpperCase());
	}

	public PppEntity getPpp() {
		return ppp;
	}

	public void setPpp(PppEntity ppp) {
		this.ppp = ppp;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}