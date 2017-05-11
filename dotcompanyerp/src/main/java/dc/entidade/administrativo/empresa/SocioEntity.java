package dc.entidade.administrativo.empresa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.diverso.UfEntity;

@Entity
@Table(name = "socio")
@SuppressWarnings("serial")
@Indexed
@XmlRootElement
@Analyzer(impl=BrazilianAnalyzer.class)
public class SocioEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	//@ManyToOne
	//@JoinColumn(name="id_quadro_societario", nullable = false)
	//private QuadroSocietarioEntity quadroSocietario;
	
	@Caption("Quadro Societario")
	@JoinColumn(name = "id_quadro_societario", nullable = true)
	@ManyToOne()
	@NotNull(message = "Quadro Societário é Obrigatório!")
	private QuadroSocietarioEntity quadroSocietario;
	
	@Field
	@Caption("Nome")
	@Column(name="nome")
	@NotNull(message = "Nome é Obrigatório!")
	String nome;
	
	@Field
	@Caption("Cpf")
	@Column(name="cpf")
	String cpf;

	@Field
	@Caption("Logradouro")
	@Column(name="logradouro")
	@NotNull(message = "Logradouro é Obrigatório!")
	String logradouro;

	@Field
	@Caption("Numero")
	@Column(name="numero")
	Integer numero;

	@Field
	@Caption("Complemento")
	@Column(name="complemento")
	@NotNull(message = "Complemento é Obrigatório!")
	String complemento;

	@Field
	@Caption("Bairro")
	@Column(name="bairro")
	@NotNull(message = "Bairro é Obrigatório!")
	String bairro;

	@Field
	@Caption("Municipio")
	@Column(name="municipio")
	String municipio;

	//String uf;
	
	@Transient
	private UfEntity uf;

	String cep;

	String fone;

	String celular;

	String email;

	BigDecimal participacao;

	Integer quotas;

	BigDecimal integralizar;

	@Column(name="data_ingresso")
	@Temporal(TemporalType.DATE)
	Date dataIngresso;

	@Column(name="data_saida")
	@Temporal(TemporalType.DATE)
	Date dataSaida;

//	@ManyToOne
//	@JoinColumn(name = "ID_EMPRESA", nullable = false)
//	private Empresa empresa;

	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<DependenteEntity> dependentes = new ArrayList<>();
	
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParticipacaoSocietariaEntity> participacoes = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuadroSocietarioEntity getQuadroSocietario() {
		return quadroSocietario;
	}

	public void setQuadroSocietario(QuadroSocietarioEntity quadroSocietario) {
		this.quadroSocietario = quadroSocietario;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getParticipacao() {
		return participacao;
	}

	public void setParticipacao(BigDecimal participacao) {
		this.participacao = participacao;
	}

	public Integer getQuotas() {
		return quotas;
	}

	public void setQuotas(Integer quotas) {
		this.quotas = quotas;
	}

	public BigDecimal getIntegralizar() {
		return integralizar;
	}

	public void setIntegralizar(BigDecimal integralizar) {
		this.integralizar = integralizar;
	}

	public Date getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public List<DependenteEntity> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<DependenteEntity> dependentes) {
		this.dependentes = dependentes;
	}
	
	

	public List<ParticipacaoSocietariaEntity> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<ParticipacaoSocietariaEntity> participacoes) {
		this.participacoes = participacoes;
	}

	public DependenteEntity addDependente(DependenteEntity depe) {
		getDependentes().add(depe);
		depe.setSocio(this);

		return depe;
	}

	public ParticipacaoSocietariaEntity addParticipacao(ParticipacaoSocietariaEntity par) {
		getParticipacoes().add(par);
		par.setSocio(this);
		return par;
	}
	
	public DependenteEntity removeDependente(DependenteEntity depe) {
		getDependentes().remove(depe);
		depe.setSocio(null);

		return depe;
	}
	
	public ParticipacaoSocietariaEntity removeParticipacao(ParticipacaoSocietariaEntity par) {
		getParticipacoes().remove(par);
		par.setSocio(null);

		return par;
	}


	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SocioEntity)) {
            return false;
        }

        SocioEntity that = (SocioEntity) obj;
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
