
package dc.entidade.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.UfEntity;

@Entity
@Table(name = "agencia_banco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AgenciaBancoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "agencia_banco_id_seq")
	@SequenceGenerator(name = "agencia_banco_id_seq", sequenceName = "agencia_banco_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Código")
	@Column(name = "CODIGO")
	
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código é Obrigatório!")
	private Integer codigo = new Integer(0);

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome = "";

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Logradouro é Obrigatório!")
	private String logradouro = "";

	@Field
	@Caption("Número")
	@Column(name = "NUMERO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero = "";

	@Field
	@Caption("CEP")
	@Column(name = "CEP", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Bairro é Obrigatório!")
	private String bairro = "";

	@Field
	@Caption("Município")
	@Column(name = "MUNICIPIO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String municipio = "";

	@Field
	@Caption("UF")
	@Column(name = "UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String siglaUf = "";

	@Field
	@Caption("Telefone")
	@Column(name = "TELEFONE", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Telefone é Obrigatório!")
	private String telefone = "";

	@Field
	@Caption("Gerente")
	@Column(name = "GERENTE", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Gerente é Obrigatório!")
	private String gerente = "";

	@Field
	@Caption("Contato")
	@Column(name = "CONTATO", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Contato é Obrigatório!")
	private String contato = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Observação")
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	@Field
	@Caption("Dígito")
	@Column(name = "DIGITO", length = 1)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String digito = "";

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Banco")
	@ManyToOne(optional = false, fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_banco", referencedColumnName = "id")
	@NotNull(message = "Banco é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private BancoEntity banco;

	@Caption("UF")
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_uf")
	@NotNull(message = "UF é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private UfEntity uf;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * 
	 * @module FINANCEIRO
	 */

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="agenciaBanco",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ContaCaixa> contaCaixaList = new ArrayList<ContaCaixa>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public AgenciaBancoEntity() {

	}

	public AgenciaBancoEntity(Integer id) {
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = (codigo == null ? new Integer(0) : codigo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = (logradouro == null ? "".trim() : logradouro
				.toUpperCase().trim());
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = (numero == null ? "".trim() : numero.toUpperCase().trim());
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = (cep == null ? "".trim() : cep.toUpperCase().trim());
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = (bairro == null ? "".trim() : bairro.toUpperCase().trim());
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = (municipio == null ? "".trim() : municipio
				.toUpperCase().trim());
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = (siglaUf == null ? "".trim() : siglaUf.toUpperCase()
				.trim());
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = (telefone == null ? "".trim() : telefone.toUpperCase()
				.trim());
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = (gerente == null ? "".trim() : gerente.toUpperCase()
				.trim());
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = (contato == null ? "".trim() : contato.toUpperCase()
				.trim());
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "".trim() : observacao
				.toUpperCase().trim());
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = (digito == null ? "".trim() : digito.toUpperCase().trim());
	}

	public BancoEntity getBanco() {
		return banco;
	}

	public void setBanco(BancoEntity banco) {
		this.banco = banco;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public List<ContaCaixa> getContaCaixaList() {
		return contaCaixaList;
	}

	public void setContaCaixaList(List<ContaCaixa> contaCaixaList) {
		this.contaCaixaList = contaCaixaList;
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

	    if (!(obj instanceof AgenciaBancoEntity)) {
	           return false;
	    }

	    AgenciaBancoEntity that = (AgenciaBancoEntity) obj;
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