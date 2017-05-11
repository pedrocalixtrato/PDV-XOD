package dc.entidade.geral.outro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
import dc.control.enums.TipoSindicatoEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ColaboradorEntity;

@Entity
@Table(name = "sindicato")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SindicatoEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sindicato_id_seq")
	@SequenceGenerator(name = "sindicato_id_seq", sequenceName = "sindicato_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Código do banco")
	@Column(name = "CODIGO_BANCO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoBanco = new Integer(0);

	@Field
	@Caption("Código da agência")
	@Column(name = "CODIGO_AGENCIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoAgencia = new Integer(0);

	@Field
	@Caption("Conta do banco")
	@Column(name = "CONTA_BANCO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaBanco = "";

	@Field
	@Caption("Código cedente")
	@Column(name = "CODIGO_CEDENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoCedente = "";

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
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Bairro é Obrigatório!")
	private String bairro = "";

	@Field
	@Caption("Telefone 1")
	@Column(name = "FONE1", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Telefone 1 é Obrigatório!")
	private String fone1 = "";

	@Field
	@Caption("Telefone 2")
	@Column(name = "FONE2", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Telefone 2 é Obrigatório!")
	private String fone2 = "";

	@Field
	@Caption("Email")
	@Column(name = "EMAIL", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Email é Obrigatório!")
	private String email = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo de sindicato")
	@Column(name = "TIPO_SINDICATO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoSindicatoEn tipoSindicato;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data base")
	@Column(name = "DATA_BASE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataBase;

	@Field
	@Caption("Nome")
	@Column(name = "Nome", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome = "";

	@Field
	@Caption()
	@Column(name = "PISO_SALARIAL", precision = 14, scale = 0)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal pisoSalarial = new BigDecimal(0);

	@Field
	@Caption("CNPJ")
	@Column(name = "CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "CNPJ é Obrigatório!")
	private String cnpj = "";

	@Field
	@Caption("UF")
	@Column(name = "uf", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufSigla = "";
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */
	
	@OneToMany(mappedBy = "sindicato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ColaboradorEntity> colaboradorList = new ArrayList<ColaboradorEntity>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public SindicatoEntity() {

	}

	public SindicatoEntity(Integer id) {
		this.id = id;
	}

	public SindicatoEntity(Integer id, String nome) {
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

	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = (codigoBanco == null ? new Integer(0) : codigoBanco);
	}

	public Integer getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = (codigoAgencia == null ? new Integer(0)
				: codigoAgencia);
	}

	public String getContaBanco() {
		return contaBanco;
	}

	public void setContaBanco(String contaBanco) {
		this.contaBanco = (contaBanco == null ? "".trim() : contaBanco
				.toUpperCase().trim());
	}

	public String getCodigoCedente() {
		return codigoCedente;
	}

	public void setCodigoCedente(String codigoCedente) {
		this.codigoCedente = (codigoCedente == null ? "".trim() : codigoCedente
				.toUpperCase().trim());
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = (bairro == null ? "".trim() : bairro.toUpperCase().trim());
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = (fone1 == null ? "".trim() : fone1.toUpperCase().trim());
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = (fone2 == null ? "".trim() : fone2.toUpperCase().trim());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null ? "".trim() : email.toUpperCase().trim());
	}

	public TipoSindicatoEn getTipoSindicato() {
		return tipoSindicato;
	}

	public void setTipoSindicato(TipoSindicatoEn tipoSindicato) {
		this.tipoSindicato = tipoSindicato;
	}

	public Date getDataBase() {
		return dataBase;
	}

	public void setDataBase(Date dataBase) {
		this.dataBase = dataBase;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public BigDecimal getPisoSalarial() {
		return pisoSalarial;
	}

	public void setPisoSalarial(BigDecimal pisoSalarial) {
		this.pisoSalarial = (pisoSalarial == null ? new BigDecimal(0)
				: pisoSalarial);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = (cnpj == null ? "".trim() : cnpj.toUpperCase().trim());
	}

	public String getUfSigla() {
		return ufSigla;
	}

	public void setUfSigla(String ufSigla) {
		this.ufSigla = (ufSigla == null ? "".trim() : ufSigla.toUpperCase()
				.trim());
	}

	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}
	
	public List<ColaboradorEntity> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(List<ColaboradorEntity> colaboradorList) {
		this.colaboradorList = colaboradorList;
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

        if (!(obj instanceof SindicatoEntity)) {
            return false;
        }

        SindicatoEntity that = (SindicatoEntity) obj;
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