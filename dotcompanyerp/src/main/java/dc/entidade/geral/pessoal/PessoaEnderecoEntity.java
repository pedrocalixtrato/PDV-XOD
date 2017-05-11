package dc.entidade.geral.pessoal;

import java.io.Serializable;

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
import dc.entidade.geral.diverso.UfEntity;

@Entity
@Table(name = "pessoa_endereco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaEnderecoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

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
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro = "";

	@Field
	@Caption("Número")
	@Column(name = "NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numero = new Integer(0);

	@Field
	@Caption("Complemento")
	@Column(name = "COMPLEMENTO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento = "";

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro = "";

	@Field
	@Caption("Cidade")
	@Column(name = "CIDADE", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cidade = "";

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 8)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Caption("Municipio IBGE")
	@Column(name = "MUNICIPIO_IBGE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIbge = new Integer(0);

	@Field
	@Caption("Principal")
	@Column(name = "PRINCIPAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean principal = Boolean.FALSE;

	@Field
	@Caption("Entrega")
	@Column(name = "ENTREGA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean entrega = Boolean.FALSE;

	@Field
	@Caption("Cobrança")
	@Column(name = "COBRANCA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean cobranca = Boolean.FALSE;

	@Field
	@Caption("Correspondência")
	@Column(name = "CORRESPONDENCIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean correspondencia = Boolean.FALSE;

	@Field
	@Caption("UF sigla")
	@Column(name = "uf", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String siglaUf = "";

	@Field
	@Caption("UF")
	@Column(name = "id_uf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer idUf;
	
	@Field
	@Caption("fone")
	@Column(name = "FONE", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone = "";

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Pessoa")
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private PessoaEntity pessoa;
	
	/*@Caption("Pessoa Eventos")
	@ManyToOne
	@JoinColumn(name = "id_pessoa_eventos")
	private PessoaEventosEntity pessoaEventos;*/

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	private UfEntity uf;

	/**
	 * CONSTRUTOR
	 */

	public PessoaEnderecoEntity() {

	}

	public PessoaEnderecoEntity(Integer id) {
		this.id = id;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = (logradouro == null ? "".trim() : logradouro
				.toUpperCase().trim());
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = (numero == null ? new Integer(0) : numero);
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = (complemento == null ? "".trim() : complemento
				.toUpperCase().trim());
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = (bairro == null ? "".trim() : bairro.toUpperCase().trim());
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = (cidade == null ? "".trim() : cidade.toUpperCase().trim());
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = (cep == null ? "".trim() : cep.toUpperCase().trim());
	}

	public Integer getMunicipioIbge() {
		return municipioIbge;
	}

	public void setMunicipioIbge(Integer municipioIbge) {
		this.municipioIbge = (municipioIbge == null ? new Integer(0)
				: municipioIbge);
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = (principal == null ? Boolean.FALSE : principal);
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public void setEntrega(Boolean entrega) {
		this.entrega = (entrega == null ? Boolean.FALSE : entrega);
	}

	public Boolean getCobranca() {
		return cobranca;
	}

	public void setCobranca(Boolean cobranca) {
		this.cobranca = (cobranca == null ? Boolean.FALSE : cobranca);
	}

	public Boolean getCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(Boolean correspondencia) {
		this.correspondencia = (correspondencia == null ? Boolean.FALSE
				: correspondencia);
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = (siglaUf == null ? "".trim() : siglaUf.toUpperCase()
				.trim());
	}

	public Integer getIdUf() {
		return idUf;
	}

	public void setIdUf(Integer idUf) {
		this.idUf = idUf;
	}
	
	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = (fone == null ? "".trim() : fone.toUpperCase().trim());
		
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
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