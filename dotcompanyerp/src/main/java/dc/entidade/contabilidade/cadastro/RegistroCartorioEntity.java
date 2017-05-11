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
@Table(name = "REGISTRO_CARTORIO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class RegistroCartorioEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_cartorio_id_seq")
	@SequenceGenerator(name = "registro_cartorio_id_seq", sequenceName = "registro_cartorio_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Nome do cartório")
	@Column(name = "NOME_CARTORIO")
	private String nomeCartorio = "";

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption(value = "Data de registro")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_REGISTRO")
	private Date dataRegistro;

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Número")
	@Column(name = "NUMERO")
	private Integer numero = new Integer(0);

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Folha")
	@Column(name = "FOLHA")
	private Integer folha = new Integer(0);

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Livro")
	@Column(name = "LIVRO")
	private Integer livro = new Integer(0);

	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Nire")
	@Column(name = "NIRE")
	private String nire = "";

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
		return getNomeCartorio();
	}

	/**
	 * CONSTRUTOR
	 */

	public RegistroCartorioEntity() {
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

	public String getNomeCartorio() {
		return nomeCartorio;
	}

	public void setNomeCartorio(String nomeCartorio) {
		this.nomeCartorio = (nomeCartorio == null ? "" : nomeCartorio
				.toUpperCase());
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = (numero == null ? new Integer(0) : numero);
	}

	public Integer getFolha() {
		return folha;
	}

	public void setFolha(Integer folha) {
		this.folha = (folha == null ? new Integer(0) : folha);
	}

	public Integer getLivro() {
		return livro;
	}

	public void setLivro(Integer livro) {
		this.livro = (livro == null ? new Integer(0) : livro);
	}

	public String getNire() {
		return nire;
	}

	public void setNire(String nire) {
		this.nire = (nire == null ? "" : nire.toUpperCase());
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}