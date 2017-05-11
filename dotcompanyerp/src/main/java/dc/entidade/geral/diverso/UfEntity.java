package dc.entidade.geral.diverso;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "uf")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class UfEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_id_seq")
	@SequenceGenerator(name = "uf_id_seq", sequenceName = "uf_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome = "";

	@Field
	@Caption("Sigla")
	@Column(name = "SIGLA", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Sigla é Obrigatório!")
	private String sigla = "";

	@Field
	@Caption("Código Ibge")
	@Column(name = "CODIGO_IBGE", nullable = false)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código Ibge é Obrigatório!")
	private Integer codigoIbge = new Integer(0);

	/**
	 * REFERENCIA - FK
	 */

	@Caption("País")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pais", nullable = false)
	@NotNull(message = "País é Obrigatório!")
	private PaisEntity pais;

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "uf", fetch = FetchType.LAZY)
	// private List<PessoaEnderecoEntity> pessoaEnderecoList;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="uf",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AgenciaBancoEntity> agenciaBancoList = new ArrayList<AgenciaBancoEntity>();
	

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public UfEntity() {

	}

	public UfEntity(Integer id) {
		this.id = id;
	}

	public UfEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public UfEntity(Integer id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = (sigla == null ? "".trim() : sigla.toUpperCase().trim());
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = (codigoIbge == null ? new Integer(0) : codigoIbge);
	}

	public PaisEntity getPais() {
		return pais;
	}

	public void setPais(PaisEntity pais) {
		this.pais = pais;
	}

	// public List<PessoaEnderecoEntity> getPessoaEnderecoList() {
	// return pessoaEnderecoList;
	// }

	// public void setPessoaEnderecoList(
	// List<PessoaEnderecoEntity> pessoaEnderecoList) {
	// this.pessoaEnderecoList = pessoaEnderecoList;
	// }

	public List<AgenciaBancoEntity> getAgenciaBancoList() {
		return agenciaBancoList;
	}

	public void setAgenciaBancoList(List<AgenciaBancoEntity> agenciaBancoList) {
		this.agenciaBancoList = agenciaBancoList;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UfEntity)) {
            return false;
        }

        UfEntity that = (UfEntity) obj;
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
    
    @Override
    public String toString() {
    	return nome;
    }

}