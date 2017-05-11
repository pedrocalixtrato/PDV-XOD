package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.CrtEn;
import dc.control.enums.TipoRegimeEn;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "pessoa_juridica")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaJuridicaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("CNPJ")
	@Column(name = "CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj = "";

	@Field
	@Caption("Nome fantasia")
	@Column(name = "FANTASIA", length = 150)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fantasia = "";

	@Field
	@Caption("Inscrição municipal")
	@Column(name = "INSCRICAO_MUNICIPAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoMunicipal = "";

	@Field
	@Caption("Inscrição estadual")
	@Column(name = "INSCRICAO_ESTADUAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadual = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data da constituição")
	@Column(name = "DATA_CONSTITUICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataConstituicao;

	@Field
	@Caption("Suframa")
	@Column(name = "SUFRAMA", length = 9)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String suframa = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo de regime")
	@Column(name = "tipo_regime")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoRegimeEn tipoRegime;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("CRT")
	@Column(name = "crt")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private CrtEn crt;

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Pessoa")
	@OneToOne()
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	private PessoaEntity pessoa;
	
	/*@Caption("Pessoa Eventos")
	@OneToOne()
	@JoinColumn(name = "id_pessoa_eventos", insertable = true, updatable = true)
	private PessoaEventosEntity pessoaEventos;*/

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaJuridicaEntity() {

	}

	public PessoaJuridicaEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = (cnpj == null ? "".trim() : cnpj.toUpperCase().trim());
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = (fantasia == null ? "".trim() : fantasia.toUpperCase()
				.trim());
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = (inscricaoMunicipal == null ? "".trim()
				: inscricaoMunicipal.toUpperCase().trim());
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = (inscricaoEstadual == null ? "".trim()
				: inscricaoEstadual.toUpperCase().trim());
	}

	public Date getDataConstituicao() {
		return dataConstituicao;
	}

	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = (suframa == null ? "".trim() : suframa.toUpperCase()
				.trim());
	}

	public TipoRegimeEn getTipoRegime() {
		return tipoRegime;
	}

	public void setTipoRegime(TipoRegimeEn tipoRegime) {
		this.tipoRegime = tipoRegime;
	}

	public CrtEn getCrt() {
		return crt;
	}

	public void setCrt(CrtEn crt) {
		this.crt = crt;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}
	
	/*public PessoaEventosEntity getPessoaEventos() {
		return pessoaEventos;
	}

	public void setPessoaEventos(PessoaEventosEntity entity) {
		this.pessoaEventos = pessoaEventos;
		
	}*/

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}