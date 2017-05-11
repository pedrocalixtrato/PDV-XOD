package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.TipoPessoaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.outro.ConvenioEntity;

@Entity
@Table(name = "pessoa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

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
	@Caption("Nome")
	@Column(name = "NOME")
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	@ComboValue
	private String nome = "";

	@Field
	@Caption("Email")
	@Column(name = "EMAIL")
	
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Email é Obrigatório!")
	private String email = "";

	@Field
	@Caption("Site")
	@Column(name = "SITE")
	
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Site é Obrigatório!")
	private String site = "";

	@Field
	@Caption("Cliente")
	@Column(name = "CLIENTE")
	
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoCliente = "0";

	@Field
	@Caption("Fornecedor")
	@Column(name = "FORNECEDOR")
	
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean tipoFornecedor = Boolean.FALSE;

	@Field
	@Caption("Colaborador")
	@Column(name = "COLABORADOR")
	
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean tipoColaborador = Boolean.FALSE;

	@Field
	@Caption("Transportadora")
	@Column(name = "TRANSPORTADORA")
	
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean tipoTransportadora = Boolean.FALSE;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo")
	@Column(name = "TIPO")
	
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Tipo de Pessoa é Obrigatório!")
	private TipoPessoaEn tipo;

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private PessoaFisicaEntity pessoaFisica;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private PessoaJuridicaEntity pessoaJuridica;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ClienteEntity cliente;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ColaboradorEntity colaborador;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private FornecedorEntity fornecedor;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private TransportadoraEntity transportadora;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaContatoEntity> pessoaContatoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaEnderecoEntity> pessoaEnderecoList = new ArrayList<>();
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ConvenioEntity> convenioList = new ArrayList<>();

	// @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	// private List<ClienteEntity> clienteList;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaEntity() {

	}

	public PessoaEntity(Integer id) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null ? "".trim() : email.toLowerCase().trim());
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = (site == null ? "".trim() : site.toLowerCase().trim());
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = (tipoCliente == null ? "0".trim() : tipoCliente
				.toUpperCase().trim());
	}

	public Boolean getTipoFornecedor() {
		return tipoFornecedor;
	}

	public void setTipoFornecedor(Boolean tipoFornecedor) {
		this.tipoFornecedor = (tipoFornecedor == null ? Boolean.FALSE
				: tipoFornecedor);
	}

	public Boolean getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(Boolean tipoColaborador) {
		this.tipoColaborador = (tipoColaborador == null ? Boolean.FALSE
				: tipoColaborador);
	}

	public Boolean getTipoTransportadora() {
		return tipoTransportadora;
	}

	public void setTipoTransportadora(Boolean tipoTransportadora) {
		this.tipoTransportadora = (tipoTransportadora == null ? Boolean.FALSE
				: tipoTransportadora);
	}

	public TipoPessoaEn getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoaEn tipo) {
		this.tipo = tipo;
	}

	public PessoaFisicaEntity getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridicaEntity getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridicaEntity pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TransportadoraEntity getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(TransportadoraEntity transportadora) {
		this.transportadora = transportadora;
	}

	public List<PessoaContatoEntity> getPessoaContatoList() {
		return pessoaContatoList;
	}

	public void setPessoaContatoList(List<PessoaContatoEntity> pessoaContatoList) {
		this.pessoaContatoList = pessoaContatoList;
	}

	public List<PessoaEnderecoEntity> getPessoaEnderecoList() {
		return pessoaEnderecoList;
	}

	public void setPessoaEnderecoList(
			List<PessoaEnderecoEntity> pessoaEnderecoList) {
		this.pessoaEnderecoList = pessoaEnderecoList;
	}
	
	public List<PessoaContatoEntity> getItensContato() {
		return pessoaContatoList;
	}
	
	public List<PessoaEnderecoEntity> getItens() {
		return pessoaEnderecoList;
	}
	
	public PessoaContatoEntity addPessoaContato(PessoaContatoEntity pessoaContato) {
		getItensContato().add(pessoaContato);
		pessoaContato.setPessoa(this);;

		return pessoaContato;
	}
	
	public PessoaContatoEntity removePessoaContato(PessoaContatoEntity pessoaContato) {
		getItensContato().remove(pessoaContato);
		pessoaContato.setPessoa(null);
		return pessoaContato;
	}
	
	public PessoaEnderecoEntity addPessoaEndereco(PessoaEnderecoEntity pessoaEndereco) {
		getItens().add(pessoaEndereco);
		pessoaEndereco.setPessoa(this);;

		return pessoaEndereco;
	}
	
	public PessoaEnderecoEntity removePessoaEndereco(PessoaEnderecoEntity pessoaEndereco) {
		getItens().remove(pessoaEndereco);
		pessoaEndereco.setPessoa(null);
		return pessoaEndereco;
	}

	// public List<ClienteEntity> getClienteList() {
	// return clienteList;
	// }

	// public void setClienteList(List<ClienteEntity> clienteList) {
	// this.clienteList = clienteList;
	// }

	@Override
	public String toString() {
		return nome;
	}
	
	public List<ConvenioEntity> getConvenioList() {
		return convenioList;
	}

	public void setConvenioList(List<ConvenioEntity> convenioList) {
		this.convenioList = convenioList;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof PessoaEntity)) {
	           return false;
	    }

	    PessoaEntity that = (PessoaEntity) obj;
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