package dc.entidade.nfe;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "nfe_destinatario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDestinatarioEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_destinatario_id_seq")
	@SequenceGenerator(name = "nfe_destinatario_id_seq", sequenceName = "nfe_destinatario_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "cpf_cnpj")
	@Caption(value = "CPF / CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cpfCnpj = "";

	@Field
	@Column(name = "nome")
	@Caption(value = "Nome")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	@Field
	@Column(name = "logradouro")
	@Caption(value = "Logradouro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro = "";

	@Field
	@Column(name = "numero")
	@Caption(value = "Número")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero = "";

	@Field
	@Column(name = "complemento")
	@Caption(value = "Complemento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento = "";

	@Field
	@Column(name = "bairro")
	@Caption(value = "Bairro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro = "";

	@Field
	@Column(name = "codigo_municipio")
	@Caption(value = "Código do município")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoMunicipio;

	@Field
	@Column(name = "nome_municipio")
	@Caption(value = "Nome do município")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeMunicipio = "";

	@Field
	@Column(name = "uf")
	@Caption(value = "UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String uf = "";

	@Field
	@Column(name = "cep")
	@Caption(value = "CEP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Column(name = "codigo_pais")
	@Caption(value = "Código do país")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoPais = new Integer(1053);

	@Field
	@Column(name = "nome_pais")
	@Caption(value = "Nome do país")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomePais = "BRASIL";

	@Field
	@Column(name = "telefone")
	@Caption(value = "Telefone")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String telefone = "";

	@Field
	@Column(name = "inscricao_estadual")
	@Caption(value = "Inscrição estadual")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadual = "";
	
	@Field
	@Column(name = "inscricao_municipal")
	@Caption(value = "Inscrição Municipal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoMunicipal = "";

	@Field
	@Column(name = "suframa")
	@Caption(value = "SUFRAMA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer suframa;

	@Field
	@Column(name = "email")
	@Caption(value = "E-mail")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email = "";
	
	@Field
	@Column(name = "estrangeiro_identificacao")
	@Caption(value = "Estrangeiro Identificação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String estrangeiroIdentificacao = "";
	
	@Field
	@Column(name = "indicador_ie")
	@Caption(value = "Indicador IE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer indicadorIe;

	/**
	 * REFERENCIA - FK
	 */

	// id_nfe_cabecalho integer NOT NULL,

	@OneToOne
	@JoinColumn(name = "id_nfe_cabecalho")
	private NfeCabecalhoEntity nfeCabecalho;

	// id_empresa integer,

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDestinatarioEntity() {
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

	public String getCpfCnpj() {
		return cpfCnpj.trim();
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = (cpfCnpj == null ? "" : cpfCnpj.toUpperCase().trim());
	}

	public String getNome() {
		return nome.trim();
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome
				.toUpperCase().trim());
	}

	public String getLogradouro() {
		return logradouro.trim();
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = (logradouro == null ? "" : logradouro.toUpperCase()
				.trim());
	}

	public String getNumero() {
		return numero.trim();
	}

	public void setNumero(String numero) {
		this.numero = (numero == null ? "" : numero.toUpperCase().trim());
	}

	public String getComplemento() {
		return complemento.trim();
	}

	public void setComplemento(String complemento) {
		this.complemento = (complemento == null ? "" : complemento
				.toUpperCase().trim());
	}

	public String getBairro() {
		return bairro.trim();
	}

	public void setBairro(String bairro) {
		this.bairro = (bairro == null ? "" : bairro.toUpperCase().trim());
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = (codigoMunicipio == null ? new Integer(0)
				: codigoMunicipio);
	}

	public String getNomeMunicipio() {
		return nomeMunicipio.trim();
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = (nomeMunicipio == null ? "" : nomeMunicipio
				.toUpperCase().trim());
	}

	public String getUf() {
		return uf.trim();
	}

	public void setUf(String uf) {
		this.uf = (uf == null ? "" : uf.toUpperCase().trim());
	}

	public String getCep() {
		return cep.trim();
	}

	public void setCep(String cep) {
		this.cep = (cep == null ? "" : cep.toUpperCase().trim());
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = (codigoPais == null ? new Integer(1053) : codigoPais);
	}

	public String getNomePais() {
		return nomePais.trim();
	}

	public void setNomePais(String nomePais) {
		this.nomePais = (nomePais == null ? "BRASIL" : nomePais.toUpperCase()
				.trim());
	}

	public String getTelefone() {
		return telefone.trim();
	}

	public void setTelefone(String telefone) {
		this.telefone = (telefone == null ? "" : telefone.toUpperCase().trim());
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual.trim();
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = (inscricaoEstadual == null ? ""
				: inscricaoEstadual.toUpperCase().trim());
	}
	
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal.trim();
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = (inscricaoMunicipal == null ? ""
				: inscricaoMunicipal.toUpperCase().trim());
	}

	public Integer getSuframa() {
		return suframa;
	}

	public void setSuframa(Integer suframa) {
		this.suframa = suframa;
	}

	public String getEmail() {
		return email.trim();
	}

	public void setEmail(String email) {
		this.email = (email == null ? "" : email.toUpperCase().trim());
	}
	
	public String getEstrangeiroIdentificacao() {
		return estrangeiroIdentificacao;
	}

	public void setEstrangeiroIdentificacao(String estrangeiroIdentificacao) {
		this.estrangeiroIdentificacao = estrangeiroIdentificacao;
	}

	public Integer getIndicadorIe() {
		return indicadorIe;
	}

	public void setIndicadorIe(Integer indicadorIe) {
		this.indicadorIe = indicadorIe;
	}

	public NfeCabecalhoEntity getNfeCabecalho() {
		return nfeCabecalho;
	}

	public void setNfeCabecalho(NfeCabecalhoEntity nfeCabecalho) {
		this.nfeCabecalho = nfeCabecalho;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}