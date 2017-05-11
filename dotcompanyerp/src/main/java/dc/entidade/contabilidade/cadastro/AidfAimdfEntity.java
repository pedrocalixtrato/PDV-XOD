package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
@Table(name = "AIDF_AIMDF")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AidfAimdfEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aidf_aimdf_id_seq")
	@SequenceGenerator(name = "aidf_aimdf_id_seq", sequenceName = "aidf_aimdf_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Número")
	@Column(name = "NUMERO")
	private Integer numero = new Integer(0);

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption(value = "Data de validade")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VALIDADE")
	private Date dataValidade;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption(value = "Data de autorização")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_AUTORIZACAO")
	private Date dataAutorizacao;

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Número da autorização")
	@Column(name = "NUMERO_AUTORIZACAO")
	private String numeroAutorizacao = "";

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Formulário disponível")
	@Column(name = "FORMULARIO_DISPONIVEL")
	private String formularioDisponivel = "";

	/**
	 * REFERENCIA - FK
	 */

	// @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	// @ManyToOne(optional = false)
	// private EmpresaVO empresa;

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
		return getNumeroAutorizacao();
	}

	/**
	 * CONSTRUTOR
	 */

	public AidfAimdfEntity() {
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = (numero == null ? new Integer(0) : numero);
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getNumeroAutorizacao() {
		return numeroAutorizacao;
	}

	public void setNumeroAutorizacao(String numeroAutorizacao) {
		this.numeroAutorizacao = (numeroAutorizacao == null ? ""
				: numeroAutorizacao.toUpperCase());
	}

	public String getFormularioDisponivel() {
		return formularioDisponivel;
	}

	public void setFormularioDisponivel(String formularioDisponivel) {
		this.formularioDisponivel = (formularioDisponivel == null ? ""
				: formularioDisponivel.toUpperCase());
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}