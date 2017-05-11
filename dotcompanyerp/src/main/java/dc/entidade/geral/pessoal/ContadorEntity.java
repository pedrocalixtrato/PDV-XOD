package dc.entidade.geral.pessoal;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
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
@Table(name = "contador")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContadorEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contador_id_seq")
	@SequenceGenerator(name = "contador_id_seq", sequenceName = "contador_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Inscricao CRC")
	@Column(name = "INSCRICAO_CRC")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoCrc;

	@Field
	@Caption("Uf CRC")
	@Column(name = "UF_CRC")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufCrc;

	@Field
	@Caption("Telefone")
	@Column(name = "FONE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Telefone é Obrigatório!")
	private String fone;

	@Field
	@Caption("Fax")
	@Column(name = "FAX")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fax;

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Logradouro é Obrigatório!")
	private String logradouro;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero;

	@Field
	@Caption("Complemento")
	@Column(name = "complemento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Complemento é Obrigatório!")
	private String complemento;

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Bairro é Obrigatório!")
	private String bairro;

	@Field
	@Caption("Cidade")
	@Column(name = "CIDADE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cidade;

	@Field
	@Caption("Cep")
	@Column(name = "CEP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep;

	@Field
	@Caption("Municipio IBGE")
	@Column(name = "MUNICIPIO_IBGE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIBGE;

	//@Field
	//@Caption("UF")
	//@ComboValue
	//@Analyzer(definition = "dc_combo_analyzer")
	//private String uf;
	
	@Transient
	@Caption("UF")
	private UfEntity uf;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Email é Obrigatório!")
	private String email;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome;

	@Field
	@Caption("Cpf")
	@Column(name = "CPF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cpf;

	@Field
	@Caption("Cnpj")
	@Column(name = "CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj;

	@Field
	@Caption("Site")
	@Column(name = "SITE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String site;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public ContadorEntity() {

	}

	public ContadorEntity(Integer id) {
		this.id = id;
	}

	public ContadorEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public String getInscricaoCrc() {
		return inscricaoCrc;
	}

	public void setInscricaoCrc(String inscricaoCrc) {
		this.inscricaoCrc = inscricaoCrc;
	}

	public String getUfCrc() {
		return ufCrc;
	}

	public void setUfCrc(String ufCrc) {
		this.ufCrc = ufCrc;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getMunicipioIBGE() {
		return municipioIBGE;
	}

	public void setMunicipioIBGE(Integer municipioIBGE) {
		this.municipioIBGE = municipioIBGE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
	       if (this == obj) {
	           return true;
	       }
		
	       if (!(obj instanceof ContadorEntity)) {
	           return false;
	       }
		
	       ContadorEntity that = (ContadorEntity) obj;
	       EqualsBuilder eb = new EqualsBuilder();
	       eb.append(getId(), that.getId());
	       return eb.isEquals();
	}
		
	@Override
	public int hashCode() {
	      if (getId() == null) {
	          return super.hashCode();
	      } else {
	          return new HashCodeBuilder()
	                   .append(id)
	                   .toHashCode();
	      }
	}

}