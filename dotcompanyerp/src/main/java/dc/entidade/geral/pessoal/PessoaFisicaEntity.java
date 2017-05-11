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
import javax.persistence.ManyToOne;
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
import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.TipoSanguineoEn;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "pessoa_fisica")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaFisicaEntity implements Serializable {

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
	@Caption("CPF")
	@Column(name = "CPF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cpf = "";

	@Field
	@Caption("RG")
	@Column(name = "RG")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String rg = "";

	@Field
	@Caption("RG - Órgão emissor")
	@Column(name = "ORGAO_RG")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String orgaoRg = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("RG - Data de emissão")
	@Column(name = "DATA_EMISSAO_RG")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEmissaoRg;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de nascimento")
	@Column(name = "DATA_NASCIMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataNascimento;

	@Field
	@Caption("Naturalidade")
	@Column(name = "NATURALIDADE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String naturalidade = "";

	@Field
	@Caption("Nacionalidade")
	@Column(name = "NACIONALIDADE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nacionalidade = "";

	@Field
	@Caption("CNH")
	@Column(name = "CNH_NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnhNumero = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("CNH - Vencimento")
	@Column(name = "CNH_VENCIMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date cnhVencimento;

	@Field
	@Caption("Número do título de eleitor")
	@Column(name = "TITULO_ELEITORAL_NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tituloEleitoralNumero = "";

	@Field
	@Caption("Título de eleitor - Zona")
	@Column(name = "TITULO_ELEITORAL_ZONA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer tituloEleitoralZona = new Integer(0);

	@Field
	@Caption("Título de eleitor - Seção")
	@Column(name = "TITULO_ELEITORAL_SECAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer tituloEleitoralSecao = new Integer(0);

	@Field
	@Caption("Número da reservista")
	@Column(name = "RESERVISTA_NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String reservistaNumero = "";

	@Field
	@Caption("Nome da mãe")
	@Column(name = "NOME_MAE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeMae = "";

	@Field
	@Caption("Nome do pai")
	@Column(name = "NOME_PAI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomePai = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Sexo")
	@Column(name = "sexo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SexoEn sexo;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("CNH categoria")
	@Column(name = "cnh_categoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private CnhEn cnh;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Raça")
	@Column(name = "raca")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private RacaEn raca;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo de sangue")
	@Column(name = "tipo_sangue")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoSanguineoEn tipoSangue;

	@Enumerated(EnumType.ORDINAL)
	@Field
	@Caption("Reservista categoria")
	@Column(name = "reservista_categoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private CategoriaReservistaEn reservistaCategoria;

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Estado civil")
	@ManyToOne
	@JoinColumn(name = "id_estado_civil")
	//@NotNull(message = "Estado Civil é Obrigatório!")
	private EstadoCivilEntity estadoCivil;

	@Caption("Pessoa")
	@OneToOne
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	private PessoaEntity pessoa;
	
	/*@Caption("Pessoa Eventos")
	@OneToOne
	@JoinColumn(name = "id_pessoa_eventos", insertable = true, updatable = true)
	private PessoaEventosEntity pessoaEventos;*/

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaFisicaEntity() {

	}

	public PessoaFisicaEntity(Integer id) {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = (cpf == null ? "".trim() : cpf.toUpperCase().trim());
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = (rg == null ? "".trim() : rg.toUpperCase().trim());
	}

	public String getOrgaoRg() {
		return orgaoRg;
	}

	public void setOrgaoRg(String orgaoRg) {
		this.orgaoRg = (orgaoRg == null ? "".trim() : orgaoRg.toUpperCase()
				.trim());
	}

	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = (naturalidade == null ? "".trim() : naturalidade
				.toUpperCase().trim());
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = (nacionalidade == null ? "".trim() : nacionalidade
				.toUpperCase().trim());
	}

	public String getCnhNumero() {
		return cnhNumero;
	}

	public void setCnhNumero(String cnhNumero) {
		this.cnhNumero = (cnhNumero == null ? "".trim() : cnhNumero
				.toUpperCase().trim());
	}

	public Date getCnhVencimento() {
		return cnhVencimento;
	}

	public void setCnhVencimento(Date cnhVencimento) {
		this.cnhVencimento = cnhVencimento;
	}

	public String getTituloEleitoralNumero() {
		return tituloEleitoralNumero;
	}

	public void setTituloEleitoralNumero(String tituloEleitoralNumero) {
		this.tituloEleitoralNumero = (tituloEleitoralNumero == null ? "".trim()
				: tituloEleitoralNumero.toUpperCase().trim());
	}

	public Integer getTituloEleitoralZona() {
		return tituloEleitoralZona;
	}

	public void setTituloEleitoralZona(Integer tituloEleitoralZona) {
		this.tituloEleitoralZona = (tituloEleitoralZona == null ? new Integer(0)
				: tituloEleitoralZona);
	}

	public Integer getTituloEleitoralSecao() {
		return tituloEleitoralSecao;
	}

	public void setTituloEleitoralSecao(Integer tituloEleitoralSecao) {
		this.tituloEleitoralSecao = (tituloEleitoralSecao == null ? new Integer(
				0) : tituloEleitoralSecao);
	}

	public String getReservistaNumero() {
		return reservistaNumero;
	}

	public void setReservistaNumero(String reservistaNumero) {
		this.reservistaNumero = (reservistaNumero == null ? "".trim()
				: reservistaNumero.toUpperCase().trim());
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = (nomeMae == null ? "".trim() : nomeMae.toUpperCase()
				.trim());
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = (nomePai == null ? "".trim() : nomePai.toUpperCase()
				.trim());
	}

	public SexoEn getSexo() {
		return sexo;
	}

	public void setSexo(SexoEn sexo) {
		this.sexo = sexo;
	}

	public CnhEn getCnh() {
		return cnh;
	}

	public void setCnh(CnhEn cnh) {
		this.cnh = cnh;
	}

	public RacaEn getRaca() {
		return raca;
	}

	public void setRaca(RacaEn raca) {
		this.raca = raca;
	}

	public TipoSanguineoEn getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(TipoSanguineoEn tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public CategoriaReservistaEn getReservistaCategoria() {
		return reservistaCategoria;
	}

	public void setReservistaCategoria(CategoriaReservistaEn reservistaCategoria) {
		this.reservistaCategoria = reservistaCategoria;
	}

	public EstadoCivilEntity getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEntity estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*public PessoaEventosEntity getPessoaEventos() {
		return pessoaEventos;
	}

	public void setPessoaEventos(PessoaEventosEntity pessoaEventos) {
		this.pessoaEventos = pessoaEventos;
		
	}*/

}
